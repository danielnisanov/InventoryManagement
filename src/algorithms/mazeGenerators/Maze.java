package algorithms.mazeGenerators;

public class Maze {
    private int rows;
    private int columns;
    private Position startPosition;
    private Position goalPosition;
    private int[][] maze;

    /**
    constructor
     */
    public Maze(int rows, int columns){
        if (rows > 0 && columns > 0) {
            this.rows = rows;
            this.columns = columns;
            this.maze = new int[rows][columns];
            this.startPosition = new Position(0, 0);
            this.goalPosition = new Position(rows - 1, columns - 1);
        }
    }

    /**
     getters
     */
    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    /**
     print function -
     The entrance point to the maze is marked with 'S' and the exit point is marked with 'E'
     */
    public void print() {
        int rows = maze.length;
        int cols = maze[0].length;

        for (int i = 0; i < rows; i++) {
            System.out.print("[ ");
            for (int j = 0; j < cols; j++) {
                if (i == startPosition.getRowIndex() && j == startPosition.getRowIndex()) {
                    System.out.print("S ");
                }
                else if (i == goalPosition.getRowIndex() && j == goalPosition.getRowIndex()) {
                    System.out.print("E ");
                }
                else
                    System.out.print(maze[i][j] + " ");
            }
            System.out.print("]");
            System.out.println();
        }
    }

    public int getValue(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns)
            return -1;
        return maze[row][column];
    }

    public int getValue(Position position) {
        if (position.getRowIndex() < 0 || position.getRowIndex() >= rows || position.getColumnIndex() < 0 || position.getColumnIndex() >= columns)
            return -1;
        return maze[position.getRowIndex()][position.getColumnIndex()];
    }

    /**
     function to set 1 that means a wall in the maze
     */
    public void setOne(int row, int column) {
        this.maze[row][column] = 1;
    }

    public void setOne(Position position) {

        this.maze[position.getRowIndex()][position.getColumnIndex()] = 1;
    }

    /**
     function to set 0 that means a path in the maze
     */
    public void setZero(int row, int column) {
        this.maze[row][column] = 0;
    }

    public void setZero(Position position) {

        this.maze[position.getRowIndex()][position.getColumnIndex()] = 0;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getMaze() {
        return maze;
    }

    /**
     function to check if position is a valid position in the maze(inside the boundaries)
     */


    public boolean isValidPosition(Position position) {
        return (position != null &&
                0 <= position.getRowIndex() && position.getRowIndex() < this.rows &&
                0 <= position.getColumnIndex() && position.getColumnIndex() < this.columns);
    }

    /**
     function that use to move in the DFS - can change the value of position from 1 to 0
     */
    public void moveDFS(Position current, Position next){
        if(!isValidPosition(next)) {
            return;
        }

        if(next.getRowIndex() == current.getRowIndex())
        {
            //go right
            if(next.getColumnIndex() > current.getColumnIndex()){
                maze[current.getRowIndex()][current.getColumnIndex()+1] = 0;
            }
            //go left
            else{
                maze[current.getRowIndex()][current.getColumnIndex()-1] = 0;
            }
        }
        else if(next.getColumnIndex() == current.getColumnIndex())
        {
            //go down
            if(next.getRowIndex() > current.getRowIndex()){
                maze[current.getRowIndex()+1][current.getColumnIndex()] = 0;
            }
            //go up
            else {
                maze[current.getRowIndex()-1][current.getColumnIndex()] = 0;
            }
        }
    }
    /**
     *setters
     */
    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }
}