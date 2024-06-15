package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    //FIXME
    //TODO NEW

    private Position position;

    /**
     constructor
     */
    public MazeState(Position position) {
        super(position.toString());
        this.position = position;
    }
    public MazeState(Position position, double cost) {
        super(position.toString(), cost);
        this.position = position;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeState mazeState = (MazeState) o;
        return position != null ? position.equals(mazeState.position) : mazeState.position == null;
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof MazeState)) {
            throw new IllegalArgumentException("Cannot compare MazeState with a different type");
        }
        AState other = ((AState)o);
        MazeState otherMazeState = (MazeState) other;

        // Compare based on position equality
        if (!this.position.equals(otherMazeState.position)) {
            return this.position.equals(otherMazeState.position) ? 0 : -1;
        }

        // If positions are equal, compare based on cost (assuming cost is relevant)
        return Double.compare(this.getCost(), otherMazeState.getCost());
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


}
