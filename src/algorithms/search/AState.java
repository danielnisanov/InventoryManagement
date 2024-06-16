package algorithms.search;

import java.io.Serializable;
import java.util.Objects;

public abstract class AState implements Serializable,Comparable {
    private Object currentPos;
    private double cost;
    private AState cameFrom;


    public AState (Object currentPos, double cost, AState cameFrom){
        this.currentPos = currentPos;
        this.cost = cost;
        this.cameFrom = cameFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState state = (AState) o;
        return currentPos != null ? currentPos.equals(state.currentPos) : state.currentPos != null;
    }


    @Override
    public int compareTo(Object o) {
        AState other = ((AState)o);
        if(cost < other.cost)
            return 1;
        else if (cost == other.cost)
            return 0;
        else
            return -1;
    }

    public void setCurrentPos(Object currentPos) {
        this.currentPos = currentPos;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

    public Object getCurrentPos() {
        return currentPos;
    }

    public double getCost() {
        return cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    @Override
    public String toString() {
        return this.currentPos.toString();
    }
}
