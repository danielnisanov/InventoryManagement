package algorithms.search;

public class AState {
    private String stateName;
    private double cost;
    private AState cameFrom;

    public AState (String stateName){
        this.stateName = stateName;
    }

    @Override
    public boolean equals (Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState state1 = (AState) o;
        return stateName != null ? stateName.equals(state1.stateName) : state1.stateName == null;
    }

    @Override
    public int hashCode(){
        return stateName != null ? stateName.hashCode() : 0;
    }


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
