package Algorithms;

import TSP.TSP;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class RandomAlgorithm {
    private TSP tsp;
    private int cost;
    private ArrayList<Integer> visitedNodes;

    public RandomAlgorithm(TSP tsp) {
        this.tsp = tsp;
        this.visitedNodes = new ArrayList<>();
        this.cost = 0;
    }

    public TSP getTsp() {
        return tsp;
    }

    public void setTsp(TSP tsp) {
        this.tsp = tsp;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ArrayList<Integer> getVisitedNodes() {
        return visitedNodes;
    }

    public void setVisitedNodes(ArrayList<Integer> visitedNodes) {
        this.visitedNodes = visitedNodes;
    }

    private boolean checkIfWasUsed(int nodeToCheck) {
        try {
            for(int i = 0; i < this.visitedNodes.size(); i++) {
                if(visitedNodes.get(i) == nodeToCheck) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Cos poszlo nie tak w sprawdzaniu czy zostal uzyty");
        }
        return false;
    }

    private int getRandomNode(int[] neighbors, int i) {
        int generatedInt = ThreadLocalRandom.current().nextInt(i, neighbors.length);
        return generatedInt;
    }

    public int getNextNode (int[] neighbors) {
        int closesNode = 0;
        try {
            for(int i = 0; i < neighbors.length; i++) {
                closesNode = getRandomNode(neighbors, i);
                if(i+1 == neighbors.length) {
                    closesNode = 0;
                    continue;
                }
                if(neighbors[i] == -1) {
                    continue;
                }
                if(checkIfWasUsed(closesNode)) {
                    continue;
                }
                else {
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Cos poszlo nie tak w generowaniu nastepnego sasiada");
        }
        return closesNode;
    }

    public int runAlgorithm() {
        int currentNode = 0;
        this.cost = 0;
        int numberOfNodesInPath = tsp.getMatrix().size() + 1;
        this.visitedNodes.add(currentNode);
        while(numberOfNodesInPath != visitedNodes.size()) {
            int[] neighbors = tsp.getMatrix().get(currentNode);
            currentNode = this.getNextNode(neighbors);
            this.visitedNodes.add(currentNode);
            cost += neighbors[currentNode];
        }
        return cost;
    }
}

