package mlp.functions;

import org.jblas.DoubleMatrix;

public class Errors {
	public DoubleMatrix mse(DoubleMatrix y, DoubleMatrix yp) throws Exception {
		int l = y.length; 
		DoubleMatrix r = y.sub(yp).transpose().mmul(y.sub(yp))
				.mul(1/l).get(new int[] {0,0}); 
		
		if(r.isScalar()) {
			return r; 
		} else { throw new Exception("Fatal error: unexpected shape result when computing MSE"); }
	}
	
	public DoubleMatrix mse_prime(DoubleMatrix y, DoubleMatrix yp) {
		return yp.sub(y).mul(2.0/yp.length);
		
	}
 	
}
