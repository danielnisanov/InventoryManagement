package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate (int rows, int columns) {
        //   validateInput(rows, columns); //TODO
        if (rows < 1 || columns < 1)
            return new Maze(1, 1); //TODO
        Maze newMaze = new Maze(rows,columns);
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                Random random = new Random();
                int randomInt = random.nextInt(2);
                if (randomInt == 0)
                    newMaze.setZero(i, j);
                else
                    newMaze.setOne(i, j);
            }
        }

        for (int col = newMaze.getStartPosition().getColumnIndex(); col < columns; col++) {
            newMaze.setZero(0, col);
        }
        for (int row = 0; row < newMaze.getGoalPosition().getRowIndex(); row++) {
            newMaze.setZero(row, columns - 1);
        }

        return newMaze;
    }

//    protected void validateInput(int rows, int columns) {
//        if (rows <= 0 || columns <= 0) {
//            throw new IllegalArgumentException("Rows and columns must be greater than zero.");
//        }
//    }
}
