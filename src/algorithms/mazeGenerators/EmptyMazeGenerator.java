package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate (int rows, int columns){
        if (rows < 1 || columns < 1)
            return new Maze(1, 1); //TODO
        Maze newMaze = new Maze(rows,columns);
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                newMaze.setZero(i,j);
            }
        }
        return newMaze;
    }

}
