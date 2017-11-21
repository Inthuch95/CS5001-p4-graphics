package model;

import java.util.Observable;

public class MandelbrotModel extends Observable {
	private int[][] madelbrotData;
	private MandelbrotCalculator mandelCalc;
	private int xResolution, yResolution, maxIterations;
	private double minReal, maxReal, minImaginary, maxImaginary, radiusSquared;
	
	public MandelbrotModel() {
		mandelCalc = new MandelbrotCalculator();
		this.xResolution = 800;
		this.yResolution = 800;
		this.minReal = MandelbrotCalculator.INITIAL_MIN_REAL;
		this.maxReal = MandelbrotCalculator.INITIAL_MAX_REAL;
		this.minImaginary = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
		this.maxImaginary = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
		this.maxIterations = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;
		this.radiusSquared = MandelbrotCalculator.DEFAULT_RADIUS_SQUARED;
		this.madelbrotData = mandelCalc.calcMandelbrotSet(800, 
				800, 
				MandelbrotCalculator.INITIAL_MIN_REAL, 
				MandelbrotCalculator.INITIAL_MAX_REAL, 
				MandelbrotCalculator.INITIAL_MIN_IMAGINARY, 
				MandelbrotCalculator.INITIAL_MAX_IMAGINARY, 
				MandelbrotCalculator.INITIAL_MAX_ITERATIONS, 
				MandelbrotCalculator.DEFAULT_RADIUS_SQUARED);
	}
	
	public void updateModel(int xResolution, int yResolution, double minReal, double maxReal
			, double minImaginary, double maxImaginary, int maxIterations, double radiusSquared) {
		this.xResolution = xResolution;
		this.yResolution = yResolution;
		this.minReal = minReal;
		this.maxReal = maxReal;
		this.minImaginary = minImaginary;
		this.maxImaginary = maxImaginary;
		this.maxIterations = maxIterations;
		this.radiusSquared = radiusSquared;
		this.madelbrotData = mandelCalc.calcMandelbrotSet(xResolution, 
				yResolution,
				minReal,
				maxReal,
				minImaginary,
				maxImaginary,
				maxIterations,
				radiusSquared);
		setChanged();
		notifyObservers();
	}
	
	public void resetModel() {
		this.xResolution = 800;
		this.yResolution = 800;
		this.minReal = MandelbrotCalculator.INITIAL_MIN_REAL;
		this.maxReal = MandelbrotCalculator.INITIAL_MAX_REAL;
		this.minImaginary = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
		this.maxImaginary = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
		this.maxIterations = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;
		this.radiusSquared = MandelbrotCalculator.DEFAULT_RADIUS_SQUARED;
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
	
	public int[][] getMadelbrotData() {
		return this.madelbrotData;
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
