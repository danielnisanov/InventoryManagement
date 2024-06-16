package algorithms.search;

import java.util.Stack;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Solution solution;
    protected int numVisitedNodes;

    /**
     * Constructor for the ASearchingAlgorithm class.
     * Initializes the solution object and the number of visited nodes to zero.
     */
    public ASearchingAlgorithm() {
        this.solution = new Solution();
        this.numVisitedNodes = 0;
    }

    /**
     * Returns the solution found by the algorithm.
     *
     * @return Solution object containing the path from start to goal.
     */
    public Solution getSolution() {
        return solution;
    }

    /**
     * Returns the number of nodes that have been visited by the algorithm.
     *
     * @return Number of visited nodes.
     */
    public int getNumVisitedNodes() {
        return numVisitedNodes;
    }

    /**
     * Constructs the solution path by tracing back from the goal state to the start state.
     * The path is reversed to ensure it goes from the start to the goal.
     *
     * @param s   The goal state from which to start tracing back.
     * @param sol The Solution object to which the states will be added.
     */
    public void backwardPath(AState s, Solution sol) {
        Stack<AState> stackSol = new Stack<>();
        AState curr = s;

        // Ensure the state is valid and has a predecessor
        if (s != null && s.getCameFrom() != null) {
            stackSol.push(curr);
            while (curr.getCameFrom() != null) {
                curr = curr.getCameFrom();
                stackSol.push(curr);
            }

            // Add states to the solution in reverse order to get the correct path
            while (!stackSol.empty()) {
                sol.AddState(stackSol.pop());
            }
        }
    }
}
