package guiDelegate;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	int[][] madelbrotData;
	Setting setting;
	
	public Panel(int[][] madelbrotData, Setting setting) {
		this.madelbrotData = madelbrotData;
		this.setting = setting;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < madelbrotData.length; i++) {
            for (int j = 0; j < madelbrotData[i].length; j++) {
                if (madelbrotData[i][j] >= setting.maxIterations) {
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
