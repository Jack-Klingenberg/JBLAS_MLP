package mlp.functions;

import java.util.function.BiFunction;

import org.jblas.DoubleMatrix;

public interface MatrixBiNorm extends BiFunction<DoubleMatrix, DoubleMatrix, Double> {}
