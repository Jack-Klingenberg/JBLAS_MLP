package mlp.tests;

import org.jblas.DoubleMatrix;

import mlp.functions.activations.Sigmoid;
import mlp.functions.activations.SigmoidPrime;
import mlp.functions.errors.MSE;
import mlp.functions.errors.MSEPrime;
import mlp.layers.ActivationLayer;
import mlp.layers.FullLayer;
import mlp.layers.Layer;
import mlp.structures.Network;

public class NonSeperableTest {
	public static void main(String[] args) throws Exception {
		DoubleMatrix X_train = new DoubleMatrix( new double[][] {{0,1},{1,0},{0,0},{1,1}} ); 
		DoubleMatrix y_train = new DoubleMatrix( new double[][] {{0},{1},{1},{0}} );
		
		Layer[] arch = {
				new FullLayer(2,2),	
				new ActivationLayer(new Sigmoid(),new SigmoidPrime()),
				new FullLayer(2,1),
				new ActivationLayer(new Sigmoid(), new SigmoidPrime())
		};
		
		((FullLayer) arch[0]).W = new DoubleMatrix(new double[][] {{.01,.02},{.03,.04}}); 
		((FullLayer) arch[0]).B = new DoubleMatrix(new double[][] {{.01},{.02}}); 
		((FullLayer) arch[2]).W = new DoubleMatrix(new double[][] {{.01},{.02}}); 
		((FullLayer) arch[2]).B = new DoubleMatrix(new double[][] {{.01}}); 

		
		Network network = new Network(arch);
		network.compile(new MSE(), new MSEPrime());

		try {
			network.fit(X_train, y_train, .1, 5000);
	
			System.out.println("+++++");
			for(int i = 0; i < X_train.rows; i++) {
				System.out.println("X: " + X_train.getRow(i) + "pred: " + network.predict(X_train.getRow(i)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
