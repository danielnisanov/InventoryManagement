package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    @Override
    public abstract Maze generate(int rows, int columns);

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns){
        validateInput(rows, columns);
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        long finishTime = System.currentTimeMillis();
        return finishTime-startTime;
    }

    protected void validateInput(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Rows and columns must be greater than zero.");
        }
    }


}
