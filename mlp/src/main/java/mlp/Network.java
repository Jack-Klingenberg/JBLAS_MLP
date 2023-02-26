package mlp;

import java.lang.reflect.InvocationTargetException;

import org.jblas.DoubleMatrix;

import mlp.functions.MatrixBiFunction;
import mlp.functions.MatrixBiNorm;
import mlp.functions.errors.MSE;
import mlp.jblasextensions.MatrixTools;
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
	public void compile(MatrixBiNorm erfOperator, MatrixBiFunction erfGradOperator) {
		this.errorFunction = erfOperator; 
		this.errorGradFunction = erfGradOperator; 
		
		this.compiled = true; 
	}
	
	public DoubleMatrix forward(DoubleMatrix x) {
		DoubleMatrix output = x.dup(); 

		for(Layer layer : this.layer_architecture) {
			try {
				output = layer.forward(output);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		return output; 
	}
	
	public DoubleMatrix back(DoubleMatrix pred, DoubleMatrix acc) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException{
		DoubleMatrix output_error = this.errorGradFunction.apply(acc, pred); 
		
		for(int l = layer_architecture.length-1; l >= 0; l--) {
			output_error = layer_architecture[l].back(output_error, .01);
		}
		
		return output_error; 
		
		
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
				
				error += this.errorFunction.apply(y, output); 
				

				DoubleMatrix output_error = this.errorGradFunction.apply(y, output); 

				for(int l = layer_architecture.length-1; l >= 0; l--) {
					output_error = layer_architecture[l].back(output_error, lr); 
				}
				
			}
			
			//System.out.println("Epoch " + (i+1) + ": error: " + error/((double) features.rows ));
			verboseTest(features,labels);

		}
		
	}
	
	public DoubleMatrix predict(DoubleMatrix feature) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		DoubleMatrix output = feature.dup();
		
		for(Layer layer : this.layer_architecture) {
			output = layer.forward(output); 
		}
		
		return output; 
	}
	
	public void verboseTest(DoubleMatrix features, DoubleMatrix labels) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		double indx = 0.0; 


		for(int i = 0; i < features.rows; i++) {
			DoubleMatrix x = features.getRow(i);
			DoubleMatrix pred = this.forward(x);
			if(pred.argmax() == labels.getRow(i).argmax()) {
				indx++; 
			}
			
		}
		System.out.println("Pcntg: " + (indx/features.rows));
	}
	
	
}