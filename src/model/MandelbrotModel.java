package model;

import java.util.Observable;
import java.util.Stack;

public class MandelbrotModel extends Observable {
	private int[][] madelbrotData;
	private MandelbrotCalculator mandelCalc;
	private Setting setting; 
	private Stack<Setting> undoSt = new Stack<Setting>();
	private Stack<Setting> redoSt = new Stack<Setting>();
	
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
	
	public void recalculate() {
		this.madelbrotData = mandelCalc.calcMandelbrotSet(setting.getXResolution(), 
				setting.getYResolution(),
				setting.getMinReal(),
				setting.getMaxReal(),
				setting.getMinImaginary(),
				setting.getMaxImaginary(),
				setting.getMaxIterations(),
				setting.getRadiusSquared());
	}
	
	public void resetModel() {
		undoSt.clear();
		redoSt.clear();
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
	
	public void clearRedoStack() {
		redoSt.clear();
	}
	
	public void saveUndoSetting() {
		Setting oldSetting = new Setting();
		oldSetting.updateSetting(setting);
		undoSt.push(oldSetting);
	}
	
	public void saveRedoSetting() {
		Setting oldSetting = new Setting();
		oldSetting.updateSetting(setting);
		redoSt.push(oldSetting);
	}
	
	public void restoreUndoSetting() {
		Setting oldSetting = undoSt.pop();
		setting.updateSetting(oldSetting);
	}
	
	public void restoreRedoSetting() {
		Setting newSetting = redoSt.pop();
		setting.updateSetting(newSetting);
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

	public Stack<Setting> getUndoSt() {
		return undoSt;
	}

	public void setUndoSt(Stack<Setting> undoSt) {
		this.undoSt = undoSt;
	}

	public Stack<Setting> getRedoSt() {
		return redoSt;
	}

	public void setRedoSt(Stack<Setting> redoSt) {
		this.redoSt = redoSt;
	}

}
