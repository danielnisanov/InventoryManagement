package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{
    public BestFirstSearch() {
        super();
    }

    @Override
    public Queue<AState> getStructure() {
        return new PriorityQueue<>();
    }

    @Override
    public String getName() {
        return "BestFirstSearch";
    }

}
