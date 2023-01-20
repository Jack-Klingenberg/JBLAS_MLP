package mlp.functions.activations;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixFunction;

public class ReLU implements MatrixFunction {
	public DoubleMatrix apply(DoubleMatrix x) {
		return DoubleMatrix.concatHorizontally(x, DoubleMatrix.zeros(x.rows,x.columns)).rowMaxs(); 
	}

}
