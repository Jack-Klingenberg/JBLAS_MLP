package mlp.functions.activations;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixFunction;

public class ReLUPrime implements MatrixFunction {
	
	public DoubleMatrix apply(DoubleMatrix x) {
		ReLU operator = new ReLU(); 
		return operator.apply(x).not().not(); 
	}
	
}
