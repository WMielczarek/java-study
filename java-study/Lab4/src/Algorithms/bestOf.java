package Algorithms;


public class bestOf {
    private GreedyAlgorithm greedyAlgorithm;
    private RandomAlgorithm randomAlgorithm;
    private int bestResult;

    public bestOf(GreedyAlgorithm greedyAlgorithm, RandomAlgorithm randomAlgorithm) {
        this.greedyAlgorithm = greedyAlgorithm;
        this.randomAlgorithm = randomAlgorithm;
        this.bestResult = Integer.MAX_VALUE;
    }

    public GreedyAlgorithm getGreedyAlgorithm() {
        return greedyAlgorithm;
    }

    public void setGreedyAlgorithm(GreedyAlgorithm greedyAlgorithm) {
        this.greedyAlgorithm = greedyAlgorithm;
    }

    public RandomAlgorithm getRandomAlgorithm() {
        return randomAlgorithm;
    }

    public void setRandomAlgorithm(RandomAlgorithm randomAlgorithm) {
        this.randomAlgorithm = randomAlgorithm;
    }

    public int run() {
        int foo;
        foo = this.greedyAlgorithm.runAlgorithm();
        this.randomAlgorithm.runAlgorithm();
        if (foo < this.bestResult) {
            this.bestResult = foo;
        }
        return bestResult;
    }
}
