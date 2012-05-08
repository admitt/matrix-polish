package ee.ttu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InputMatrixReader {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> rows = new ArrayList<String>();
        try {
            System.out.println("Input first row, separate integer numbers by space!");

            while (true) {
                String s = reader.readLine();
                if (s.isEmpty()) {
                    break;
                }
                rows.add(s);
                System.out.println("Input next row or press enter!");
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(new Polish(new Matrix(rows)).polish());
    }
}
