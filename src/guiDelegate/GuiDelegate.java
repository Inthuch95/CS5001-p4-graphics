package guiDelegate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import model.MandelbrotModel;


/**
 * The SimpleGuiDelegate class whose purpose is to render relevant state information stored in the model and make changes to the model state based on user events. 
 * 
 * This class uses Swing to display the model state when the model changes. This is the view aspect of the delegate class. 
 * It also listens for user input events (in the listeners defined below), translates these to appropriate calls to methods
 * defined in the model class so as to make changes to the model. This is the controller aspect of the delegate class. 
 * The class implements Observer in order to permit it to be added as an observer of the model class. 
 * When the model calls notifyObservers() (after executing setChanged()) 
 * the update(...) method below is called in order to update the view of the model.
 * 
 * @author jonl
 *
 */

public class GuiDelegate implements Observer {
	private static final int TEXT_WIDTH = 10;
	private JFrame mainFrame;
	private JToolBar toolbar;
	private JTextField inputField;
	private JButton changeColor;
	private JButton reset;
	private JButton undo;
	private JButton redo;
	private JButton changeIter;
	private Panel panel;
	private JLabel maxIter;
	private JLabel currentIter;
	private JMenuBar menu;
	private MandelbrotModel model = new MandelbrotModel();
	private Setting setting;
	private Stack<Setting> undoSt = new Stack<Setting>();
	private Stack<Setting> redoSt = new Stack<Setting>();

	/**
	 * Instantiate a new SimpleGuiDelegate object
	 * @param model the Model to observe, render, and update according to user events
	 */
	public GuiDelegate(MandelbrotModel model){
		this.model = model;
		this.mainFrame = new JFrame();  // set up the main frame for this GUI
		setting = new Setting(model);
		menu = new JMenuBar();
		toolbar = new JToolBar();
		inputField = new JTextField(TEXT_WIDTH);
		panel = new Panel(model);
		setupComponents();
		
		// add the delegate UI component as an observer of the model
		// so as to detect changes in the model and update the GUI view accordingly
		model.addObserver(this);
		
	}


	
	/**
	 * Initialises the toolbar to contain the buttons, label, input field, etc. and adds the toolbar to the main frame.
	 * Listeners are created for the buttons and text field which translate user events to model object method calls (controller aspect of the delegate) 
	 */
	private void setupToolbar(){
		changeColor = new JButton("Change color");
		changeColor.addActionListener(new ActionListener(){     // to translate event for this button into appropriate model method call
			public void actionPerformed(ActionEvent e){
				// should  call method in model class if you want it to affect model
				JOptionPane.showMessageDialog(mainFrame, "Ooops, Change color not linked to model!");
			}
		});
		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveSetting();
				setting.resetSetting();
				model.resetModel();
			}
		});
		undo = new JButton("Undo");
		undo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (!undoSt.isEmpty()) {
					restoreSetting();
					model.updateModel(setting);
				}
			}
		});
		redo = new JButton("Redo");
		redo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// should  call method in model class if you want it to affect model
				JOptionPane.showMessageDialog(mainFrame, "Ooops, Redo not linked to model!");
			}
		});
		
		changeIter = new JButton("Change");
		changeIter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveSetting();
				setting.setMaxIterations(Integer.parseInt(inputField.getText()));
				model.updateModel(setting);
			}
		});

		maxIter = new JLabel("Max iteration: ");
		currentIter = new JLabel("Current iteration: " + model.getMaxIterations());
		
		inputField.addKeyListener(new KeyListener(){        // to translate key event for the text filed into appropriate model method call
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					saveSetting();
					setting.setMaxIterations(Integer.parseInt(inputField.getText()));
					model.updateModel(setting);
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent e) {
			}
		});

		// add buttons, label, and textfield to the toolbar
		toolbar.add(changeColor);
		toolbar.add(reset);
		toolbar.add(undo);
		toolbar.add(redo);
		toolbar.add(currentIter);
		toolbar.add(maxIter);
		toolbar.add(inputField);
		toolbar.add(changeIter);
		// add toolbar to north of main frame
		mainFrame.add(toolbar, BorderLayout.NORTH);
	}

	
	/**
	 * Sets up File menu with Load and Save entries
	 * The Load and Save actions would normally be translated to appropriate model method calls similar to the way the code does this 
	 * above in @see #setupToolbar(). However, load and save functionality is not implemented here, instead the code below merely displays 
	 * an error message. 
	 */ 
	private void setupMenu(){
		JMenu file = new JMenu ("File");
		JMenuItem load = new JMenuItem ("Load");
		JMenuItem save = new JMenuItem ("Save");
		file.add (load);
		file.add (save);
		menu.add (file);
		load.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				// should call appropriate method in model class if you want it to do something useful
				JOptionPane.showMessageDialog(mainFrame, "Ooops, Load not linked to model!");
			}
		});
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// should call appropriate method in model class if you want it to do something useful
				JOptionPane.showMessageDialog(mainFrame, "Ooops, Save not linked to model!");
			}
		});		
		// add menubar to frame
		mainFrame.setJMenuBar(menu);
	}
	
	/**
	 * Method to setup the menu and toolbar components  
	 */
	private void setupComponents(){
		setupMenu();
		setupToolbar();
		panel.setBackground(Color.WHITE);
		mainFrame.add(panel, BorderLayout.CENTER);
		mainFrame.setSize (setting.getXResolution(), setting.getYResolution() + 90);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	private void saveSetting() {
		Setting oldSetting = new Setting(model);
		undoSt.push(oldSetting);
	}
	
	private void restoreSetting() {
		Setting oldSetting = undoSt.pop();
		setting.updateSetting(oldSetting);
	}
	
	public void update(Observable o, Object arg) {

		// Tell the SwingUtilities thread to update the GUI components.
		// This is safer than executing outputField.setText(model.getText()) 
		// in the caller's thread 
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				currentIter.setText("Current iteration: " + setting.getMaxIterations());
				inputField.setText("");
				panel.repaint();
			}
		});
	}
}
