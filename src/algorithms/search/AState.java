package algorithms.search;

import java.io.Serializable;
import java.util.Objects;

public abstract class AState implements Serializable, Comparable<AState> {
    private Object currentPos;
    private double cost;
    private AState cameFrom;

    /**
     * Constructs a new state with the given position, cost, and predecessor state.
     */
    public AState(Object currentPos, double cost, AState cameFrom) {
        this.currentPos = currentPos;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    /**
     * Checks if this state is equal to another object.
     * Two states are considered equal if they have the same current position.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState state = (AState) o;
        return currentPos != null ? currentPos.equals(state.currentPos) : state.currentPos == null;
    }

    /**
     * Compares this state to another state based on their cost.
     * Returns a positive value if this state's cost is greater,
     * zero if the costs are equal, and a negative value otherwise.
     */
    @Override
    public int compareTo(AState other) {
        return Double.compare(other.cost, this.cost);
    }

    /**
     * Sets the current position of this state.
     */
    public void setCurrentPos(Object currentPos) {
        this.currentPos = currentPos;
    }

    /**
     * Sets the cost associated with this state.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Sets the predecessor state from which this state was reached.
     */
    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * Returns the current position of this state.
     */
    public Object getCurrentPos() {
        return currentPos;
    }

    /**
     * Returns the cost associated with this state.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Returns the predecessor state from which this state was reached.
     */
    public AState getCameFrom() {
        return cameFrom;
    }

    /**
     * Returns a string representation of this state.
     */
    @Override
    public String toString() {
        return currentPos != null ? currentPos.toString() : "";
    }
}
