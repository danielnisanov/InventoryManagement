package algorithms.search;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {

    /**
     * Constructor for the BreadthFirstSearch class.
     * Calls the superclass constructor to initialize the solution and number of visited nodes.
     */
    public BreadthFirstSearch() {
        super();
    }

    /**
     * Solves the given searchable problem using the Breadth-First Search algorithm.
     *
     * @param s The searchable problem to solve.
     * @return Solution object containing the path from start to goal, or null if no solution is found.
     */
    @Override
    public Solution solve(ISearchable s) {
        Queue<AState> statesQueue = getStructure();
        LinkedHashSet<String> visitedNodes = new LinkedHashSet<>();

        // Add the start state to the visited set and the queue
        visitedNodes.add(s.getStartState().toString());
        statesQueue.add(s.getStartState());

        while (!statesQueue.isEmpty()) {
            AState current = statesQueue.remove();

            // Check if the goal state is reached
            if (current.equals(s.getGoalState())) {
                backwardPath(current, solution);
                return solution;
            }

            // Get all possible states (neighbors) from the current state
            List<AState> neighborsNodes = s.getAllPossibleStates(current);
            numVisitedNodes++;

            // Process each neighbor
            for (AState neighbor : neighborsNodes) {
                if (!visitedNodes.contains(neighbor.toString())) {
                    neighbor.setCameFrom(current);
                    statesQueue.add(neighbor);
                    visitedNodes.add(neighbor.toString());
                }
            }
        }
        // Return null if no solution is found
        return null;
    }

    /**
     * Returns a new queue structure for storing states.
     *
     * @return A Queue of AState objects.
     */
    public Queue<AState> getStructure() {
        return new LinkedList<>();
    }

    /**
     * Returns the name of the search algorithm.
     *
     * @return The name of the algorithm as a String.
     */
    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
