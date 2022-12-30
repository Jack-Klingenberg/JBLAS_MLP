package mlp.layers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jblas.DoubleMatrix;

import mlp.functions.Activations;

public class ActivationLayer implements Layer {
	Method f, fprime; 
	DoubleMatrix input; 
	public ActivationLayer(Method activationfxn, Method activationfxngradient) {
		this.f = activationfxn;
		this.fprime = activationfxngradient; 		
	}
	
	public DoubleMatrix forward(DoubleMatrix v) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.input = v; 
		
		Activations obj = new Activations(); 
		return (DoubleMatrix) f.invoke(obj, new Object[] {v}); 
	}
	
	public DoubleMatrix back(DoubleMatrix output_error, float learning_rate) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Activations obj = new Activations(); 
		return ((DoubleMatrix) fprime.invoke(obj, new Object[] {this.input})).mmul(output_error); 
	}
}
