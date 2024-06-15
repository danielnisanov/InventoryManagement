package algorithms.search;

import java.io.Serializable;

public abstract class AState implements Serializable,Comparable {
    private String stateName;
    private double cost;
    private AState cameFrom;

    /**
     constructor
     */
    public AState (String stateName){
        this.stateName = stateName;
    }

    /**
     override equals
     */
    @Override
    public boolean equals (Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState state1 = (AState) o;
        return stateName != null ? stateName.equals(state1.stateName) : state1.stateName == null;
    }

    /**
     override hash code
     */
    @Override
    public int hashCode(){
        return stateName != null ? stateName.hashCode() : 0;
    }

    /**
     override compare to
     */
    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot compare to null");
        }
        AState other = ((AState)o);
        return Double.compare(this.cost, other.cost);
    }
    @Override
    public String toString(){
        return this.stateName;
    }

    /**
     getters and setters
     */
    public String getStateName() {
        return stateName;
    }

    public double getCost() {
        return cost;
    }

    public AState getCameFrom() {
        return cameFrom;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCameFrom(AState cameFrom) {
        this.cameFrom = cameFrom;
    }

}
