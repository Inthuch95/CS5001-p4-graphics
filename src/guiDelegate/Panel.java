package guiDelegate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.JPanel;

import model.MandelbrotModel;
import model.Setting;

/**
 * JPanel used to display the image computed from the Mandelbrot set.
 *
 */
public class Panel extends JPanel implements MouseListener, MouseMotionListener {
	private MandelbrotModel model;
	private Deque<Setting> frames = new ArrayDeque<Setting>();
	private final int ZOOM_FRAMES = 10;
	private int x1, y1, x2, y2;
	
	/**
	 * Create Panel instance
	 * @param model - current model
	 */
	public Panel(MandelbrotModel model) {
		this.model = model;
		x1 = -1;
		x2 = -1;
		y1 = -1;
		y2 = -1;
		this.setBackground(Color.WHITE);
		super.addMouseListener(this);
		super.addMouseMotionListener(this);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.paintImage(g);
		this.drawZoomBox(g);
    }
	
	private void paintImage(Graphics g) {
		int[][] madelbrotData = model.getMadelbrotData();
        int maxIterations = model.getSetting().getMaxIterations();
        for (int i = 0; i < madelbrotData.length; i++) {
        	for (int j = 0; j < madelbrotData[i].length; j++) {
        		if (madelbrotData[i][j] >= maxIterations) {
        			g.setColor(Color.BLACK);
                } else {
                	// draw with different color mapping
                	Deque<String> colors = model.getSetting().getColors();
                	String currentColor = colors.peek();
                	float colorValue = (float) (madelbrotData[i][j] * 1.0/maxIterations);
                	if (currentColor.equals("red")) {
                		g.setColor(new Color(colorValue, 0, 0));
                	} else if (currentColor.equals("green")) {
                		g.setColor(new Color(0, colorValue, 0));
                	} else if (currentColor.equals("blue")) {
                		g.setColor(new Color(0, 0, colorValue));
                	} else {
                		g.setColor(Color.WHITE);
                	}
                }
        		g.drawLine(j, i, j, i);
            }
        }
	}
	
	private void drawZoomBox(Graphics g) {
		Deque<String> colors = model.getSetting().getColors();
        String currentColor = colors.peek();
        if (currentColor.equals("red")) {
    		g.setColor(Color.RED);
    	} else if (currentColor.equals("green")) {
    		g.setColor(Color.GREEN);
    	} else if (currentColor.equals("blue")) {
    		g.setColor(Color.BLUE);
    	} else {
    		g.setColor(Color.BLACK);
    	}
        int width = Math.abs(x2 - x1);
    	int height = Math.abs(y2 - y1);
        // drag southeast
        if (x2 > x1 && y2 > y1) {
        	g.drawRect(x1, y1, width, height);
        }
        // drag northeast
        if (x2 > x1 && y2 < y1) {
        	g.drawRect(x1, y2, width, height);
        }
        // drag southwest
        if (x2 < x1 && y2 > y1) {
        	g.drawRect(x2, y1, width, height);
        }
        // drag northwest
        if (x2 < x1 && y2 < y1) {
        	g.drawRect(x2, y2, width, height);
        }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Setting currentSetting = new Setting();
		currentSetting.updateSetting(model.getSetting());
		double minReal = currentSetting.getMinReal();
		double maxReal = currentSetting.getMaxReal();
		double minImaginary = currentSetting.getMinImaginary();
		double maxImaginary = currentSetting.getMaxImaginary();
		int xResolution = currentSetting.getXResolution();
		int yResolution = currentSetting.getYResolution();
		double rangeReal = maxReal - minReal;
		double rangeImaginary = maxImaginary - minImaginary;
		int newX1, newX2, newY1, newY2;
		if (x2 > x1) {
			newX1 = x1;
			newX2 = x2;
		} else {
			newX1 = x2;
			newX2 = x1;
		}
		if (y2 > y1) {
			newY1 = y1;
			newY2 = y2;
		} else {
			newY1 = y2;
			newY2 = y1;
		}
		double scale;
		double ratioMinReal = newX1 / (double) xResolution;
		double ratioMaxReal = newX2 / (double) xResolution;
		double ratioMinImaginary = newY1 / (double) yResolution;
		double ratioMaxImaginary = newY2 / (double) yResolution;
		// calculate new setting
		double newMinReal = minReal + (ratioMinReal * rangeReal);
		double newMaxReal = minReal + (ratioMaxReal * rangeReal);
		double newMinImaginary =  minImaginary + (ratioMinImaginary * rangeImaginary);
		double newMaxImaginary = minImaginary + (ratioMaxImaginary * rangeReal);
		double rangeMinReal = newMinReal - minReal;
		double rangeMaxReal = maxReal - newMaxReal;
		double rangeMinImaginary = newMinImaginary - minImaginary;
		double rangeMaxImaginary = maxImaginary - newMaxImaginary;
		x1 = -1;
		x2 = -1;
		y1 = -1;
		y2 = -1;
		// generate animation frames
		for (int i = 1; i <= ZOOM_FRAMES; i++) {
			scale = (double) i / (double) ZOOM_FRAMES;
			Setting animationSetting = new Setting();
			animationSetting.setMinReal(minReal + (scale * rangeMinReal));
			animationSetting.setMaxReal(maxReal - (scale * rangeMaxReal));
			animationSetting.setMinImaginary(minImaginary + (scale * rangeMinImaginary));
			animationSetting.setMaxImaginary(maxImaginary - (scale * rangeMaxImaginary));
			animationSetting.setMaxIterations(currentSetting.getMaxIterations());
			animationSetting.setColors(currentSetting.getColors());
			frames.add(animationSetting);
		}
		// save current setting
		model.saveUndoSetting();
		model.clearRedoStack();
		// update setting and GUI
		model.getSetting().updateSetting(frames.remove());
		model.updateModel();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 =  e.getY();
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Allow GuiDelegate to access animation frames
	 * @return animation frames
	 */
	public Deque<Setting> getFrames() {
		return frames;
	}
}
