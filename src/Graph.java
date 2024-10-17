import java.util.Arrays;
import java.util.Iterator;

public class Graph implements GraphADT{
    GraphEdge matrix[][];
    GraphNode nodes[];
    int length;

    /**
     * Graph
     * @param n parameters of the matrix and node
     */
    public Graph(int n) {
        length = n;
        nodes = new GraphNode[length];
        matrix = new GraphEdge[length][length];

        int i = 0;
        while (i < n) {
            nodes[i] = new GraphNode(i);
            i++;
        }
    }

    /**
     * insertEdge
     * @param u source node
     * @param v destination node
     * @param edgeType
     * @param label
     * @throws GraphException
     */
    public void insertEdge(GraphNode u, GraphNode v, int edgeType, String label) throws GraphException{
        int startIndex = u.getName();
        int destinationIndex = v.getName();

        try {
            if (matrix[startIndex][destinationIndex] == null) {
                matrix[startIndex][destinationIndex] = new GraphEdge(u, v, edgeType, label);
                matrix[destinationIndex][startIndex] = new GraphEdge(v, u, edgeType, label);
            }else{
                throw new GraphException("The node exist");
            }
        }catch (Exception e){
            throw new GraphException("The node does not exist");
        }
    }

    /**
     * getNode
     * @param name the specific name
     * @return the node with the name in the parameter
     * @throws GraphException if the node does not exist
     */
    public GraphNode getNode(int name) throws GraphException{
        for (int i = 0; i < nodes.length; i++){
            if (nodes[i].getName() == name){
                return nodes[i];
            }
        }
        throw new GraphException("Node does not exist");
    }

    /**
     * incidentEdges
     * @param u node
     * @return null if u does not have any edges incident on it. If u is not a node of the graph a GraphException must be thrown.
     * @throws GraphException if the node does not exist
     */
    public Iterator incidentEdges(GraphNode u) throws GraphException{
        int edgeCount = 0;
        int arraySize = 0;
        try {
            int nodeIndex = u.getName();
            for (int i = 0; i < matrix[nodeIndex].length; i++) {
                if (matrix[nodeIndex][i] != null) {
                    arraySize++;
                }
            }

            GraphEdge[] edges = new GraphEdge[arraySize];

            for (int i = 0; i < matrix[nodeIndex].length; i++) {
                if (matrix[nodeIndex][i] != null) {
                    edges[edgeCount++] = matrix[nodeIndex][i];
                }
            }

            if (edgeCount == 0) {
                return null;
            }

            return Arrays.stream(edges, 0, edgeCount).iterator();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GraphException("Node does not exist in the graph.");
        }
    }

    /**
     * getEdge
     * @param u connecting node
     * @param v connecting node
     * @return edge
     * @throws GraphException node does not exist or no edge between two nodes
     */
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
        int sourceIndex = u.getName();
        int destinationIndex = v.getName();

        if (sourceIndex < 0 || sourceIndex >= this.nodes.length || destinationIndex < 0 || destinationIndex >= this.nodes.length) {
            throw new GraphException("One of the nodes does not exist.");
        }

        GraphEdge edge = this.matrix[sourceIndex][destinationIndex];

        if (edge == null) {
            throw new GraphException("There is no edge between the two nodes.");
        }

        return edge;
    }

    /**
     * areAdjacent
     * @param u node
     * @param v node
     * @return true if nodes are adjacent, false otherwise
     * @throws GraphException
     */
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
        try {
            int indexU = u.getName();
            int indexV = v.getName();

            if (indexU < 0 || indexU >= this.nodes.length || indexV < 0 || indexV >= this.nodes.length) {
                throw new GraphException("One of the nodes does not exist in this graph.");
            }

            return matrix[indexU][indexV] != null;
        } catch (Exception e) {
            throw new GraphException("An error occurred while checking adjacency.");
        }
    }
}