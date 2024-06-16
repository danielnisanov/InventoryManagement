package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {

    /**
     * Constructor for the DepthFirstSearch class.
     * Calls the superclass constructor to initialize the solution and number of visited nodes.
     */
    public DepthFirstSearch() {
        super();
    }

    /**
     * Solves the given searchable problem using the Depth-First Search algorithm.
     *
     * @param s The searchable problem to solve.
     * @return Solution object containing the path from start to goal, or null if no solution is found.
     */
    @Override
    public Solution solve(ISearchable s) {
        HashSet<String> visitedString = new HashSet<>();
        Stack<AState> stack = new Stack<>();

        // Initialize the stack with the start state and mark it as visited
        stack.push(s.getStartState());
        visitedString.add(s.getStartState().toString());

        while (!stack.isEmpty()) {
            AState current = stack.pop();

            // Check if the goal state is reached
            if (current.equals(s.getGoalState())) {
                backwardPath(current, solution);
                return solution;
            }

            // Get all possible states (neighbors) from the current state
            List<AState> neighbors = s.getAllPossibleStates(current);
            numVisitedNodes++;

            // Process each neighbor
            for (AState neighbor : neighbors) {
                if (!visitedString.contains(neighbor.toString())) {
                    neighbor.setCameFrom(current);
                    stack.push(neighbor);
                    visitedString.add(neighbor.toString());
                }
            }
        }
        // Return null if no solution is found
        return null;
    }

    /**
     * Returns the name of the search algorithm.
     *
     * @return The name of the algorithm as a String.
     */
    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
