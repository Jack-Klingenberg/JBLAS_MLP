package mlp.functions;

import org.jblas.DoubleMatrix;

public class Errors {
	public static DoubleMatrix mse(DoubleMatrix y, DoubleMatrix yp) throws Exception {
		int l = y.length; 
		DoubleMatrix inter = y.sub(yp).mul(y.sub(yp));
		
		return inter.transpose().mmul(DoubleMatrix.ones(inter.rows, inter.columns)).mul(1.0/l); 
	}
	
	public static DoubleMatrix mse_prime(DoubleMatrix y, DoubleMatrix yp) {
		return yp.sub(y).mul(2.0/yp.length);
	}
	
	public static void main(String[] args) throws Exception {
		DoubleMatrix yt = new DoubleMatrix(new double[][] {{1},{0}});
		DoubleMatrix yp = new DoubleMatrix(new double[][] {{0},{-1}});
		
		System.out.println(Errors.mse_prime(yt,yp));
	}
 	
}
