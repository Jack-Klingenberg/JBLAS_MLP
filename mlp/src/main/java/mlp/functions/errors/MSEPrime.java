package mlp.functions.errors;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixBiFunction;

public class MSEPrime implements MatrixBiFunction {

	public DoubleMatrix apply(DoubleMatrix y, DoubleMatrix yp) {
		return yp.sub(y).mul(2.0/yp.length);
	}

}
