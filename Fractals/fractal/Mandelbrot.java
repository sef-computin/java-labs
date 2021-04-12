package fractal;

import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator{


	public static final int MAX_ITERATIONS = 2000;

	public int numIterations(double x, double y){
		double cx = x;
		double cy = y;
		int i = 0;
		for (i=0;i<MAX_ITERATIONS; i++){
			double nx = x*x - y*y + cx;
			double ny = 2*x*y + cy;

			x = nx;
			y = ny;

			if (x*x + y*y > 4) break;
		}
		return i<MAX_ITERATIONS?i:-1;
	}

	public void getInitialRange(Rectangle2D.Double range){
		range.x = -2;
		range.y = -1.5;
		range.width = 3;
		range.height = 3;
	}



}
