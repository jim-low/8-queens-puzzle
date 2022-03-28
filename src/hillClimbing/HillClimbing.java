package hillClimbing;

import java.util.ArrayList;
import java.util.Random;

import main.Node;
import main.Queen;

public class HillClimbing {
    private final static int N = 8;
    private Queen[] startState;
    private Node start; //start state
    private int nodesGenerated;

    public HillClimbing() {
        start = new Node(); //empty start node
        startState = new Queen[N]; //empty start state
        startState();
        nodesGenerated = 0;
    }

    public HillClimbing(Queen[] s) {
        start = new Node();
        startState = new Queen[N];
        for (int i = 0; i < s.length; i++) {
            startState[i] = new Queen(s[i].getRow(), s[i].getColumn());
        }
        start.setState(startState);
        start.computeHeuristic();
        nodesGenerated = 0;
        System.out.println("Initial State:\n" + start);
    }

    public void startState() {
        Random gen = new Random();
        for(int i = 0; i < N; i++) {
            startState[i] = new Queen(gen.nextInt(N), i);
        }
        start.setState(startState);
        start.computeHeuristic();
    }

    public Node hillClimbing() {
        Node currentNode = start;
        while (true) {
            ArrayList<Node> successors = currentNode.generateNeighbours(currentNode);
            nodesGenerated += successors.size();
            Node nextNode = null;

            for (int i = 0; i < successors.size(); i++) {
                if (successors.get(i).compareTo(currentNode) < 0) {
                    nextNode = successors.get(i);
                }
            }

            if (nextNode == null) {
                return currentNode;
            }

            currentNode = nextNode;
        }
    }

    public Node getStartNode() {
        return start;
    }

    public int getNodesGenerated() {
        return nodesGenerated;
    }
}
