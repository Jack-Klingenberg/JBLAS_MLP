package mlp.functions.activations;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixFunction;

public class SigmoidPrime implements MatrixFunction {
	
	public DoubleMatrix apply(DoubleMatrix M) {
		DoubleMatrix R = new DoubleMatrix(M.rows,M.columns);
		for(int i = 0; i < M.rows; i++) {
			for(int j = 0; j < M.columns; j++) {
				R.put(i,j, ( sigmoid_prime(M.get(i,j)) ));
			}
		}
		
		return R; 
	}
	
	private double sigmoid_prime(double x) {
	    return Math.exp(-x) / Math.pow((1 + Math.exp(-x)),2);
	}

}
