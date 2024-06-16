package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * Represents a state within a maze for use in maze-solving algorithms.
 * Extends the abstract class AState.
 */
public class MazeState extends AState {

    /**
     * Constructor to initialize a MazeState object.
     *
     * @param currentPos The current position in the maze.
     * @param cost       The cost associated with reaching this state.
     * @param cameFrom   The predecessor state from which this state was reached.
     */
    public MazeState(Position currentPos, double cost, AState cameFrom) {
        super(currentPos, cost, cameFrom);
    }

}
