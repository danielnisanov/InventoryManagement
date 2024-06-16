package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    /**
     override of generate function - returns an instance of an empty Maze
     */
    @Override
    public Maze generate (int rows, int columns){
        if (rows < 1 || columns < 1)
            System.out.println("invalid values for rows or columns");
        Maze newMaze = new Maze(rows,columns);
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                newMaze.setZero(i,j);
            }
        }
        return newMaze;
    }

}
