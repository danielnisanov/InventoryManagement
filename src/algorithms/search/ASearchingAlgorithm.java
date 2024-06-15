package algorithms.search;

import java.util.Stack;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Solution solution;
    protected int numVisitedNodes;

    /**
     constructor
     */
    public ASearchingAlgorithm(){
        this.solution = new Solution();
        this.numVisitedNodes = 0;
    }

    /**
     function that return solution of the maze
     */
    public Solution getSolution() {
        return solution;
    }

    /**
     function that return the number of visited nodes
     */
    public int getNumVisitedNodes() {
        return numVisitedNodes;
    }

    /**
     function that reverses the path that was found
     */
    public void backwardPath(AState s, Solution sol) {
        Stack<AState> stackSol=new Stack<>();
        AState curr=s;
        if(s != null && s.getCameFrom() != null) {
            stackSol.push(curr);
            while (curr.getCameFrom() != null){
                curr=curr.getCameFrom();
                stackSol.push(curr);
            }

            while (!stackSol.empty()) {
                sol.AddState(stackSol.pop());
            }
        }
    }
}
