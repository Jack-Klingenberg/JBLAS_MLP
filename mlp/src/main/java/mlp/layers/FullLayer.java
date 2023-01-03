package mlp.layers;

import org.jblas.DoubleMatrix;

public class FullLayer implements Layer {
	private int isize,osize;
	
	private DoubleMatrix W;
	private DoubleMatrix B; 
	
	private DoubleMatrix input; 
	
	public FullLayer(int isize, int osize) {
		this.isize = isize; 
		this.osize = osize; 
		
		W = DoubleMatrix.randn(isize,osize).mul(1.0/(osize+isize));
		B = DoubleMatrix.randn(1,osize).mul(Math.sqrt(1.0/(osize+isize)));
	}

	public DoubleMatrix forward(DoubleMatrix input) {
		this.input = input; 
		
		return this.input.mmul(this.W).add(this.B);
	}

	public DoubleMatrix back(DoubleMatrix output_error, double learning_rate) {
		DoubleMatrix i_error = output_error.mmul(this.W.transpose());
		DoubleMatrix w_error = this.input.transpose().mmul(output_error);
		
		this.W = this.W.sub(w_error.mul(learning_rate));
		this.B = this.B.sub(output_error.mul(learning_rate));
		
		
		return i_error;
	}

	public int getOsize() {
		return this.osize; 
	}

	public int getIsize() {
		return isize;
	}
}
