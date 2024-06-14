package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    private ArrayList<AState> solPath;
    private int i=0;

    public Solution(){
        solPath=new ArrayList<>();
    }

    public Solution(ArrayList<AState> solPath){
        this.solPath=solPath;
    }

    public ArrayList getSolutionPath(){
        return solPath;
    }

    public int getSolutionLength(){return solPath.size();}

    public void AddState(AState state){
        if(state != null) {
            solPath.add(i, state);
            i++;
        }
    }
}
