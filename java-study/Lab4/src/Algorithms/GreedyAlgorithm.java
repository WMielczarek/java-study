package Algorithms;

import TSP.TSP;

import java.util.ArrayList;
import java.util.Scanner;

public class GreedyAlgorithm {
    private TSP tsp;
    private int cost;
    private ArrayList<Integer> visitedNodes;

    public GreedyAlgorithm() {
        this.cost = 0;
        this.visitedNodes = new ArrayList<>();
    }

   public  GreedyAlgorithm(TSP tsp) {
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

    private void addToVisitedNodes(int node) {
        this.visitedNodes.add(node);
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

    public int getClosesNeighbor (int[] neighbors) {
        int closesNode = 0;
        int min = Integer.MAX_VALUE;
        try {
            for(int i = 0; i < neighbors.length; i++) {
                if(i == neighbors.length) {
                    closesNode = 0;
                    min = neighbors[0];
                    continue;
                }
                if(neighbors[i] == -1) {
                    continue;
                }
                if(checkIfWasUsed(i)) {
                    continue;
                }
                if(neighbors[i] < min) {
                    closesNode = i;
                    min = neighbors[i];
                }
            }

        } catch (Exception e) {
            System.out.println("Cos poszlo nie tak w szukaniu najblizszego sasiada");
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
            currentNode = this.getClosesNeighbor(neighbors);
            this.visitedNodes.add(currentNode);
            cost += neighbors[currentNode];
            }
        return cost;
    }

}
