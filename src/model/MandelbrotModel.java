package model;

import java.util.Observable;

public class MandelbrotModel extends Observable {
	private int[][] madelbrotData;
	private MandelbrotCalculator mandelCalc;
	private Setting setting; 
	
	public MandelbrotModel() {
		this.mandelCalc = new MandelbrotCalculator();
		this.setting = new Setting();
		this.madelbrotData = mandelCalc.calcMandelbrotSet(setting.getXResolution(), 
				setting.getYResolution(), 
				MandelbrotCalculator.INITIAL_MIN_REAL, 
				MandelbrotCalculator.INITIAL_MAX_REAL, 
				MandelbrotCalculator.INITIAL_MIN_IMAGINARY, 
				MandelbrotCalculator.INITIAL_MAX_IMAGINARY, 
				MandelbrotCalculator.INITIAL_MAX_ITERATIONS, 
				MandelbrotCalculator.DEFAULT_RADIUS_SQUARED);
	}
	
	public void updateModel() {
		this.madelbrotData = mandelCalc.calcMandelbrotSet(setting.getXResolution(), 
				setting.getYResolution(),
				setting.getMinReal(),
				setting.getMaxReal(),
				setting.getMinImaginary(),
				setting.getMaxImaginary(),
				setting.getMaxIterations(),
				setting.getRadiusSquared());
		setChanged();
		notifyObservers();
	}
	
	public void resetModel() {
		setting.resetSetting();
		madelbrotData = mandelCalc.calcMandelbrotSet(800, 
				800, 
				MandelbrotCalculator.INITIAL_MIN_REAL, 
				MandelbrotCalculator.INITIAL_MAX_REAL, 
				MandelbrotCalculator.INITIAL_MIN_IMAGINARY, 
				MandelbrotCalculator.INITIAL_MAX_IMAGINARY, 
				MandelbrotCalculator.INITIAL_MAX_ITERATIONS, 
				MandelbrotCalculator.DEFAULT_RADIUS_SQUARED);
		setChanged();
		notifyObservers();
	}
	
	public void changeColor() {
		setting.changeColor();
		setChanged();
		notifyObservers();
	}
	
	public int[][] getMadelbrotData() {
		return this.madelbrotData;
	}

	public Setting getSetting() {
		return this.setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

}
