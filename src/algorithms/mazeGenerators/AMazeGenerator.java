package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * Generates a maze with the specified number of rows and columns.
     * @return A Maze object representing the generated maze.
     */
    @Override
    public abstract Maze generate(int rows, int columns);

    /**
     * Measures the time taken to generate a maze.
     * @return The time taken to generate the maze in milliseconds.
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns){
        validateInput(rows, columns);
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        long finishTime = System.currentTimeMillis();
        return finishTime-startTime;
    }

    /**
     * Validates the input dimensions for the maze.
     * If either rows or columns is less than or equal to zero, an error message is printed.
     */
    protected void validateInput(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            System.out.println("Rows and columns must be greater than zero.");
        }
    }

}
