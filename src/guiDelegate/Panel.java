package guiDelegate;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.MandelbrotModel;

public class Panel extends JPanel {
	MandelbrotModel model;
	
	public Panel(MandelbrotModel model) {
		this.model = model;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] madelbrotData = model.getMadelbrotData();
        int maxIterations = model.getMaxIterations();
        for (int i = 0; i < madelbrotData.length; i++) {
        	for (int j = 0; j < madelbrotData[i].length; j++) {
        		if (madelbrotData[i][j] >= maxIterations) {
        			g.setColor(new Color(0, 0, 0));
                	g.drawLine(j, i, j, i);
                } else {
//                	g.setColor(new Color(255, 0, 0));
//                	g.drawLine(j, i, j, i);
                }
                if (i == 799) {
                	g.drawLine(j, i, j, i);
                }
                if (j == 799) {
                	g.drawLine(j, i, j, i);
                }
            }
        }
    }
}
