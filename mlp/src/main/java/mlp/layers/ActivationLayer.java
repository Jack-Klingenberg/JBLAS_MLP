package mlp.layers;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixFunction;

public class ActivationLayer implements Layer {
	MatrixFunction f, fprime; 
	public DoubleMatrix input; 
	public ActivationLayer(MatrixFunction fi, MatrixFunction fpi) {
		this.f = fi;
		this.fprime = fpi; 		
	}
	
	public DoubleMatrix forward(DoubleMatrix v) throws IllegalAccessException, IllegalArgumentException {
		this.input = v; 
		return (DoubleMatrix) this.f.apply(v); 
	}
	
	public DoubleMatrix back(DoubleMatrix output_error, double learning_rate) throws IllegalAccessException, IllegalArgumentException {
		
		return output_error.mul( ( (DoubleMatrix) fprime.apply(this.input) ) ); 
	}
	
	public static void main(String[] args) {
		System.out.println("Hello, world!");
	}
}
