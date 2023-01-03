package mlp.layers;

import java.lang.reflect.InvocationTargetException;

import org.jblas.DoubleMatrix;

public class FlattenLayer implements Layer {
	private int w,h;
	
	public FlattenLayer(int x, int y) {
		this.w = x;
		this.h = y; 
	}

	public DoubleMatrix forward(DoubleMatrix v)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		return v.reshape(1, this.w * this.h); 
	}

	public DoubleMatrix back(DoubleMatrix output_error, double learning_rate)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		return output_error.reshape(this.w, this.h); 
	}
}
