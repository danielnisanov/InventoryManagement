package algorithms.search;

import java.util.Stack;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected Solution solution;
    protected int numVisitedNodes;

    public ASearchingAlgorithm(){
        this.solution = new Solution();
        this.numVisitedNodes = 0;
    }

    public Solution getSolution() {
        return solution;
    }

    public int getNumVisitedNodes() {
        return numVisitedNodes;
    }

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
    }}
