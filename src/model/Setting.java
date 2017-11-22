package model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

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
    Deque<String> colors = new ArrayDeque<String>();
	
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
	
	public void changeColor() {
		String currentColor = colors.poll();
		colors.add(currentColor);
	}
	
	public int getXResolution() {
		return this.xResolution;
	}
	
	public int getYResolution() {
		return this.yResolution;
	}
	
	public double getMinReal() {
		return this.minReal;
	}
	
	public double getMaxReal() {
		return this.maxReal;
	}
	
	public double getMinImaginary() {
		return this.minImaginary;
	}
	
	public double getMaxImaginary() {
		return this.maxImaginary;
	}
	
	public int getMaxIterations() {
		return this.maxIterations;
	}
	
	public double getRadiusSquared() {
		return this.radiusSquared;
	}

	public void setMinReal(double minReal) {
		this.minReal = minReal;
	}

	public void setMaxReal(double maxReal) {
		this.maxReal = maxReal;
	}

	public void setMinImaginary(double minImaginary) {
		this.minImaginary = minImaginary;
	}

	public void setMaxImaginary(double maxImaginary) {
		this.maxImaginary = maxImaginary;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	public void setRadiusSquared(double radiusSquared) {
		this.radiusSquared = radiusSquared;
	}

	public Deque<String> getColors() {
		return colors;
	}

	public void setColors(Deque<String> colors) {
		this.colors = colors;
	}

	public void setyResolution(int yResolution) {
		this.yResolution = yResolution;
	}

	public void setxResolution(int xResolution) {
		this.xResolution = xResolution;
	}
}
