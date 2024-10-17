/**
 * This class represent a node of the graph.
 *
 * Class: CS2210
 * Date: Dec 7 2023
 * @author James Wong
 */

public class GraphNode {

    /** Attributes */
    private int name;
    private boolean marked;


    /**
     * GraphNode
     * @param name created node with the given name
     */
    public GraphNode(int name) {
        this.name = name;
        this.marked = false;
    }

    /**
     * mark
     * @param mark boolean to specify if the node was marked or not
     */
    public void mark(boolean mark) {
        this.marked = mark;
    }

    /**
     * isMarked
     * @return marked
     */
    public boolean isMarked() {
        return this.marked;
    }

    /**
     * getName
     * @return name
     */
    public int getName() {
        return this.name;
    }




}
