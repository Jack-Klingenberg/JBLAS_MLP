package mlp.jblasextensions;

import java.util.Arrays;
import java.util.Random;

import org.jblas.DoubleMatrix; 

public class MatrixTools {
	public static DoubleMatrix exp(DoubleMatrix M) {
		return null;	
	}
	
	
	public static DoubleMatrix exp(DoubleMatrix M,int itter) {
		DoubleMatrix R = DoubleMatrix.ones(M.rows, M.columns); 
		double fac = 1; 
		DoubleMatrix T = DoubleMatrix.zeros(M.rows, M.columns);
		for(int i = 0; i < itter; i++) {
			T = T.add(R).div(fac); 
			
			R.muliColumnVector(R);
			fac *= (i+1); 
		}
		
		return T; 
	}
	
	public static DoubleMatrix columnProduct(DoubleMatrix x, DoubleMatrix scalars) {
		int l = x.getRows();
		DoubleMatrix r = x.getColumn(0).mul(scalars.get(0,0)); 
		
		for(int i = 1; i < l; i++) {
			r = DoubleMatrix.concatHorizontally(r,x.getColumn(0).mul(scalars.get(0,i)));
		}
		
		return r; 
	}
	public static DoubleMatrix columnSum(DoubleMatrix x, DoubleMatrix scalars) {
		int l = x.getRows();
		DoubleMatrix r = x.getColumn(0).add(scalars.get(0,0)); 
		
		for(int i = 1; i < l; i++) {
			r = DoubleMatrix.concatHorizontally(r,x.getColumn(0).mul(scalars.get(0,i)));
		}
		
		return r; 
	}
	
	public static DoubleMatrix tile(DoubleMatrix x, int n) {
		DoubleMatrix y = x.dup();
		final DoubleMatrix ys = x.dup();  
		
		for(int i = 1; i <n; i++) {
			y = DoubleMatrix.concatHorizontally(y,ys);
		}
		
		return y; 
	}
	

	
	public static DoubleMatrix pow(DoubleMatrix M, int pow) {
		if(pow == 0) {
			return DoubleMatrix.ones(M.rows,M.columns); 
		}
		
		DoubleMatrix X = M.dup(); 
		MatrixTools.powIP(X,pow);
		
		return X; 
	}
	
	public static DoubleMatrix powIP(DoubleMatrix M, int pow) {
		for(int i = 0; i < pow; i++) { M.muliColumnVector(M); }
		return M; 
	}

	public static DoubleMatrix[][] zip(DoubleMatrix x, DoubleMatrix y) {
		DoubleMatrix[][] r = new DoubleMatrix[x.rows][2]; 
		for(int i = 0; i < x.rows; i++) {
			r[i][0] = x.getRow(i);
			r[i][1] = y.getRow(i); 
		}

		return r; 
	}


	public static double[] toCategorical(double d) {
		double[] r = new double[10];
		r[(int)(d)]=1;
		return r; 
	}
	
	public static void printFirst(int n, DoubleMatrix m) {
		for(int i = 0; i < n; i++) {
			System.out.println(m.getRow(i));
		}
	} 
}
