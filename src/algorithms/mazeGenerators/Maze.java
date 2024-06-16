package algorithms.mazeGenerators;

/**
 * Represents a maze with a grid of cells, start position, and goal position.
 */
public class Maze {
    private int rows;
    private int columns;
    private Position startPosition;
    private Position goalPosition;
    private int[][] maze;

    /**
     * Constructor to initialize the maze with specified rows and columns.
     * Sets the start position to (0, 0) and goal position to (rows-1, columns-1).
     *
     * @param rows    The number of rows in the maze.
     * @param columns The number of columns in the maze.
     */
    public Maze(int rows, int columns) {
        if (rows > 0 && columns > 0) {
            this.rows = rows;
            this.columns = columns;
            this.maze = new int[rows][columns];
            this.startPosition = new Position(0, 0);
            this.goalPosition = new Position(rows - 1, columns - 1);
        }
    }

    /**
     * Gets the start position of the maze.
     *
     * @return The start position.
     */
    public Position getStartPosition() {
        return startPosition;
    }

    /**
     * Gets the goal position of the maze.
     *
     * @return The goal position.
     */
    public Position getGoalPosition() {
        return goalPosition;
    }

    /**
     * Prints the maze with 'S' marking the start position and 'E' marking the goal position.
     */
    public void print() {
        int rows = maze.length;
        int cols = maze[0].length;

        for (int i = 0; i < rows; i++) {
            System.out.print("[ ");
            for (int j = 0; j < cols; j++) {
                if (i == startPosition.getRowIndex() && j == startPosition.getColumnIndex()) {
                    System.out.print("S ");
                } else if (i == goalPosition.getRowIndex() && j == goalPosition.getColumnIndex()) {
                    System.out.print("E ");
                } else {
                    System.out.print(maze[i][j] + " ");
                }
            }
            System.out.print("]");
            System.out.println();
        }
    }

    /**
     * Gets the value at the specified cell.
     *
     * @param row    The row index.
     * @param column The column index.
     * @return The value at the specified cell, or -1 if out of bounds.
     */
    public int getValue(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            return -1;
        }
        return maze[row][column];
    }

    /**
     * Gets the value at the specified position.
     *
     * @param position The position to get the value from.
     * @return The value at the specified position, or -1 if out of bounds.
     */
    public int getValue(Position position) {
        if (position.getRowIndex() < 0 || position.getRowIndex() >= rows || position.getColumnIndex() < 0 || position.getColumnIndex() >= columns) {
            return -1;
        }
        return maze[position.getRowIndex()][position.getColumnIndex()];
    }

    /**
     * Sets the value at the specified cell to 1, indicating a wall.
     *
     * @param row    The row index.
     * @param column The column index.
     */
    public void setOne(int row, int column) {
        this.maze[row][column] = 1;
    }

    /**
     * Sets the value at the specified position to 1, indicating a wall.
     *
     * @param position The position to set the value at.
     */
    public void setOne(Position position) {
        this.maze[position.getRowIndex()][position.getColumnIndex()] = 1;
    }

    /**
     * Sets the value at the specified cell to 0, indicating a path.
     *
     * @param row    The row index.
     * @param column The column index.
     */
    public void setZero(int row, int column) {
        this.maze[row][column] = 0;
    }

    /**
     * Sets the value at the specified position to 0, indicating a path.
     *
     * @param position The position to set the value at.
     */
    public void setZero(Position position) {
        this.maze[position.getRowIndex()][position.getColumnIndex()] = 0;
    }

    /**
     * Gets the number of rows in the maze.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the maze.
     *
     * @return The number of columns.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets the maze grid.
     *
     * @return The maze grid.
     */
    public int[][] getMaze() {
        return maze;
    }

    /**
     * Checks if a position is valid within the maze boundaries.
     *
     * @param position The position to check.
     * @return True if the position is valid, false otherwise.
     */
    public boolean isValidPosition(Position position) {
        return (position != null &&
                0 <= position.getRowIndex() && position.getRowIndex() < this.rows &&
                0 <= position.getColumnIndex() && position.getColumnIndex() < this.columns);
    }

    /**
     * Moves in the DFS algorithm by changing the value of the position from 1 to 0.
     *
     * @param current The current position.
     * @param next    The next position to move to.
     */
    public void moveDFS(Position current, Position next) {
        if (!isValidPosition(next)) {
            return;
        }

        if (next.getRowIndex() == current.getRowIndex()) {
            // Move right
            if (next.getColumnIndex() > current.getColumnIndex()) {
                maze[current.getRowIndex()][current.getColumnIndex() + 1] = 0;
            }
            // Move left
            else {
                maze[current.getRowIndex()][current.getColumnIndex() - 1] = 0;
            }
        } else if (next.getColumnIndex() == current.getColumnIndex()) {
            // Move down
            if (next.getRowIndex() > current.getRowIndex()) {
                maze[current.getRowIndex() + 1][current.getColumnIndex()] = 0;
            }
            // Move up
            else {
                maze[current.getRowIndex() - 1][current.getColumnIndex()] = 0;
            }
        }
    }

    /**
     * Sets the start position of the maze.
     *
     * @param startPosition The start position to set.
     */
    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    /**
     * Sets the goal position of the maze.
     *
     * @param goalPosition The goal position to set.
     */
    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }
}