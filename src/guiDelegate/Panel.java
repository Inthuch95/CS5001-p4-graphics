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

public class Panel extends JPanel implements MouseListener, MouseMotionListener {
	MandelbrotModel model;
	int x1, y1, x2, y2;
	
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
        int[][] madelbrotData = model.getMadelbrotData();
        int maxIterations = model.getSetting().getMaxIterations();
        for (int i = 0; i < madelbrotData.length; i++) {
        	for (int j = 0; j < madelbrotData[i].length; j++) {
        		if (madelbrotData[i][j] >= maxIterations) {
        			g.setColor(Color.BLACK);
                } else {
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
		double minReal = model.getSetting().getMinReal();
		double maxReal = model.getSetting().getMaxReal();
		double minImaginary = model.getSetting().getMinImaginary();
		double maxImaginary = model.getSetting().getMaxImaginary();
		int xResolution = model.getSetting().getXResolution();
		int yResolution = model.getSetting().getYResolution();
		double rangeReal = maxReal - minReal;
		double rangeImaginary = maxImaginary - minImaginary;
		int newX1, newX2;
		if (x2 > x1) {
			newX1 = x1;
			newX2 = x2;
		} else {
			newX1 = x2;
			newX2 = x1;
		}
		int newY1, newY2;
		if (y2 > y1) {
			newY1 = y1;
			newY2 = y2;
		} else {
			newY1 = y2;
			newY2 = y1;
		} 
		double ratioMinReal = newX1 * 1.0 / xResolution;
		double ratioMaxReal = newX2 * 1.0 / xResolution;
		double ratioMinImaginary = newY1 * 1.0 / yResolution;
		double ratioMaxImaginary = newY2 * 1.0 / yResolution;
		model.saveUndoSetting();
		model.clearRedoStack();
		model.getSetting().setMinReal(minReal + ratioMinReal * rangeReal);
		model.getSetting().setMaxReal(minReal + ratioMaxReal * rangeReal);
		model.getSetting().setMinImaginary(minImaginary + ratioMinImaginary * rangeImaginary);
		model.getSetting().setMaxImaginary(minImaginary + ratioMaxImaginary * rangeReal);
		x1 = -1;
		x2 = -1;
		y1 = -1;
		y2 = -1;
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
}
