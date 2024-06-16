package algorithms.mazeGenerators;

/**
 * Interface for maze generator classes.
 */
public interface IMazeGenerator {
    /**
     * Generates a maze with the specified number of rows and columns.
     * @return A Maze object representing the generated maze.
     */
    public Maze generate (int rows, int columns);

    /**
     * Measures the time taken to generate a maze.
     * @return The time taken to generate the maze in milliseconds.
     */
    public long measureAlgorithmTimeMillis(int rows, int columns);
}