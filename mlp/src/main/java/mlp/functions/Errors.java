package mlp.functions;

import org.jblas.DoubleMatrix;

public class Errors {
	public static double mse(DoubleMatrix y, DoubleMatrix yp) throws Exception {
		int l = y.length; 
		DoubleMatrix inter = y.sub(yp).mul(y.sub(yp));
		
		return inter.transpose().mmul(DoubleMatrix.ones(inter.rows, inter.columns)).mul(1.0/l).scalar(); 
	}
	
	public static DoubleMatrix mse_prime(DoubleMatrix y, DoubleMatrix yp) {
		return yp.sub(y).mul(2.0/yp.length);
	}
}
