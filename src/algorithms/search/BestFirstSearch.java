package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    @Override
    public Queue<AState> getDataStructure() {
        return new PriorityQueue<>();
    }

}
