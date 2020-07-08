package TSP;

import java.util.Scanner;
import Algorithms.GreedyAlgorithm;
import Algorithms.RandomAlgorithm;
import Algorithms.bestOf;

public class Main {

    public static void main(String[] args) {
        TSP tsp = new TSP();
        int testMatrix[][] = {
                {-1,2,2,1},
                {2,-1,2,3},
                {2,2,-1,4},
                {1,3,4,-1}
        };
        System.out.println("Podaj nazwe pliku: ");
        Scanner reader = new Scanner(System.in);
        String fileName = reader.nextLine();
        tsp.readFromFile(fileName);
        tsp.displayMatrix();

        GreedyAlgorithm test = new GreedyAlgorithm(tsp);
        RandomAlgorithm testRandom = new RandomAlgorithm(tsp);
        bestOf testBestOf = new bestOf(test,testRandom);
        int foo = testBestOf.run();
        System.out.println(foo);

    }

}
