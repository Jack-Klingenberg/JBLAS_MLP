package mlp;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.jblas.DoubleMatrix;

import mlp.jblasextensions.*;
import mlp.layers.*;
import mlp.functions.*; 

public class Client {
	public static void main(String[] args) throws IOException {
		ArrayList<ArrayList<String>> csv = FileTools.loadLocalCSV("/Users/jackklingenberg/C/JBLAS_MLP/test/train.csv",",",false);
		DoubleMatrix parsed = FileTools.parseCSV(csv); 
		
		Layer[] architecture = {
				new FlattenLayer(28,28),
				new FullLayer(28*28, 128),
				new ActivationLayer(new Method(),Activations.relu_prime)
				
		}; 
		
		
		Network network = new Network(null); 
		
		
		
		
		
	}
}

  