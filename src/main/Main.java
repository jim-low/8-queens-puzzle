package main;

import java.text.NumberFormat;
import java.util.Random;

import hillClimbing.HillClimbing;
import simulatedAnnealing.SimulatedAnnealing;

public class Main {
    public static int numberOfRuns = 12;

    public static void main(String[] args) {
        runHillClimbing();

        System.out.println();

        runSimulatedAnnealing();
    }

    public static Queen[] generateBoard(){
        Queen[] start = new Queen[8];
        Random gen = new Random();

        for(int i = 0; i < 8; i++){
            start[i] = new Queen(gen.nextInt(8), i);
        }

        return start;
    }

    public static void runHillClimbing() {
        int hillClimbNodes = 0;
        int hillClimbSuccesses = 0;

        for(int i = 0; i < numberOfRuns; i++){
            System.out.println("this is " + i + " time running");
            Queen[] startBoard = Main.generateBoard();
            for (int j = 0; j < startBoard.length; ++j) {
                System.out.println(startBoard[j]);
            }
            System.out.println();

            HillClimbing hillClimber = new HillClimbing(startBoard);

            Node hillSolved = hillClimber.hillClimbing();

            // found answer if heuristic == 0
            if(hillSolved.getHn() == 0){
                System.out.println("Hill Climbing Solved:\n"+hillSolved);
                hillClimbSuccesses++;
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

        for(int i = 0; i < numberOfRuns; i++){
            Queen[] startBoard = Main.generateBoard();

            SimulatedAnnealing anneal = new SimulatedAnnealing(startBoard);

            Node annealSolved = anneal.simulatedAnneal(28, 0.0001);

            if(annealSolved.getHn() == 0){
                System.out.println("Anneal Solved:\n"+annealSolved);
                annealSuccesses++;
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
