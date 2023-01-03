package mlp;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import mlp.layers.Layer;

public class Network {
	private LinkedList<Layer> layer_architecture;
	private Method u; // functionality not yet implemented
	private double lr; 
	
	public Network(Layer[] layers) {
		for(Layer layer : layers) {
			layer_architecture.add(layer); 
		}	
	}
	public void fit(ArrayList<ArrayList<String>> data) {
		
		
	}
	

}
