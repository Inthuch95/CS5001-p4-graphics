package guiDelegate;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.JPanel;

import model.MandelbrotModel;
import model.Setting;

public class Panel extends JPanel {
	MandelbrotModel model;
	
	public Panel(MandelbrotModel model) {
		this.model = model;
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
                	g.drawLine(j, i, j, i);
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
                	g.drawLine(j, i, j, i);
                }
            }
        }
    }
}
