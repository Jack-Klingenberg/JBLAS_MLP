package mlp;

import java.io.IOException;
import java.util.ArrayList;

import org.jblas.DoubleMatrix;

import mlp.functions.activations.ReLU;
import mlp.functions.activations.ReLUPrime;
import mlp.functions.activations.Sigmoid;
import mlp.functions.activations.SigmoidPrime;
import mlp.functions.errors.MSE;
import mlp.functions.errors.MSEPrime;
import mlp.jblasextensions.*;
import mlp.layers.*;

public class Client {
	public static void main(String[] args) throws IOException {
		// Load the file
		ArrayList<ArrayList<String>> csv = FileTools.loadLocalCSV("/Users/jackklingenberg/C/JBLAS_MLP/test/train.csv",",",false);
		System.out.println("CSV Loaded. Beginning to parse...");
		
		// Do some file pre-processing 
		double[][] featuresa = new double[csv.size()][csv.get(0).size()-1]; 
		double[][] labelsa = new double[csv.size()][10]; 
		
		
		for(int i = 0; i < 1000/*csv.size()*/; i++) {
			for(int j = 0; j < csv.get(0).size(); j++) {
				if(j>=28*28) {
					labelsa[i] = MatrixTools.toCategorical(Double.parseDouble(csv.get(i).get(j)));
				} else {
					featuresa[i][j] = Double.parseDouble(csv.get(i).get(j))/255.0; 
				}
			}
		}
		
		// Convert features, labels to DoubleMatrix
		DoubleMatrix features = new DoubleMatrix(featuresa);
		DoubleMatrix labels  = new DoubleMatrix(labelsa); 
		
		System.out.println(features.getRow(0));		
		
		
		Layer[] architecture = {
				new FullLayer(28*28, 128),	
				new ActivationLayer(new ReLU(),new ReLUPrime()),
				new FullLayer(128,10),
				new ActivationLayer(new Sigmoid(), new SigmoidPrime())
		}; 
		
		
		Network network = new Network(architecture);
		network.compile(new MSE(), new MSEPrime()); 
		
		try {
			network.fit(features, labels, .1, 40);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

  