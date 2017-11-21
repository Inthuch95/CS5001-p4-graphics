package guiDelegate;

import model.MandelbrotCalculator;

public class Setting {
	// Initial parameter values
    protected static final double INITIAL_MIN_REAL = -2.0;
    protected static final double INITIAL_MAX_REAL = 0.7;
    protected static final double INITIAL_MIN_IMAGINARY = -1.25;
    protected static final double INITIAL_MAX_IMAGINARY = 1.25;
    protected static final int INITIAL_MAX_ITERATIONS = 50;
    // Default parameter values
    protected static final double DEFAULT_RADIUS_SQUARED = 4.0;
	protected double minReal;
    protected double maxReal;
    protected double minImaginary;
    protected double maxImaginary;
    protected int maxIterations;
    protected double radiusSquared;
	protected int yResolution;
	protected int xResolution;
	
	public Setting(int xResolution, int yResolution, double minReal, double maxReal, double minImaginary
			, double maxImaginary, int maxIterations, double radiusSquared) {
		this.yResolution = yResolution;
		this.xResolution = xResolution;
		this.minReal = minReal;
		this.maxReal = maxReal;
		this.minImaginary = minImaginary;
		this.maxImaginary = maxImaginary;
		this.maxIterations = maxIterations;
		this.radiusSquared = radiusSquared;
	}
	
	public void updateSetting(int xResolution, int yResolution, double minReal, double maxReal
			, double minImaginary, double maxImaginary, int maxIterations, double radiusSquared) {
		this.yResolution = yResolution;
		this.xResolution = xResolution;
		this.minReal = minReal;
		this.maxReal = maxReal;
		this.minImaginary = minImaginary;
		this.maxImaginary = maxImaginary;
		this.maxIterations = maxIterations;
		this.radiusSquared = radiusSquared;
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
}
