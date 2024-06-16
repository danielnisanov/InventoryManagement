package algorithms.mazeGenerators;

import java.util.Random;

/**
 * A simple maze generator that creates a maze with random walls and paths.
 */
public class SimpleMazeGenerator extends AMazeGenerator {
    /**
     * Overrides the generate function to return an instance of a random Maze.
     * @param rows    The number of rows in the maze.
     * @param columns The number of columns in the maze.
     * @return A randomly generated Maze instance.
     */
    @Override
    public Maze generate(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            return new Maze(1, 1);  // Return a minimal valid maze
        }

        Maze newMaze = new Maze(rows, columns);
        Random random = new Random();

        // Randomly fill the maze with walls (1) and paths (0)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int randomInt = random.nextInt(2);
                if (randomInt == 0) {
                    newMaze.setZero(i, j);
                } else {
                    newMaze.setOne(i, j);
                }
            }
        }

        // Ensure there is a path from the start position to the end position
        // Clear the top row path
        for (int col = newMaze.getStartPosition().getColumnIndex(); col < columns; col++) {
            newMaze.setZero(0, col);
        }
        // Clear the right column path
        for (int row = 0; row < newMaze.getGoalPosition().getRowIndex(); row++) {
            newMaze.setZero(row, columns - 1);
        }

        return newMaze;
    }
}
