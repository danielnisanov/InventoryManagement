package algorithms.search;

public interface ISearchingAlgorithm {
    public Solution search(ISearchable s);
    public int getNumVisitedNodes();
}
