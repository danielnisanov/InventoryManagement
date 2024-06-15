package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    private ArrayList<AState> solPath;
    private int i=0;

    /**
     default constuctor
     */
    public Solution(){
        solPath=new ArrayList<>();
    }

    /**
     constuctor
     */
    public Solution(ArrayList<AState> solPath){
        this.solPath=solPath;
    }

    /**
     getter
     */
    public ArrayList getSolutionPath(){
        return solPath;
    }

    /**
     function that return the length of the path
     */
    public int getSolutionLength(){return solPath.size();}

    /**
     function to add state to the path
     */
    public void AddState(AState state){
        if(state != null) {
            solPath.add(i, state);
            i++;
        }
    }
}
