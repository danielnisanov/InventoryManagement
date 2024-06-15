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
        Queue<AState> statesQueue = getStructure();
        LinkedHashSet<String> visitedNodes = new LinkedHashSet<>();

        visitedNodes.add(s.getStartState().toString());
        statesQueue.add(s.getStartState());

        while (!statesQueue.isEmpty()) {
            AState current = statesQueue.remove();

            if (current.equals(s.getGoalState())) {
                backwardPath(current, solution);
                return solution;
            }

            List<AState> neighborsNodes = s.getAllPossibleStates(current);
            numVisitedNodes++;

            for (AState neighbor : neighborsNodes) {
                if (!visitedNodes.contains(neighbor.toString())) {
                    neighbor.setCameFrom(current);
                    statesQueue.add(neighbor);
                    visitedNodes.add(neighbor.toString());
                }
            }
        }
        return null;
    }



    public Queue<AState> getStructure() {
        return new LinkedList<>();
    }

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
