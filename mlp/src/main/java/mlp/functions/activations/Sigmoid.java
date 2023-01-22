package mlp.functions.activations;

import org.jblas.DoubleMatrix;
import mlp.functions.MatrixFunction;

public class Sigmoid implements MatrixFunction {

	public DoubleMatrix apply(DoubleMatrix M) {
		DoubleMatrix R = new DoubleMatrix(M.rows,M.columns);
		for(int i = 0; i < M.rows; i++) {
			for(int j = 0; j < M.columns; j++) {
				R.put(i,j, ( sigmoid(M.get(i,j)) ));
			}
		}
		
		return R;
	}
	
	private double sigmoid(double x) {
		return 1.0/(1.0 + Math.exp(-x));
	}
}