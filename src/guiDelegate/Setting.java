package guiDelegate;

public class Setting {
    protected double minReal;
    protected double maxReal;
    protected double minImaginary;
    protected double maxImaginary;
    protected int maxIterations;
    protected double radiusSquared;
	protected int frameHeight;
	protected int frameWidth;
	
	public Setting(int frameHeight, int frameWidth, double minReal, double maxReal, double minImaginary
			, double maxImaginary, int maxIterations, double radiusSquared) {
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		this.minReal = minReal;
		this.maxReal = maxReal;
		this.minImaginary = minImaginary;
		this.maxImaginary = maxImaginary;
		this.maxIterations = maxIterations;
		this.radiusSquared = radiusSquared;
	}
}
