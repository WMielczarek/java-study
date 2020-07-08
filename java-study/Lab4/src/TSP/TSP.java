package TSP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TSP {
    private ArrayList<int[]> matrix;

    public ArrayList<int[]> getMatrix() {
        return matrix;
    }

    public void setMatrix(ArrayList<int[]> matrix) {
        this.matrix = matrix;
    }

    public void readFromFile(String fileName) {
        ArrayList<int[]> loadedMatrix = new ArrayList<>();
        try {
            Scanner inputStream = new Scanner(new File(fileName));
            while(inputStream.hasNext()) {
                String data = inputStream.next();
                String[] values = data.split(",");
                int[] valuesInRow = new int[values.length];
                for(int i = 0; i <values.length; i++) {
                    valuesInRow[i] = Integer.parseInt(values[i]);
                }
                loadedMatrix.add(valuesInRow);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read from file");
        }
        this.matrix = loadedMatrix;
    }


    public void displayMatrix() {
        for (int i = 0; i < this.matrix.size(); i++) {
            for (int j = 0; j < this.matrix.size(); j++) {
                int[] foo = this.matrix.get(j);
                System.out.print(foo[i]+" ");
            }
            System.out.println();
        }
    }
}
