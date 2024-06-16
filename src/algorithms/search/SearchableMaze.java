package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * Represents a maze that is made searchable for pathfinding algorithms.
 * Implements the ISearchable interface.
 */
public class SearchableMaze implements ISearchable {
    private Maze maze;
    private MazeState start;
    private MazeState end;

    /**
     * Constructs a SearchableMaze object based on a given maze.
     *
     * @param maze The maze object to be made searchable.
     */
    public SearchableMaze(Maze maze) {
        this.end = new MazeState(maze.getGoalPosition(), 0, null);
        this.start = new MazeState(maze.getStartPosition(), 0, null);
        this.maze = maze;
    }

    /**
     * Retrieves the number of rows in the maze.
     *
     * @return The number of rows in the maze.
     */
    public int getMazeRows() {
        return maze.getRows();
    }

    /**
     * Retrieves the number of columns in the maze.
     *
     * @return The number of columns in the maze.
     */
    public int getMazeColumns() {
        return maze.getColumns();
    }

    /**
     * Retrieves the starting state of the maze.
     *
     * @return The starting state of the maze.
     */
    @Override
    public AState getStartState() {
        return this.start;
    }

    /**
     * Retrieves the goal state of the maze.
     *
     * @return The goal state of the maze.
     */
    @Override
    public AState getGoalState() {
        return this.end;
    }

    /**
     * Generates and returns all possible states (successors) from a given state in the maze.
     *
     * @param s The current state for which successors are generated.
     * @return An ArrayList of all possible successor states from the current state.
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        ArrayList<AState> allPossibleStates = new ArrayList<>();
        if (s == null)
            return allPossibleStates;
        Position currentPosition = (Position) s.getCurrentPos();
        Position up = new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex());
        Position down = new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex());
        Position right = new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() + 1);
        Position left = new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() - 1);
        Position upRight = new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex() + 1);
        Position downRight = new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex() + 1);
        Position upLeft = new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex() - 1);
        Position downLeft = new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex() - 1);

        // Straight steps
        // Up
        if (maze.isValidPosition(up) && maze.getValue(up) == 0)
            allPossibleStates.add(new MazeState(up, s.getCost() + 10, s));
        // Down
        if (maze.isValidPosition(down) && maze.getValue(down) == 0)
            allPossibleStates.add(new MazeState(down, s.getCost() + 10, s));
        // Left
        if (maze.isValidPosition(left) && maze.getValue(left) == 0)
            allPossibleStates.add(new MazeState(left, s.getCost() + 10, s));
        // Right
        if (maze.isValidPosition(right) && maze.getValue(right) == 0)
            allPossibleStates.add(new MazeState(right, s.getCost() + 10, s));

        // Diagonal steps
        // UpRight
        if (maze.isValidPosition(upRight) && maze.isValidPosition(up) && maze.isValidPosition(right) && maze.getValue(upRight) == 0 && (maze.getValue(up) == 0 || maze.getValue(right) == 0))
            allPossibleStates.add(new MazeState(upRight, s.getCost() + 15, s));
        // UpLeft
        if (maze.isValidPosition(upLeft) && maze.isValidPosition(up) && maze.isValidPosition(left) && maze.getValue(upLeft) == 0 && (maze.getValue(up) == 0 || maze.getValue(left) == 0))
            allPossibleStates.add(new MazeState(upLeft, s.getCost() + 15, s));
        // DownRight
        if (maze.isValidPosition(downRight) && maze.isValidPosition(down) && maze.isValidPosition(right) && maze.getValue(downRight) == 0 && (maze.getValue(down) == 0 || maze.getValue(right) == 0))
            allPossibleStates.add(new MazeState(downRight, s.getCost() + 15, s));
        // DownLeft
        if (maze.isValidPosition(downLeft) && maze.isValidPosition(down) && maze.isValidPosition(left) && maze.getValue(downLeft) == 0 && (maze.getValue(down) == 0 || maze.getValue(left) == 0))
            allPossibleStates.add(new MazeState(downLeft, s.getCost() + 15, s));

        return allPossibleStates;
    }
}
