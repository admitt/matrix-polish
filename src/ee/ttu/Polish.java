package ee.ttu;

import java.util.Arrays;

public class Polish {
    private Matrix m;

    public Polish(Matrix m) {
        this.m = m;
    }

    public Matrix polish() {
        Matrix previous, current = m;
        int steps = 0;
        while(true) {
            previous = current;
            current = new Matrix(steps % 2 == 0
                    ? subtractRowMedians(current)
                    : subtractColumnMedians(current));

            if (previous.equals(current)) {
                break;
            }
        }

        return current;
    }

    private double[][] subtractColumnMedians(Matrix current) {
        double[] medians = new double[current.columnCount()];
        for (int i = 0; i < current.columnCount(); i++) {
            medians[i] = median(current.getColumn(i));
        }

        double[][] matrix = new double[current.rowCount()][];
        for (int i = 0; i < current.rowCount(); i++) {
            matrix[i] = new double[current.columnCount()];
            for (int j = 0; j < current.columnCount(); j++) {
                matrix[i][j] = current.getValue(i, j) - medians[j];
            }
        }
        return matrix;
    }

    private double[][] subtractRowMedians(Matrix current) {
        double[] medians = new double[current.rowCount()];
        for (int i = 0; i < current.rowCount(); i++) {
            medians[i] = median(current.getRow(i));
        }

        double[][] matrix = new double[current.rowCount()][];
        for (int i = 0; i < current.rowCount(); i++) {
            matrix[i] = new double[current.columnCount()];
            for (int j = 0; j < current.columnCount(); j++) {
                matrix[i][j] = current.getValue(i, j) - medians[i];
            }
        }
        return matrix;
    }

    double median(double[] sequence) {
        if (sequence.length == 0) {
            return 0.0;
        }
        
        Arrays.sort(sequence);
        int middleElement = sequence.length / 2;

        return sequence.length % 2 == 0
            ? (sequence[middleElement-1] + sequence[middleElement]) / 2
            : sequence[((int) Math.floor(middleElement))];
    }
}
