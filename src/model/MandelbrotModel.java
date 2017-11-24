package model;

import java.util.Observable;
import java.util.Stack;

/**
 * The MandelbrotModel stores Mandelbrot data and setting. 
 * The model extends the Observable class and is observed by the Delegate class.
 *
 */
public class MandelbrotModel extends Observable {
	private int[][] madelbrotData;
	private MandelbrotCalculator mandelCalc;
	private Setting setting; 
	private Stack<Setting> undoSt = new Stack<Setting>();
	private Stack<Setting> redoSt = new Stack<Setting>();
	
	/**
	 * Create model instance.
	 */
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
	
	
	/**
	 * Recalculate the Mandelbrot set and notify the observer.
	 */
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
	
	/**
	 * Restore default setting and notify the observer.
	 */
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
	
	/**
	 * Clear all redo data.
	 */
	public void clearRedoStack() {
		redoSt.clear();
	}
	
	/**
	 * Save current setting to undo stack.
	 */
	public void saveUndoSetting() {
		Setting oldSetting = new Setting();
		oldSetting.updateSetting(setting);
		undoSt.push(oldSetting);
	}
	
	/**
	 * Save current setting to redo stack.
	 */
	public void saveRedoSetting() {
		Setting oldSetting = new Setting();
		oldSetting.updateSetting(setting);
		redoSt.push(oldSetting);
	}
	
	/**
	 * Update the setting with the setting at the top of undo stack.
	 */
	public void restoreUndoSetting() {
		Setting oldSetting = undoSt.pop();
		setting.updateSetting(oldSetting);
	}
	
	/**
	 * Update setting with the setting at the top of redo stack.
	 */
	public void restoreRedoSetting() {
		Setting newSetting = redoSt.pop();
		setting.updateSetting(newSetting);
	}
	
	/**
	 * Change current color mapping.
	 */
	public void changeColor() {
		setting.changeColor();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * This allows the panel to access current Mandelbrot set.
	 * @return Mandelbrot data
	 */
	public int[][] getMadelbrotData() {
		return this.madelbrotData;
	}
	
	/**
	 * This allows the Delegate to access current setting.
	 * @return current setting
	 */
	public Setting getSetting() {
		return this.setting;
	}

	/**
	 * Apply new setting.
	 * @param new setting
	 */
	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	/**
	 * Allows the Delegate to access undo stack.
	 * @return undo stack
	 */
	public Stack<Setting> getUndoSt() {
		return undoSt;
	}

	/**
	 * Assign new undo stack.
	 * @param undoSt - new undo stack
	 */
	public void setUndoSt(Stack<Setting> undoSt) {
		this.undoSt = undoSt;
	}

	/**
	 * Allows the Delegate to access redo stack.
	 * @return redo stack
	 */
	public Stack<Setting> getRedoSt() {
		return redoSt;
	}

	/**
	 * Assign new redo stack.
	 * @param redoSt - new redo stack
	 */
	public void setRedoSt(Stack<Setting> redoSt) {
		this.redoSt = redoSt;
	}

}
