package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {

    /**
     *
     * @return The starting state as an AState object.
     */
    AState getStartState();

    /**
     *
     * @return The goal state as an AState object.
     */
    AState getGoalState();

    /**
     *
     * @param currState The current state from which possible states are to be found.
     * @return An ArrayList of AState objects representing all possible states that can be reached from the current state.
     */
    ArrayList<AState> getAllPossibleStates(AState currState);
}
