package mlp.functions.errors;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixBiFunction;

public class MSEPrime implements MatrixBiFunction {

	public DoubleMatrix apply(DoubleMatrix y, DoubleMatrix yp) {
		return yp.sub(y).mul(2.0/yp.length);
	}
	
	public static void main(String[] args) {
		DoubleMatrix m1 = new DoubleMatrix(new double[][] {{0.488250, 0.509634, 0.510170, 0.501069, 0.490129, 0.513342, 0.479870, 0.498618, 0.509125, 0.500961}});
		DoubleMatrix m2 = new DoubleMatrix(new double[][] {{0,0,0,0,0,1,0,0,0,0}});
		
		MSEPrime op = new MSEPrime();
		
		System.out.println(op.apply(m1, m2));
		
	}

}
