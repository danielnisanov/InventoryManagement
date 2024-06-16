package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{

    public MazeState(Position currentPos, double cost, AState cameFrom) {
        super(currentPos, cost, cameFrom);
    }

}
