package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    /**
     * Override of generate function - returns an instance of Maze using DFS algorithm.
     */
    @Override
    public Maze generate(int rows, int columns) {
        Maze newMaze = new Maze(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newMaze.setOne(i, j);
            }
        }
        // Initialize start and end positions
        Position start = new Position(0, 0);
        Position end = new Position(rows - 1, columns - 1);

        Random random = new Random();

        // Create stack for DFS algorithm
        Stack<Position> stack = new Stack<>();
        stack.push(start);
        newMaze.setZero(start);

        List<Position> neighbors;
        while (!stack.isEmpty()) {
            Position current = stack.pop();
            newMaze.setZero(current);
            neighbors = getUnvisitedNeighbors(newMaze, current);
            if (!neighbors.isEmpty()) {
                stack.push(current);
                Collections.shuffle(neighbors, random);
                Position next = neighbors.get(0);
                newMaze.moveDFS(current, next);
                stack.push(next);
            }
        }

        // Ensure all boundary cells are connected if maze size is even
        connectBoundaryCells(newMaze);

        // Add some randomness to maze paths and walls
        addRandomPathsAndWalls(newMaze);

        // Ensure end position has path neighbors
        ensureEndPath(newMaze, end);

        return newMaze;
    }

    private List<Position> getUnvisitedNeighbors(Maze maze, Position current) {
        int row = current.getRowIndex();
        int col = current.getColumnIndex();
        List<Position> neighbors = new ArrayList<>();

        // Check right neighbor
        if (col + 2 < maze.getColumns() && maze.getMaze()[row][col + 2] == 1) {
            neighbors.add(new Position(row, col + 2));
        }
        // Check left neighbor
        if (col - 2 >= 0 && maze.getMaze()[row][col - 2] == 1) {
            neighbors.add(new Position(row, col - 2));
        }
        // Check down neighbor
        if (row + 2 < maze.getRows() && maze.getMaze()[row + 2][col] == 1) {
            neighbors.add(new Position(row + 2, col));
        }
        // Check up neighbor
        if (row - 2 >= 0 && maze.getMaze()[row - 2][col] == 1) {
            neighbors.add(new Position(row - 2, col));
        }

        return neighbors;
    }

    /**
     * Connect boundary cells to ensure all parts of the maze are reachable.
     */
    private void connectBoundaryCells(Maze maze) {
        int rows = maze.getRows();
        int columns = maze.getColumns();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Check and connect top row cells if they are isolated
                if (i == 0 && maze.getMaze()[i][j] == 1) {
                    if (j > 0 && maze.getMaze()[i][j - 1] == 0) {
                        maze.setZero(i, j);
                    }
                    if (j < columns - 1 && maze.getMaze()[i][j + 1] == 0) {
                        maze.setZero(i, j);
                    }
                }
                // Check and connect bottom row cells if they are isolated
                if (i == rows - 1 && maze.getMaze()[i][j] == 1) {
                    if (j > 0 && maze.getMaze()[i][j - 1] == 0) {
                        maze.setZero(i, j);
                    }
                    if (j < columns - 1 && maze.getMaze()[i][j + 1] == 0) {
                        maze.setZero(i, j);
                    }
                }
                // Check and connect left column cells if they are isolated
                if (j == 0 && maze.getMaze()[i][j] == 1) {
                    if (i > 0 && maze.getMaze()[i - 1][j] == 0) {
                        maze.setZero(i, j);
                    }
                    if (i < rows - 1 && maze.getMaze()[i + 1][j] == 0) {
                        maze.setZero(i, j);
                    }
                }
                // Check and connect right column cells if they are isolated
                if (j == columns - 1 && maze.getMaze()[i][j] == 1) {
                    if (i > 0 && maze.getMaze()[i - 1][j] == 0) {
                        maze.setZero(i, j);
                    }
                    if (i < rows - 1 && maze.getMaze()[i + 1][j] == 0) {
                        maze.setZero(i, j);
                    }
                }
            }
        }
    }

    /**
     * Add random walls or paths (only instead 1 - not damage the path we created).
     */
    private void addRandomPathsAndWalls(Maze maze) {
        Random random = new Random();
        int numRandomChanges = (int) Math.sqrt(maze.getRows() * maze.getColumns());

        for (int i = 0; i < numRandomChanges; i++) {
            int randRow = random.nextInt(maze.getRows());
            int randCol = random.nextInt(maze.getColumns());
            if (maze.getMaze()[randRow][randCol] == 1) {
                maze.getMaze()[randRow][randCol] = random.nextInt(2);
            }
        }
    }

    /**
     * Function to ensure that there is a path to the end point.
     */
    private void ensureEndPath(Maze maze, Position end) {
        int endRow = end.getRowIndex();
        int endCol = end.getColumnIndex();

        if (endRow + 1 < maze.getRows() && maze.getMaze()[endRow + 1][endCol] == 1) {
            maze.getMaze()[endRow + 1][endCol] = 0;
        }
        if (endCol - 1 >= 0 && maze.getMaze()[endRow][endCol - 1] == 1) {
            maze.getMaze()[endRow][endCol - 1] = 0;
        }
        if (endRow - 1 >= 0 && maze.getMaze()[endRow - 1][endCol] == 1) {
            maze.getMaze()[endRow - 1][endCol] = 0;
        }
        if (endCol + 1 < maze.getColumns() && maze.getMaze()[endRow][endCol + 1] == 1) {
            maze.getMaze()[endRow][endCol + 1] = 0;
        }
    }

    /**
     * Boolean function to check if position is valid.
     */
    private boolean isValidPosition(Maze maze, Position position) {
        int row = position.getRowIndex();
        int col = position.getColumnIndex();
        return row >= 0 && row < maze.getRows() && col >= 0 && col < maze.getColumns();
    }
}

