package mlp.functions.errors;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixBiNorm;

public class MSE implements MatrixBiNorm {

	public Double apply(DoubleMatrix y, DoubleMatrix yp) {
		int l = y.length; 
		DoubleMatrix inter = y.sub(yp).mul(y.sub(yp));
		
		return (Double)(inter.transpose().mmul(DoubleMatrix.ones(inter.rows, inter.columns)).mul(1.0/l).scalar());
	}

}
