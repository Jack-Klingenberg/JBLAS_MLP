package mlp.layers;

import org.jblas.DoubleMatrix;

public class SoftmaxLayer implements Layer {

	@Override
	public DoubleMatrix forward(DoubleMatrix v)
			throws IllegalAccessException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleMatrix back(DoubleMatrix output_error, double learning_rate)
			throws IllegalAccessException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
//	private int isize; 
//	
//	private DoubleMatrix input; 
//	private DoubleMatrix output; 
//	
//	public SoftmaxLayer(int inputsize) {
//		this.isize = inputsize; 
//	}
//
//	public DoubleMatrix forward(DoubleMatrix v) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		this.input = v;
//		
//		DoubleMatrix t1 = MatrixFunctions.exp(this.input);
//		
//		int m = t1.getRows();
//		int n = t1.getColumns(); 
//		
//		DoubleMatrix t2 = t1.transpose().mul(DoubleMatrix.ones(m,1)).transpose().rdiv(DoubleMatrix.ones(m,n));
//				
//		return t1.mul(t2);
//	}
//
//	public DoubleMatrix back(DoubleMatrix output_error, double learning_rate)
//			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
////		
////		DoubleMatrix input_error = DoubleMatrix.zeros(output_error.getRows(),output_error.getColumns());
////		DoubleMatrix out = MatrixTools.tile(output_error, new int[]{this.input.getRows(), this.input.getColumns()}); 
////		
//		return null;
//	}

}