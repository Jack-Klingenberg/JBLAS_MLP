package mlp.functions;

import java.lang.Math;

import org.jblas.DoubleMatrix; 

public class Activations {
	public double sigmoid(double x) {
		return 1.0/(1.0 + Math.exp(-x));
	}
	public double sigmoid_prime(double x) {
	    return Math.exp(-x) / Math.pow((1 + Math.exp(-x)),2);
	}
	public double tanh(double x) {
		return Math.tanh(x);
		
	}
	public double tanh_prime(double x) {
		return 1-Math.pow(Math.tanh(x),2);
		
	}
	
	public DoubleMatrix relu(DoubleMatrix x) throws Exception {
		if(x.isColumnVector()) {
			DoubleMatrix M = DoubleMatrix.concatHorizontally(x, DoubleMatrix.zeros(x.rows,x.columns)); 
			
			return M.rowMaxs(); 
		} else {
			throw new Exception("Fatal error: column vector expected but not recieved.");
		}
	}
	
	public DoubleMatrix relu_prime(DoubleMatrix x) throws Exception {
		return this.relu(x).not().not(); 
	}
}