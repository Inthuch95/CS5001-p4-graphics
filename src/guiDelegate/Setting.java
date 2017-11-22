package guiDelegate;

import model.MandelbrotModel;

public class Setting {
	// Initial parameter values
    private static final double INITIAL_MIN_REAL = -2.0;
    private static final double INITIAL_MAX_REAL = 0.7;
    private static final double INITIAL_MIN_IMAGINARY = -1.25;
    private static final double INITIAL_MAX_IMAGINARY = 1.25;
    private static final int INITIAL_MAX_ITERATIONS = 50;
    // Default parameter values
    private static final double DEFAULT_RADIUS_SQUARED = 4.0;
    private double minReal;
    private double maxReal;
    private double minImaginary;
    private double maxImaginary;
    private int maxIterations;
    private double radiusSquared;
    private int yResolution;
    private int xResolution;
	
	public Setting(MandelbrotModel model) {
		this.yResolution = model.getXResolution();
		this.xResolution = model.getXResolution();
		this.minReal = model.getMinReal();
		this.maxReal = model.getMaxReal();
		this.minImaginary = model.getMinImaginary();
		this.maxImaginary = model.getMaxImaginary();
		this.maxIterations = model.getMaxIterations();
		this.radiusSquared = model.getRadiusSquared();
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
	}
	
	public void resetSetting() {
		this.xResolution = 800;
		this.yResolution = 800;
		this.minReal = INITIAL_MIN_REAL;
		this.maxReal = INITIAL_MAX_REAL;
		this.minImaginary = INITIAL_MIN_IMAGINARY;
		this.maxImaginary = INITIAL_MAX_IMAGINARY;
		this.maxIterations = INITIAL_MAX_ITERATIONS;
		this.radiusSquared = DEFAULT_RADIUS_SQUARED;
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

	public void setyResolution(int yResolution) {
		this.yResolution = yResolution;
	}

	public void setxResolution(int xResolution) {
		this.xResolution = xResolution;
	}
}
