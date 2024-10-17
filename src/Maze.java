/**
 * This class represents the maze.
 *
 * Class: CS2210
 * Date: Dec 7 2023
 * @author James Wong
 */

import java.util.*;
import java.io.*;
import java.util.Stack;

public class Maze {
    int coins;
    private int start;
    private int end;
    private int horizontalEdges = 0;
    private int verticalEdges = 0;
    Graph maze;
    private Stack<GraphNode> stack = new Stack<>();

    public Maze(String inputFile) throws MazeException, GraphException, NumberFormatException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            reader.readLine();

            int width = Integer.parseInt(reader.readLine());
            int height = Integer.parseInt(reader.readLine());
            coins = Integer.parseInt(reader.readLine());
            maze = new Graph(width * height);

            int lineCount = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                processLine(line, width, verticalEdges, horizontalEdges, lineCount);
            }
        } catch (FileNotFoundException e) {
            throw new MazeException("Invalid input file");
        }
    }

    private void processLine(String line, int width, int verticalEdges, int horizontalEdges, int lineCount) throws GraphException {
        int length = line.length();

        for (int i = 0; i < length; i++) {
            char currentChar = line.charAt(i);

            if (lineCount % 2 == 0 && i % 2 == 0) {
                if (currentChar == 'w') {
                    verticalEdges++;
                } else if (currentChar == 'c') {
                    maze.insertEdge(maze.getNode(verticalEdges), maze.getNode(verticalEdges + width), 0, "corridor");
                    verticalEdges++;
                } else if (Character.isDigit(currentChar)) {
                    maze.insertEdge(maze.getNode(verticalEdges), maze.getNode(verticalEdges + width), Character.getNumericValue(currentChar), "door");
                    verticalEdges++;
                }
            }

            if (lineCount % 2 == 1) {
                if (currentChar == 's') {
                    start = horizontalEdges;
                } else if (currentChar == 'x') {
                    end = horizontalEdges;
                }

                if (i % 2 == 1) {
                    if (currentChar == 'w') {
                        horizontalEdges++;
                    } else if (currentChar == 'c') {
                        maze.insertEdge(maze.getNode(horizontalEdges), maze.getNode(horizontalEdges + 1), 0, "corridor");
                        horizontalEdges++;
                    } else if (Character.isDigit(currentChar)) {
                        maze.insertEdge(maze.getNode(horizontalEdges), maze.getNode(horizontalEdges + 1), Character.getNumericValue(currentChar), "door");
                        horizontalEdges++;
                    }
                }
            }
        }

        updateEdgesCount(lineCount, width);
    }

    /**
     * updateEdgesCount
     * @param lineCount
     * @param width
     */
    private void updateEdgesCount(int lineCount, int width) {
        if (lineCount % 2 == 0) {
            verticalEdges = (lineCount / 2) * width;
        } else {
            horizontalEdges = ((lineCount / 2) + 1) * width;
        }
    }

    /**
     * getGraph
     * @return graph
     * @throws MazeException if graph is null
     */
    public Graph getGraph() throws MazeException {
        if (maze == null){
            throw new MazeException("maze does not exist");
        }
        return maze;
    }

    /**
     * solve
     * @return null
     * @throws GraphException
     */
    public Iterator<GraphNode> solve() throws GraphException {

        if (dfs(coins, maze.getNode(start), maze.getNode(end))){
            return stack.iterator();
        }
        return null;
    }

    /**
     * dfs
     * @param current
     * @param endNode
     * @param coins
     * @return true or false
     * @throws GraphException
     */
    private boolean dfs(int coins, GraphNode current, GraphNode endNode) throws GraphException {

        stack.push(current);
        if (current == endNode){
            return true;
        }

        current.mark(true);
        Iterator<GraphEdge> edges = maze.incidentEdges(current);
        while (edges.hasNext()) {
            GraphEdge curEdge = edges.next();
            boolean canMove = !curEdge.secondEndpoint().isMarked() && curEdge.getType() <= coins;

            if (canMove) {
                int remainingCoins = coins-curEdge.getType();

                if (dfs(remainingCoins, curEdge.secondEndpoint(), endNode)) {
                    return true;
                }
            }
        }
        current.mark(false);
        stack.pop();
        return false;
    }
}