package mlp.layers;

import java.lang.reflect.InvocationTargetException;

import org.jblas.DoubleMatrix;

public interface Layer {
	public DoubleMatrix forward(DoubleMatrix v) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	public DoubleMatrix back(DoubleMatrix output_error, float learning_rate) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException; 
}
