package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Maze newMaze = new Maze(rows, columns);
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                newMaze.setOne(i,j);
            }
        }
        Random random = new Random();

        // Initialize start and end positions
        Position start = new Position(0, 0);
        Position end = new Position(rows - 1, columns - 1);

        // Create stack for DFS algorithm
        Stack<Position> stack = new Stack<>();
        stack.push(start);
        newMaze.setZero(start);



        while (!stack.isEmpty()) {
            Position current = stack.pop();
            List<Position> neighbors = getUnvisitedNeighbors(newMaze, current);
            if (!neighbors.isEmpty()) {
                Collections.shuffle(neighbors, random); // Shuffle to randomize neighbor selection
                Position next = neighbors.get(0); // Pick the first neighbor
                newMaze.setZero(next);
                stack.push(current); // Push current cell back onto the stack
                newMaze.moveDFS(current, next); // Remove wall between current and next
                stack.push(next); // Push next cell onto the stack
            }
        }

        // Add some randomness to maze paths and walls
        addRandomPathsAndWalls(newMaze);

        // Ensure end position has path neighbors
        ensureEndPath(newMaze, end);

        return newMaze;
    }

    private List<Position> getUnvisitedNeighbors(Maze maze, Position current) {
        int row = current.getRowIndex();
        int col = current.getColumnIndex();
        List<Position> neighbors = new ArrayList<>();

        // Check right neighbor
        if (col + 2 < maze.getColumns() && maze.getMaze()[row][col + 2] == 1) {
            neighbors.add(new Position(row, col + 2));
        }
        // Check left neighbor
        if (col - 2 >= 0 && maze.getMaze()[row][col - 2] == 1) {
            neighbors.add(new Position(row, col - 2));
        }
        // Check down neighbor
        if (row + 2 < maze.getRows() && maze.getMaze()[row + 2][col] == 1) {
            neighbors.add(new Position(row + 2, col));
        }
        // Check up neighbor
        if (row - 2 >= 0 && maze.getMaze()[row - 2][col] == 1) {
            neighbors.add(new Position(row - 2, col));
        }

        return neighbors;
    }

    private void addRandomPathsAndWalls(Maze maze) {
        Random random = new Random();
        int numRandomChanges = (int) Math.sqrt(maze.getRows() * maze.getColumns());

        for (int i = 0; i < numRandomChanges; i++) {
            int randRow = random.nextInt(maze.getRows());
            int randCol = random.nextInt(maze.getColumns());
            if (maze.getMaze()[randRow][randCol] == 1) {
                maze.getMaze()[randRow][randCol] = random.nextInt(2);
            }
        }
    }


    private void ensureEndPath(Maze maze, Position end) {
        int endRow = end.getRowIndex();
        int endCol = end.getColumnIndex();

        if (isValidPosition(maze, new Position(endRow + 1, endCol))) {
            maze.getMaze()[endRow + 1][endCol] = 0;
        }
        if (isValidPosition(maze, new Position(endRow, endCol - 1))) {
            maze.getMaze()[endRow][endCol - 1] = 0;
        }
    }

    private boolean isValidPosition(Maze maze, Position position) {
        int row = position.getRowIndex();
        int col = position.getColumnIndex();
        return row >= 0 && row < maze.getRows() && col >= 0 && col < maze.getColumns();
    }
}



