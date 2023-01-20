package mlp;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixBiFunction;
import mlp.functions.MatrixBiNorm; 
import mlp.layers.Layer; 

public class Network {
	private Layer[] layer_architecture;
	private MatrixBiNorm errorFunction; 
	private MatrixBiFunction errorGradFunction; 
	private boolean compiled; 
	
	public Network(Layer[] layers) {
		this.layer_architecture = layers.clone();
	}
	
	// Implement verbosity option 
	public void Compile(MatrixBiNorm erfOperator, MatrixBiFunction erfGradOperator) {
		this.errorFunction = erfOperator; 
		this.errorGradFunction = erfGradOperator; 
		
		this.compiled = true; 
	}
	
	public void fit(DoubleMatrix features, DoubleMatrix labels, double lr, int epochs) throws Exception {
		
		if(!this.compiled) {
			throw new Exception("Model not compiled. Process terminated.");
		}
		
		for(int i = 0; i < epochs; i++) {
			double error = 0;

			for(int j = 0; j < features.rows; j++) {
				DoubleMatrix x = features.getRow(j); 
				DoubleMatrix y = labels.getRow(j); 

				DoubleMatrix output = x.dup(); 

				for(Layer layer : this.layer_architecture) {
					output = layer.forward(output); 
				}

				error += this.errorFunction.apply(output, y); 

				DoubleMatrix output_error = this.errorGradFunction.apply(y, output); 

				for(int l = layer_architecture.length-1; l >= 0; l--) {
					output_error = layer_architecture[l].back(output_error, lr); 
				}
			}

			error /= features.rows; 
			System.out.println("Epoch " + i + ": error: " + error);

		}
	}
}