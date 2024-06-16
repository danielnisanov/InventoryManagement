package algorithms.mazeGenerators;

public class Position {
    private int row;
    private int column;

    /**
     * Constructor to initialize the position with row and column indices.
     * @param row The row index of the position.
     * @param column The column index of the position.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets the row index of the position.
     * @return The row index.
     */
    public int getRowIndex() {
        return row;
    }

    /**
     * Gets the column index of the position.
     * @return The column index.
     */
    public int getColumnIndex() {
        return column;
    }

    /**
     * Checks if this position is equal to another object.
     * @param o The object to compare with.
     * @return True if the positions are the same, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    /**
     * Returns a string representation of the position.
     * @return A string in the format "{row,column}".
     */
    @Override
    public String toString() {
        return "{"+ row + "," + column + "}";
    }


}
