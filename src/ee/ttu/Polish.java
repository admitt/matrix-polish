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

        do {
            previous = current;
            current = new Matrix(subtractMedians(current, steps % 2 == 0));
        } while (!previous.equals(current));

        return current;
    }

    private double[][] subtractMedians(Matrix current, boolean row) {
        int size = row ? current.rowCount() : current.columnCount();
        double[] medians = new double[size];
        for (int i = 0; i < size; i++) {
            medians[i] = median(row ? current.getRow(i) : current.getColumn(i));
        }

        double[][] matrix = new double[current.rowCount()][];
        for (int i = 0; i < current.rowCount(); i++) {
            matrix[i] = new double[current.columnCount()];
            for (int j = 0; j < current.columnCount(); j++) {
                matrix[i][j] = current.getValue(i, j) - medians[row ? i : j];
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
                ? (sequence[middleElement - 1] + sequence[middleElement]) / 2
                : sequence[((int) Math.floor(middleElement))];
    }
}
