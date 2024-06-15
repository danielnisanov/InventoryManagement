package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;

public class SearchableMaze implements ISearchable {

    private Maze maze;

    /**
     constructor
     */
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

 //   @Override
//    public ArrayList<AState> getAllPossibleStates(AState state) {
//        ArrayList<AState> possiblestates = new ArrayList<>();
//        if (!(state instanceof MazeState)) {
//            return possiblestates; // Return empty list if state is not MazeState
//        }
//
//        // Extract position from MazeState
//        Position currentPosition = ((MazeState) state).getPosition();
//
//        // Generate possible moves (up, down, left, right)
//        // Assuming movements are allowed only to empty cells (0) in the maze
//        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        for (int[] direction : directions) {
//            int newRow = currentPosition.getRowIndex() + direction[0];
//            int newCol = currentPosition.getColumnIndex() + direction[1];
//            Position neighborPosition = new Position(newRow, newCol);
//
//            // Check if the neighbor position is valid and accessible (maze cell value is 0)
//            if (maze.isValidPosition(neighborPosition) && maze.getValue(neighborPosition) == 0) {
//                MazeState neighborState = new MazeState(neighborPosition);
//                possiblestates.add(neighborState);
//            }
//        }
//
//        return possiblestates;
//    }

    /**
     function that returns all the possible moves
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        ArrayList<AState> allPossibleStates = new ArrayList<>();

        if (!(s instanceof MazeState)) {
            return allPossibleStates; // Return empty list if state is not MazeState
        }

        MazeState currentState = (MazeState) s;
        Position currentPosition = currentState.getPosition();
        int currentRow = currentPosition.getRowIndex();
        int currentCol = currentPosition.getColumnIndex();

        // Define movement directions
        int[][] directions = {
                {-1, 0, 10},   // up
                {1, 0, 10},    // down
                {0, -1, 10},   // left
                {0, 1, 10},    // right
                {-1, 1, 15},   // upRight
                {-1, -1, 15},  // upLeft
                {1, 1, 15},    // downRight
                {1, -1, 15}    // downLeft
        };

        // Iterate over each direction
        for (int[] dir : directions) {
            int newRow = currentRow + dir[0];
            int newCol = currentCol + dir[1];

            if (maze.isValidPosition(new Position(newRow, newCol)) && maze.getValue(new Position(newRow, newCol)) == 0) {
                allPossibleStates.add(new MazeState(new Position(newRow, newCol)));
            }
        }

        return allPossibleStates;
    }

}
