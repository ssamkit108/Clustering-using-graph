public class Edge {

    private int length = 0;         //weight of edge

    private final String vertexA;   //Starting vertex of Edge
    private final String vertexB;   //Ending vertex of Edge

    //Copy constructor for edge
    Edge(Edge e) {
        this.vertexA = e.vertexA;
        this.vertexB = e.vertexB;
        this.length = e.length;
    }

    //Constructor
    Edge(String vertexA, String vertexB, int length) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.length = length;
    }

    //return a length of edge
    public int getLength() {
        return length;
    }

    //return starting vertex
    public String getVertexA() {
        return vertexA;
    }

    //return ending vertex
    public String getVertexB() {
        return vertexB;
    }

    //Return true if vertex is adjacent to other vertex
    public boolean isAdjacentTo(String vertex) {
        return vertexA == vertex || vertexB == vertex;
    }

    //user to set a new length for edge
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Edge [" + vertexA + " ->" + vertexB + ",length=" + length + "]";
    }
}
