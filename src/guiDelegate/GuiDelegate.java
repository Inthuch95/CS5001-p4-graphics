package guiDelegate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import model.MandelbrotModel;
import model.Setting;

public class GuiDelegate implements Observer {
	private final int TEXT_WIDTH = 10;
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
	Setting setting;

	/**
	 * Instantiate a new SimpleGuiDelegate object
	 * @param model the Model to observe, render, and update according to user events
	 */
	public GuiDelegate(MandelbrotModel model){
		this.model = model;
		mainFrame = new JFrame();  // set up the main frame for this GUI
		menu = new JMenuBar();
		toolbar = new JToolBar();
		inputField = new JTextField(TEXT_WIDTH);
		setting = model.getSetting();
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
				model.saveUndoSetting();
				model.clearRedoStack();
				model.changeColor();
			}
		});
		reset = new JButton("Reset");
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.saveUndoSetting();
				model.clearRedoStack();
				model.resetModel();
			}
		});
		undo = new JButton("Undo");
		undo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.saveRedoSetting();
				redo.setEnabled(true);
				model.restoreUndoSetting();
				model.updateModel();
			}
		});
		undo.setEnabled(false);
		redo = new JButton("Redo");
		redo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.saveUndoSetting();
				model.restoreRedoSetting();
				model.updateModel();
			}
		});
		redo.setEnabled(false);
		changeIter = new JButton("Change");
		changeIter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				model.saveUndoSetting();
				setting.setMaxIterations(Integer.parseInt(inputField.getText()));
				model.clearRedoStack();
				model.updateModel();
			}
		});

		maxIter = new JLabel("Max iteration: ");
		currentIter = new JLabel("Current iteration: " + model.getSetting().getMaxIterations());
		// to translate key event for the text filed into appropriate model method call
		inputField.addKeyListener(new KeyListener(){        
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					model.saveUndoSetting();
					setting.setMaxIterations(Integer.parseInt(inputField.getText()));
					model.clearRedoStack();
					model.updateModel();
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent e) {
			}
		});

		// add buttons, label, and textfield to the toolbar
		toolbar.add(changeColor);
		toolbar.addSeparator();
		toolbar.add(reset);
		toolbar.addSeparator();
		toolbar.add(undo);
		toolbar.addSeparator();
		toolbar.add(redo);
		toolbar.addSeparator();
		toolbar.add(currentIter);
		toolbar.addSeparator();
		toolbar.add(maxIter);
		toolbar.addSeparator();
		toolbar.add(inputField);
		toolbar.addSeparator();
		toolbar.add(changeIter);
		toolbar.addSeparator();
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
				JFileChooser fc = new JFileChooser();
				File workingDirectory = new File(System.getProperty("user.dir"));
				fc.setCurrentDirectory(workingDirectory);
				int returnVal = fc.showOpenDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						model.saveUndoSetting();
						model.clearRedoStack();
						FileInputStream fi = new FileInputStream(file);
						ObjectInputStream oi = new ObjectInputStream(fi);
						Setting loadedSetting = (Setting) oi.readObject();
						setting.updateSetting(loadedSetting);
						oi.close();
						fi.close();
						model.updateModel();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				File workingDirectory = new File(System.getProperty("user.dir"));
				fc.setCurrentDirectory(workingDirectory);
				int returnVal = fc.showSaveDialog(fc);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						System.out.println ("File is " + file.toString());
						FileOutputStream f = new FileOutputStream(file);
						ObjectOutputStream o = new ObjectOutputStream(f);
						o.writeObject(setting);
						o.close();
						f.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
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
	
	public void update(Observable o, Object arg) {
		// Tell the SwingUtilities thread to update the GUI components.
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				if (!model.getUndoSt().isEmpty()) {
					undo.setEnabled(true);
				} else {
					undo.setEnabled(false);
				}
				if (!model.getRedoSt().isEmpty()) {
					redo.setEnabled(true);
				} else {
					redo.setEnabled(false);
				}
				currentIter.setText("Current iteration: " + setting.getMaxIterations());
				inputField.setText("");
				panel.repaint();
			}
		});
	}
}
