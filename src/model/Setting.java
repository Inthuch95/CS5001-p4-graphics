package model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Setting class stores all parameters
 *
 */
public class Setting implements Serializable {
	private final int INITIAL_X_RESOLUTION = 800;
	private final int INITIAL_Y_RESOLUTION = 800;
	private double minReal;
    private double maxReal;
    private double minImaginary;
    private double maxImaginary;
    private int maxIterations;
    private double radiusSquared;
    private int yResolution;
    private int xResolution;
    private Deque<String> colors = new ArrayDeque<String>();
	
	/**
	 * Create Setting instance
	 */
	public Setting() {
		this.xResolution = INITIAL_X_RESOLUTION;
		this.yResolution = INITIAL_Y_RESOLUTION;
		this.minReal = MandelbrotCalculator.INITIAL_MIN_REAL;
		this.maxReal = MandelbrotCalculator.INITIAL_MAX_REAL;
		this.minImaginary = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
		this.maxImaginary = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
		this.maxIterations = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;
		this.radiusSquared = MandelbrotCalculator.DEFAULT_RADIUS_SQUARED;
		initializeColors();
	}
	
	/**
	 * Apply new setting.
	 * @param setting - new setting
	 */
	public void updateSetting(Setting setting) {
		this.yResolution = setting.getXResolution();
		this.xResolution = setting.getXResolution();
		this.minReal = setting.getMinReal();
		this.maxReal = setting.getMaxReal();
		this.minImaginary = setting.getMinImaginary();
		this.maxImaginary = setting.getMaxImaginary();
		this.maxIterations = setting.getMaxIterations();
		this.radiusSquared = setting.getRadiusSquared();
		this.colors.clear();
		this.colors.addAll(setting.getColors());
	}
	
	/**
	 * Restore default setting.
	 */
	public void resetSetting() {
		this.xResolution = INITIAL_X_RESOLUTION;
		this.yResolution = INITIAL_Y_RESOLUTION;
		this.minReal = MandelbrotCalculator.INITIAL_MIN_REAL;
		this.maxReal = MandelbrotCalculator.INITIAL_MAX_REAL;
		this.minImaginary = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
		this.maxImaginary = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
		this.maxIterations = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;
		this.radiusSquared = MandelbrotCalculator.DEFAULT_RADIUS_SQUARED;
		this.colors.clear();
		initializeColors();
	}
	
	private void initializeColors() {
		colors.add("default");
		colors.add("red");
		colors.add("green");
		colors.add("blue");
	}
	
	/**
	 * Change color mapping.
	 */
	public void changeColor() {
		// remove color from the front of the queue and put insert it at the end of the queue
		String currentColor = colors.poll();
		colors.add(currentColor);
	}
	
	/**
	 * Allow the Delegate to access desired width.
	 * @return panel width
	 */
	public int getXResolution() {
		return this.xResolution;
	}
	
	/**
	 * Allow Delegate to access desired height.
	 * @return panel height
	 */
	public int getYResolution() {
		return this.yResolution;
	}
	
	/**
	 * Allow Delegate to access current min real value. 
	 * @return current min real value
	 */
	public double getMinReal() {
		return this.minReal;
	}
	
	/**
	 * Allow Delegate to access current max real value. 
	 * @return current max real value
	 */
	public double getMaxReal() {
		return this.maxReal;
	}
	
	/**
	 * Allow Delegate to access current min imaginary value. 
	 * @return current min imaginary value
	 */
	public double getMinImaginary() {
		return this.minImaginary;
	}
	
	/**
	 * Allow Delegate to access current max imaginary value. 
	 * @return current max imaginary value
	 */
	public double getMaxImaginary() {
		return this.maxImaginary;
	}
	
	/**
	 * Allow Delegate to access current max iterations value. 
	 * @return current max iterations value
	 */
	public int getMaxIterations() {
		return this.maxIterations;
	}
	
	/**
	 * Allow Delegate to access current radius squared value. 
	 * @return current radius squared value
	 */
	public double getRadiusSquared() {
		return this.radiusSquared;
	}
	
	/**
	 * Assign new min real value.
	 * @param new min real value
	 */
	public void setMinReal(double minReal) {
		this.minReal = minReal;
	}
	
	/**
	 * Assign new max real value.
	 * @param new max real value
	 */
	public void setMaxReal(double maxReal) {
		this.maxReal = maxReal;
	}

	/**
	 * Assign new min imaginary value.
	 * @param new min imaginary value
	 */
	public void setMinImaginary(double minImaginary) {
		this.minImaginary = minImaginary;
	}
	
	/**
	 * Assign new max imaginary value.
	 * @param new max imaginary value
	 */
	public void setMaxImaginary(double maxImaginary) {
		this.maxImaginary = maxImaginary;
	}
	
	/**
	 * Assign new max iterations value.
	 * @param new max iterations value
	 */
	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	/**
	 * Assign new radius squared value.
	 * @param new radius squared value
	 */
	public void setRadiusSquared(double radiusSquared) {
		this.radiusSquared = radiusSquared;
	}

	/**
	 * Allow Delegate to access color mappings queue.
	 * @return color mappings queue
	 */
	public Deque<String> getColors() {
		return colors;
	}
	
	/**
	 * Assign new color mappings queue.
	 * @param new color mappings queue
	 */
	public void setColors(Deque<String> colors) {
		this.colors = colors;
	}

	/**
	 * Assign new y-resolution value (height).
	 * @param new y-resolution value (height)
	 */
	public void setyResolution(int yResolution) {
		this.yResolution = yResolution;
	}

	/**
	 * Assign new x-resolution value (width).
	 * @param new x-resolution value (width)
	 */
	public void setxResolution(int xResolution) {
		this.xResolution = xResolution;
	}
}
