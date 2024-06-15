package algorithms.search;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm{
    public DepthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable s) {
        HashSet<String> visitedString = new HashSet<>();
        Stack<AState> stack = new Stack<>();
        stack.push(s.getStartState());
        visitedString.add(s.getStartState().toString());

        while (!stack.isEmpty()) {
            AState current = stack.pop();
            if (current.equals(s.getGoalState())) {
                backwardPath(current, solution);
                return solution;
            }

            List<AState> neighbors = s.getAllPossibleStates(current);
            numVisitedNodes++;
            for (AState neighbor : neighbors) {
                if (!visitedString.contains(neighbor.toString())) {
                    neighbor.setCameFrom(current);
                    stack.push(neighbor);
                    visitedString.add(neighbor.toString());
                }
            }
        }
        return null; // No solution found
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }
}
