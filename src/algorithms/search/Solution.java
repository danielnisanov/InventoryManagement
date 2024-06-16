package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a solution path consisting of states.
 * Implements Serializable to allow for object serialization.
 */
public class Solution implements Serializable {
    private ArrayList<AState> solPath;
    private int i = 0;

    /**
     * Default constructor.
     * Initializes an empty solution path.
     */
    public Solution() {
        solPath = new ArrayList<>();
    }

    /**
     * Constructor that initializes the solution path with an existing ArrayList of states.
     *
     */
    public Solution(ArrayList<AState> solPath) {
        this.solPath = solPath;
    }

    /**
     * Retrieves the solution path.
     *
     * @return The ArrayList of states representing the solution path.
     */
    public ArrayList<AState> getSolutionPath() {
        return solPath;
    }

    /**
     *
     * @return The number of states in the solution path.
     */
    public int getSolutionLength() {
        return solPath.size();
    }

    /**
     * Adds a state to the solution path.
     *
     */
    public void AddState(AState state) {
        if (state != null) {
            solPath.add(i, state);
            i++;
        }
    }
}
