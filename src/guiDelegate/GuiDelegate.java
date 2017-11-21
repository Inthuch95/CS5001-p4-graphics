package guiDelegate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

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

import model.MandelbrotCalculator;


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
	// Initial parameter values
    protected static final double INITIAL_MIN_REAL = -2.0;
    protected static final double INITIAL_MAX_REAL = 0.7;
    protected static final double INITIAL_MIN_IMAGINARY = -1.25;
    protected static final double INITIAL_MAX_IMAGINARY = 1.25;
    protected static final int INITIAL_MAX_ITERATIONS = 50;

    // Default parameter values
    protected static final double DEFAULT_RADIUS_SQUARED = 4.0;
	
	private static final int FRAME_HEIGHT = 800 + 90;
	private static final int FRAME_WIDTH = 800;
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
	
	private MandelbrotCalculator model = new MandelbrotCalculator();
	private int[][] madelbrotData = model.calcMandelbrotSet(800, 800, INITIAL_MIN_REAL, INITIAL_MAX_REAL
			, INITIAL_MIN_IMAGINARY, INITIAL_MAX_IMAGINARY, INITIAL_MAX_ITERATIONS, DEFAULT_RADIUS_SQUARED);
	private Setting setting;

	/**
	 * Instantiate a new SimpleGuiDelegate object
	 * @param model the Model to observe, render, and update according to user events
	 */
	public GuiDelegate(MandelbrotCalculator model){
		this.model = model;
		this.mainFrame = new JFrame();  // set up the main frame for this GUI
		this.setting = new Setting(FRAME_HEIGHT, FRAME_WIDTH, INITIAL_MIN_REAL, INITIAL_MAX_REAL
				, INITIAL_MIN_IMAGINARY, INITIAL_MAX_IMAGINARY, INITIAL_MAX_ITERATIONS, DEFAULT_RADIUS_SQUARED);
		menu = new JMenuBar();
		toolbar = new JToolBar();
		inputField = new JTextField(TEXT_WIDTH);
		panel = new Panel(madelbrotData, setting);
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
				// should  call method in model class if you want it to affect model
				JOptionPane.showMessageDialog(mainFrame, "Ooops, Reset not linked to model!");
			}
		});
		undo = new JButton("Undo");
		undo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// should  call method in model class if you want it to affect model
				JOptionPane.showMessageDialog(mainFrame, "Ooops, Undo not linked to model!");
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
				// should  call method in model class if you want it to affect model
				JOptionPane.showMessageDialog(mainFrame, "Ooops, Change not linked to model!");
			}
		});

		maxIter = new JLabel("Max iteration: ");
		currentIter = new JLabel("Current iteration: ");
		
		inputField.addKeyListener(new KeyListener(){        // to translate key event for the text filed into appropriate model method call
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
//					model.addText(inputField.getText());    // tell model to add text entered by user
					inputField.setText("");                 // clear the input box in the GUI view
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
		panel.setBackground(new Color(255, 255, 255));
		mainFrame.add(panel, BorderLayout.CENTER);
		mainFrame.setSize (FRAME_WIDTH, FRAME_HEIGHT);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	/**
	 * This method contains code to update the GUI view when the model changes
	 * The method is called when the model changes (i.e. when the model executes setChanged() and notifyObservers())
	 * Any parameters passed to notifyObservers @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * are passed to update. 
	 * The code in update should get hold of the model state it requires and update the relevant GUI components so that
	 * an updated view of the model is displayed on screen.
	 * For this simple example, the only state information we need from the model is what is in the model's text buffer and the
	 * only GUI view element we need to update is the text area used for output.
	 * 
	 * NOTE: In a more complex program, the model may hold information on a variety of objects, such as various shapes, their positions, etc.
	 * and the GUI view would then have to get hold of all that state info and produce a graphical representation of theses objects.
	 * As a result, the update method would have to get hold of various bits of model state and then
	 * call the relevant methods (defined in the GUI code) to render the objects.
	 * 
	 */
	public void update(Observable o, Object arg) {

		// Tell the SwingUtilities thread to update the GUI components.
		// This is safer than executing outputField.setText(model.getText()) 
		// in the caller's thread 
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
//				outputField.setText(model.getText());
			}
		});
	}
}
