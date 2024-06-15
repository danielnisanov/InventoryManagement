package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{
    public BestFirstSearch() {
        super();
    }

    @Override
    public Solution solve(ISearchable s) {
        PriorityQueue<AState> priorityQueue = (PriorityQueue<AState>) getDataStructure();
        HashSet<String> visitedString = new HashSet<>();
        priorityQueue.add(s.getStartState());
        visitedString.add(s.getStartState().toString());

        while (!priorityQueue.isEmpty()) {
            AState current = priorityQueue.poll();
            if (current.equals(s.getGoalState())) {
                backwardPath(current, solution);
                return solution;
            }

            List<AState> neighbors = s.getAllPossibleStates(current);
            numVisitedNodes++;
            for (AState neighbor : neighbors) {
                if (!visitedString.contains(neighbor.toString())) {
                    neighbor.setCameFrom(current);
                    priorityQueue.add(neighbor);
                    visitedString.add(neighbor.toString());
                }
            }
        }
        return null;
    }
    @Override
    public Queue<AState> getDataStructure() {
        return new PriorityQueue<>();
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
