


package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Map;

public class SearchableMaze implements ISearchable {
    private Maze maze;
    private MazeState start;
    private MazeState end;

    /**
     * @param maze the maze object to make searchable
     */
    public SearchableMaze(Maze maze) {
        this.end = new MazeState(maze.getGoalPosition(), 0, null);
        this.start = new MazeState(maze.getStartPosition(), 0, null);
        this.maze = maze;
    }

    public int getMazeRows() {
        return maze.getRows();
    }

    public int getMazeColumns() {
        return maze.getColumns();
    }

    @Override
    public AState getStartState() {
        return this.start;
    }

    @Override
    public AState getGoalState() {
        return this.end;
    }



    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        ArrayList<AState> allPossibleStates = new ArrayList<>();
        if (s == null)
            return allPossibleStates;
        Position currentPosition = (Position) s.getCurrentPos();
        Position up = new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex());
        Position down = new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex());
        Position right = new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() + 1);
        Position left = new Position(currentPosition.getRowIndex(), currentPosition.getColumnIndex() - 1);
        Position upRight = new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex() + 1);
        Position downRight = new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex() + 1);
        Position upLeft = new Position(currentPosition.getRowIndex() - 1, currentPosition.getColumnIndex() - 1);
        Position downLeft = new Position(currentPosition.getRowIndex() + 1, currentPosition.getColumnIndex() - 1);

        //straight steps

        //up
        if (maze.isValidPosition(up) && maze.getValue(up) == 0)
            allPossibleStates.add(new MazeState(up, s.getCost() + 10, s));

        //down
        if (maze.isValidPosition(down) && maze.getValue(down) == 0)
            allPossibleStates.add(new MazeState(down, s.getCost() + 10, s));
        //left
        if (maze.isValidPosition(left) && maze.getValue(left) == 0)
            allPossibleStates.add(new MazeState(left, s.getCost() + 10, s));
        //right
        if (maze.isValidPosition(right) && maze.getValue(right) == 0)
            allPossibleStates.add(new MazeState(right, s.getCost() + 10, s));

        //diagonal steps

        //upRight
        if (maze.isValidPosition(upRight) && maze.isValidPosition(up) && maze.isValidPosition(right) && maze.getValue(upRight) == 0 && (maze.getValue(up) == 0 || maze.getValue(right) == 0))
            allPossibleStates.add(new MazeState(upRight, s.getCost() + 15, s));
        //upLeft
        if (maze.isValidPosition(upLeft) && maze.isValidPosition(up) && maze.isValidPosition(left) && maze.getValue(upLeft) == 0 && (maze.getValue(up) == 0 || maze.getValue(left) == 0))
            allPossibleStates.add(new MazeState(upLeft, s.getCost() + 15, s));
        //downRight
        if (maze.isValidPosition(downRight) && maze.isValidPosition(down) && maze.isValidPosition(right) && maze.getValue(downRight) == 0 && (maze.getValue(down) == 0 || maze.getValue(right) == 0))
            allPossibleStates.add(new MazeState(downRight, s.getCost() + 15, s));
        //downLeft
        if (maze.isValidPosition(downLeft) && maze.isValidPosition(down) && maze.isValidPosition(left) && maze.getValue(downLeft) == 0 && (maze.getValue(down) == 0 || maze.getValue(left) == 0))
            allPossibleStates.add(new MazeState(downLeft, s.getCost() + 15, s));

        return allPossibleStates;
    }
}