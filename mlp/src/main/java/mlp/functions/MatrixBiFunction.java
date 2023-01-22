package mlp.functions;

import java.util.function.BiFunction;

import org.jblas.DoubleMatrix;

public interface MatrixBiFunction extends BiFunction<DoubleMatrix, DoubleMatrix, DoubleMatrix> {}
