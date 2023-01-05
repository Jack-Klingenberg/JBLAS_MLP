package mlp;

import org.jblas.DoubleMatrix;

import mlp.layers.Layer;
import mlp.functions.Errors; 

public class Network {
	private Layer[] layer_architecture;
	
	public Network(Layer[] layers) {
		this.layer_architecture = layers.clone();
	}

	public void fit(DoubleMatrix features, DoubleMatrix labels, double lr, int epochs) throws Exception {
		for(int i = 0; i < epochs; i++) {
			double error = 0;

			for(int j = 0; j < features.rows; j++) {
				DoubleMatrix x = features.getRow(j); 
				DoubleMatrix y = labels.getRow(j); 

				DoubleMatrix output = x.dup(); 

				for(Layer layer : this.layer_architecture) {
					output = layer.forward(output); 
				}

				error += Errors.mse(output, y); 

				DoubleMatrix output_error = Errors.mse_prime(y, output); 

				for(int l = layer_architecture.length-1; l >= 0; l--) {
					output_error = layer_architecture[l].back(output_error, lr); 
				}
			}

			error /= features.rows; 
			System.out.println("Epoch " + i + ": error: " + error);

		}
	}
}