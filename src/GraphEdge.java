/**
 * This class represents an edge of the graph.
 *
 * Class: CS2210
 * Date: Dec 7 2023
 * @author James Wong
 */

public class GraphEdge {

    /** Attributes */
    private GraphNode u;
    private GraphNode v;
    private int type;
    private String label;

    /**
     * GraphEdge
     * @param u first end point
     * @param v second end point
     * @param type
     * @param label
     */
    public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
        this.u = u;
        this.v = v;
        this.type = type;
        this.label = label;
    }

    /**
     * firstEndpoint
     * @return u
     */
    public GraphNode firstEndpoint() {
        return u;
    }

    /**
     * secondEndpoint
     * @return v
     */
    public GraphNode secondEndpoint() {
        return v;
    }

    /**
     * getType
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * setType
     * @param newType specified edge value
     */
    public void setType(int newType) {
        this.type = newType;
    }

    /**
     * getLabel
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * setLabel
     * @param newLabel specified label value
     */
    public void setLabel(String newLabel) {
        this.label = newLabel;
    }
}