//
//package algorithms.mazeGenerators;
//
//import java.util.*;
//
//public class MyMazeGenerator extends AMazeGenerator {
//    /**
//     * @param rows represents the number of rows that will be on our maze.
//     * @param columns represents the number of columns that will be on our maze.
//     * @return new maze that generated base on randomized Prim's algorithm.
//     */
//    @Override
//    public Maze generate(int rows, int columns) {
//        if (rows < 1 || columns < 1) return new Maze(1, 1);
//        Maze newMaze = new Maze(rows, columns);
//        Stack<Position> path = new Stack<>();
//        ArrayList<Position> neighbours;
//        ArrayList<Position> fixedNeighbours = new ArrayList<>();
//        Position currCell;
//        for (int i = 0; i < rows; i++)
//            for (int j = 0; j < columns; j++)
//                newMaze.setOne(i, j);
//        newMaze.setZero(0, 0);
//        path.push(newMaze.getStartPosition());
//        while (!path.isEmpty())
//        {
//            fixedNeighbours.clear();
//            currCell = path.pop();
//            if(currCell.equals(newMaze.getGoalPosition()))
//                continue;
//            neighbours = findWalls(newMaze, currCell);
//            for(Position position : neighbours)
//                if(getDivided(newMaze, position) == 1) fixedNeighbours.add(position);
//            if(fixedNeighbours.size() >= 1)
//            {
//                path.push(currCell);
//                currCell = pickRandomPosition(fixedNeighbours);
//                newMaze.setZero(currCell.getRowIndex(), currCell.getColumnIndex());
//                path.push(currCell);
//            }
//        }
//        if(newMaze.getValue(newMaze.getGoalPosition().getRowIndex(), newMaze.getGoalPosition().getColumnIndex()) == 1) return generate(rows, columns);
//        return newMaze;
//    }
//
//    /**
//     * @param maze the maze in which to look for neighbors.
//     * @param position the position to check.
//     * @return the number of neighbors that are passages.
//     */
//    private int getDivided(Maze maze, Position position)
//    {
//        int divided = 0;
//        if(maze.getValue(position.getRowIndex() + 1, position.getColumnIndex()) == 0) // DOWN
//            divided ++;
//        if(maze.getValue(position.getRowIndex() - 1, position.getColumnIndex()) == 0) // UP
//            divided ++;
//        if(maze.getValue(position.getRowIndex(), position.getColumnIndex() + 1) == 0) // RIGHT
//            divided ++;
//        if(maze.getValue(position.getRowIndex(), position.getColumnIndex() - 1) == 0) // LEFT
//            divided ++;
//        return divided;
//    }
//
//    /**
//     * @param maze the maze in which to look for walls.
//     * @param position the position to check.
//     * @return an ArrayList containing all the walls around the given position.
//     */
//    private ArrayList<Position> findWalls(Maze maze, Position position)
//    {
//        ArrayList<Position> wallsList = new ArrayList<>();
//        if(maze.getValue(position.getRowIndex() + 1, position.getColumnIndex()) == 1) // UP
//            wallsList.add(new Position(position.getRowIndex() + 1, position.getColumnIndex()));
//        if(maze.getValue(position.getRowIndex() - 1, position.getColumnIndex()) == 1) // DOWN
//            wallsList.add(new Position(position.getRowIndex() - 1, position.getColumnIndex()));
//        if(maze.getValue(position.getRowIndex(), position.getColumnIndex() + 1) == 1) // RIGHT
//            wallsList.add(new Position(position.getRowIndex(), position.getColumnIndex() + 1));
//        if(maze.getValue(position.getRowIndex(), position.getColumnIndex() - 1) == 1) // LEFT
//            wallsList.add(new Position(position.getRowIndex(), position.getColumnIndex() - 1));
//        return wallsList;
//    }
//    private Position pickRandomPosition(ArrayList<Position> positions)
//    {
//        Random random = new Random();
//        return positions.get(random.nextInt(positions.size()));
//    }
//}