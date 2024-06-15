package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.search.AState;
import algorithms.search.ISearchable;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze implements ISearchable {

    private Maze maze;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
    }

    @Override
    public AState getStartState() {
        // Create a new MazeState instance representing the start position
        return new MazeState(maze.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        // Create a new MazeState instance representing the goal position
        return new MazeState(maze.getGoalPosition());
    }

    @Override
    public ArrayList<AState> getAllPossibleStates(AState state) {
        ArrayList<AState> neighbors = new ArrayList<>();
        if (!(state instanceof MazeState)) {
            return neighbors; // Return empty list if state is not MazeState (should not happen)
        }

        // Extract position from MazeState
        Position currentPosition = ((MazeState) state).getPosition();

        // Generate possible moves (up, down, left, right)
        // Assuming movements are allowed only to empty cells (0) in the maze
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : directions) {
            int newRow = currentPosition.getRowIndex() + direction[0];
            int newCol = currentPosition.getColumnIndex() + direction[1];
            Position neighborPosition = new Position(newRow, newCol);

            // Check if the neighbor position is valid and accessible (maze cell value is 0)
            if (maze.isValidPosition(neighborPosition) && maze.getValue(neighborPosition) == 0) {
                MazeState neighborState = new MazeState(neighborPosition);
                neighbors.add(neighborState);
            }
        }

        return neighbors;
    }
}
