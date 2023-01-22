package mlp.functions.activations;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixFunction;

public class ReLU implements MatrixFunction {
	
	public DoubleMatrix apply(DoubleMatrix x) {
		if(x.isColumnVector()) {
			return DoubleMatrix.concatHorizontally(x, DoubleMatrix.zeros(x.rows,x.columns)).rowMaxs(); 
		} else if(x.isRowVector()) {
			return DoubleMatrix.concatVertically(x, DoubleMatrix.zeros(x.rows,x.columns)).columnMaxs(); 
		} else {
			DoubleMatrix r =  DoubleMatrix.zeros(x.rows,x.columns);  
			
			for(int i = 0; i < x.rows; i++) {
				for(int j = 0; j < x.columns; j++) {
					r.put(i,j, f(x.get(i,j))); 
				}
			}
			
			return r; 
		}
	}
	
	private static double f(double x) {
		if(x>=0) {
			return x; 
		}
		return 0; 
	}
	
	public static void main(String[] args) { 
		DoubleMatrix x = new DoubleMatrix(new double[][] {{-1,0,1}});
		ReLU operator = new ReLU();
		System.out.println(operator.apply(x));
	}

}
