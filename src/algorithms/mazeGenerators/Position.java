package algorithms.mazeGenerators;

public class Position {
    private int row;
    private int column;

    /**
     constructor
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return column;
    }

    /**
     function that compare between 2 position to check if they are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public String toString() {
        return "{"+ row + "," + column + "}";
    }

    //TODO NEW

    @Override
    public int hashCode() {
        return 31 * row + column;
    }

}
