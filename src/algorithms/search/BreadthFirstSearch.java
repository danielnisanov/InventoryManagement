package algorithms.search;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm{
    public BreadthFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable s) {
        LinkedHashSet<String> visitedString = new LinkedHashSet<>();
        Queue<AState> statesQueue = getDataStructure();
        statesQueue.add(s.getStartState());
        visitedString.add(s.getStartState().toString());

        while (!statesQueue.isEmpty()) {
            AState current = statesQueue.remove();
            if (current.equals(s.getGoalState())) {
                solution = new Solution();
                backwardPath(current, solution);
                return solution;
            }

            List<AState> neighbors = s.getAllPossibleStates(current);
            numVisitedNodes++;
            for (AState neighbor : neighbors) {
                if (!visitedString.contains(neighbor.toString())) {
                    neighbor.setCameFrom(current);
                    statesQueue.add(neighbor);
                    visitedString.add(neighbor.toString());
                }
            }
        }
        return null;
    }



    public Queue<AState> getDataStructure() {
        return new LinkedList<>();
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
