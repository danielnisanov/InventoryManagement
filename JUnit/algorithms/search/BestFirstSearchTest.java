package algorithms.search;
import algorithms.search.*;
import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    @Test
    void testBestFirstSearch_7x7Maze() {
        MyMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(7, 7);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFirstSearch = new BestFirstSearch();

        Solution solution = bestFirstSearch.solve(searchableMaze);

        assertNotNull(solution);
        assertTrue(solution.getSolutionPath().size() > 0);
        assertEquals(maze.getStartPosition().toString(), solution.getSolutionPath().get(0).toString());
        assertEquals(maze.getGoalPosition().toString(), solution.getSolutionPath().get(solution.getSolutionPath().size() - 1).toString());
    }

    @Test
    void testBestFirstSearch_NoSolution() {
        Maze maze = new Maze(3, 3);
        maze.setStartPosition(new Position(0, 0));
        maze.setGoalPosition(new Position(2, 2));

        // Create walls blocking the path
        maze.setOne(0, 1);
        maze.setOne(1, 1);
        maze.setOne(1, 0);

        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFirstSearch = new BestFirstSearch();

        Solution solution = bestFirstSearch.solve(searchableMaze);

        assertNull(solution);
    }

    @Test
    void testBestFirstSearch_Simple3x3Maze() {
        Maze maze = new Maze(3, 3);
        maze.setStartPosition(new Position(0, 0));
        maze.setGoalPosition(new Position(2, 2));

        // Ensure there's a clear path
        maze.setZero(0, 1);
        maze.setZero(1, 0);
        maze.setZero(1, 1);
        maze.setZero(1, 2);
        maze.setZero(2, 1);

        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFirstSearch = new BestFirstSearch();

        Solution solution = bestFirstSearch.solve(searchableMaze);

        assertNotNull(solution);
        assertTrue(solution.getSolutionPath().size() > 0);
        assertEquals(maze.getStartPosition().toString(), solution.getSolutionPath().get(0).toString());
        assertEquals(maze.getGoalPosition().toString(), solution.getSolutionPath().get(solution.getSolutionPath().size() - 1).toString());
    }

    @Test
    void testBestFirstSearch_NullSearchable() {
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        assertThrows(NullPointerException.class, () -> {
            bestFirstSearch.solve(null);
        });
    }

    @Test
    void testBestFirstSearch_NonSquareMaze() {
        MyMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(5, 10);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFirstSearch = new BestFirstSearch();

        Solution solution = bestFirstSearch.solve(searchableMaze);

        assertNotNull(solution);
        assertTrue(solution.getSolutionPath().size() > 0);
        assertEquals(maze.getStartPosition(), ((AState) solution.getSolutionPath().get(0)).getCurrentPos());
        assertEquals(maze.getGoalPosition(), ((AState) solution.getSolutionPath().get(solution.getSolutionPath().size() - 1)).getCurrentPos());
    }

}