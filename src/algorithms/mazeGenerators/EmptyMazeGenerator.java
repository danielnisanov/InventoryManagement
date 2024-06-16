package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    /**
     * Overrides the generate function to return an instance of an empty Maze.
     * @return A Maze object representing the generated empty maze.
     */
    @Override
    public Maze generate (int rows, int columns) {
        if (rows < 1 || columns < 1){
            Maze newMaze = new Maze(1, 1);
            return newMaze;
        }
        Maze newMaze = new Maze(rows, columns);
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                newMaze.setZero(i,j);
            }
        }
        return newMaze;
    }
}
