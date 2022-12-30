package mlp;
import org.jblas.DoubleMatrix; 
import mlp.jblasextensions.*;

public class Client {
	public static void main(String[] args) {
		DoubleMatrix dm1 = new DoubleMatrix(new double[][] {{1 ,1}, {1,1}});
		DoubleMatrix dm2 = new DoubleMatrix(new double[][] {{2 ,1}, {3,4}});

		
		System.out.print(MatrixTools.tile(dm2, 2));
	}
	
}

