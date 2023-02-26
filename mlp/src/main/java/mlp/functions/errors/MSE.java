package mlp.functions.errors;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixBiNorm;
import mlp.jblasextensions.MatrixTools;

public class MSE implements MatrixBiNorm {

	public Double apply(DoubleMatrix y, DoubleMatrix yp) {
		int l = y.length; 
		DoubleMatrix inter = y.sub(yp).mul(y.sub(yp));
		
		return (Double)(inter.transpose().mmul(DoubleMatrix.ones(inter.rows, inter.columns)).mul(1.0/l).scalar());
	}
	
	public static void main(String[] args) {
		double n = 4; 
		DoubleMatrix y = new DoubleMatrix(MatrixTools.toCategorical(5));
		DoubleMatrix yp = new DoubleMatrix(new double[][] {{0.67468466,0.73572405,0.62543767,0.68486805,0.56778443,0.5313056,0.30245558,0.41459637,0.56481883,0.65734067}});
		
		MSE operator = new MSE(); 
		
		System.out.println(operator.apply(y,yp));
		
		
		
		
	}

}
