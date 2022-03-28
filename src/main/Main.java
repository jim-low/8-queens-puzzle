package main;

import java.text.NumberFormat;
import java.util.Random;

import hillClimbing.HillClimbing;
import simulatedAnnealing.SimulatedAnnealing;

public class Main {
    public static int numberOfRuns = 10;
    public static Queen[][] initialStates = {
        {new Queen(7, 0), new Queen(1, 1), new Queen(0, 2), new Queen(4, 3), new Queen(6, 4), new Queen(1, 5), new Queen(0, 6), new Queen(4, 7)},
        {new Queen(7, 0), new Queen(0, 1), new Queen(2, 2), new Queen(6, 3), new Queen(1, 4), new Queen(2, 5), new Queen(3, 6), new Queen(7, 7)},
        {new Queen(3, 0), new Queen(4, 1), new Queen(7, 2), new Queen(0, 3), new Queen(2, 4), new Queen(5, 5), new Queen(7, 6), new Queen(4, 7)},
        {new Queen(7, 0), new Queen(5, 1), new Queen(2, 2), new Queen(0, 3), new Queen(1, 4), new Queen(1, 5), new Queen(0, 6), new Queen(1, 7)},
        {new Queen(3, 0), new Queen(3, 1), new Queen(1, 2), new Queen(5, 3), new Queen(7, 4), new Queen(4, 5), new Queen(0, 6), new Queen(0, 7)},
        {new Queen(4, 0), new Queen(1, 1), new Queen(2, 2), new Queen(7, 3), new Queen(3, 4), new Queen(6, 5), new Queen(2, 6), new Queen(1, 7)},
        {new Queen(7, 0), new Queen(3, 1), new Queen(7, 2), new Queen(1, 3), new Queen(0, 4), new Queen(1, 5), new Queen(1, 6), new Queen(7, 7)},
        {new Queen(6, 0), new Queen(1, 1), new Queen(0, 2), new Queen(0, 3), new Queen(3, 4), new Queen(4, 5), new Queen(5, 6), new Queen(3, 7)},
        {new Queen(6, 0), new Queen(6, 1), new Queen(3, 2), new Queen(2, 3), new Queen(6, 4), new Queen(4, 5), new Queen(7, 6), new Queen(0, 7)},
        {new Queen(0, 0), new Queen(0, 1), new Queen(2, 2), new Queen(4, 3), new Queen(4, 4), new Queen(5, 5), new Queen(3, 6), new Queen(7, 7)},
    };

    public static void main(String[] args) {
        runHillClimbing();
        System.out.println();
        runSimulatedAnnealing();
    }

    public static Queen[] generateBoard() {
        Queen[] start = new Queen[8];
        Random gen = new Random();

        for(int i = 0; i < 8; i++){
            start[i] = new Queen(gen.nextInt(8), i);
        }
        // System.out.println(start);

        return start;
    }

    public static void runHillClimbing() {
        int hillClimbNodes = 0;
        int hillClimbSuccesses = 0;

        for(int i = 0; i < initialStates.length; i++){
            HillClimbing hillClimber = new HillClimbing(initialStates[i]);
            long startTime = System.currentTimeMillis();
            Node hillSolved = hillClimber.hillClimbing();
            long endTime = System.currentTimeMillis();
            Runtime runtime = Runtime.getRuntime();

            // found answer if heuristic == 0
            if(hillSolved.getHn() == 0) {
                long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
                System.out.println("Hill Climbing Solved:\n" + hillSolved);
                System.out.println("Memory Used: " + memoryUsed);
                System.out.println("Elapsed Time in milli seconds: " + (endTime - startTime) + "\n");
                hillClimbSuccesses++;
            } else {
                System.out.println("Unable to solve :(");
                System.out.println();
            }

            hillClimbNodes += hillClimber.getNodesGenerated();
        }

        System.out.println("Hill climb successes: " + hillClimbSuccesses);
        System.out.println();

        double hillClimbPercent = (double)hillClimbSuccesses/(double)numberOfRuns;
        System.out.println(hillClimbPercent);

        NumberFormat fmt = NumberFormat.getPercentInstance();

        System.out.println("Hill climbing:\nNodes: " + hillClimbNodes);
        System.out.println("Percent successes: " + fmt.format(hillClimbPercent));
    }

    public static void runSimulatedAnnealing() {
        int annealNodes = 0;
        int annealSuccesses = 0;

        for(int i = 0; i < initialStates.length; i++) {
            SimulatedAnnealing anneal = new SimulatedAnnealing(initialStates[i]);

            long startTime = System.currentTimeMillis();
            Node annealSolved = anneal.simulatedAnneal(28, 0.0001);
            long endTime = System.currentTimeMillis();
            Runtime runtime = Runtime.getRuntime();

            if (annealSolved.getHn() == 0) {
                long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
                System.out.println("Anneal Solved:\n" + annealSolved);
                System.out.println("Memory Used: " + memoryUsed);
                System.out.println("Elapsed Time in milli seconds: " + (endTime - startTime) + "\n");
                annealSuccesses++;
            } else {
                System.out.println("Unable to solve :(");
                System.out.println();
            }

            annealNodes += anneal.getNodesGenerated();
        }

        System.out.println("Simulated Annealing successes: " + annealSuccesses);
        System.out.println();

        double annealPercent = (double)(annealSuccesses/numberOfRuns);
        NumberFormat fmt = NumberFormat.getPercentInstance();

        System.out.println("Simulated Annealing:\nNodes: " + annealNodes);
        System.out.println("Percent successes: " + fmt.format(annealPercent));
    }
}
