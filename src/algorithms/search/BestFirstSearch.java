package algorithms.search;
import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch {

    /**
     * Constructor for the BestFirstSearch class.
     * Calls the constructor of the superclass (BreadthFirstSearch).
     */
    public BestFirstSearch() {
        super();
    }

    /**
     * Returns the data structure used for the Best First Search algorithm.
     * Overrides the structure from BreadthFirstSearch to use a PriorityQueue.
     *
     * @return PriorityQueue of AState, representing the structure used by the algorithm.
     */
    @Override
    public Queue<AState> getStructure() {
        return new PriorityQueue<>();
    }

    /**
     * Returns the name of the search algorithm.
     *
     * @return "BestFirstSearch" as the name of the algorithm.
     */
    @Override
    public String getName() {
        return "BestFirstSearch";
    }
}
