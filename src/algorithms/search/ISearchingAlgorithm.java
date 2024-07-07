package algorithms.search;

public interface ISearchingAlgorithm {

    /**
     *
     * @param s The searchable problem to solve.
     * @return A Solution object containing the path from the start state to the goal state.
     */
    Solution solve(ISearchable s);

    /**
     *
     * @return The number of visited nodes as an integer.
     */
    int getNumVisitedNodes();

    /**
     *
     * @return The name of the algorithm as a String.
     */
    String getName();
}
