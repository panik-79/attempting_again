package graphs;

import java.util.*;

public class Graph {

    /**
     * Common graph definition used by ALL representations.
     */
    private static final int[][] EDGES = {
            {0, 1, 10},
            {0, 2, 15},
            {1, 3, 12},
            {2, 4, 8},
            {3, 4, 2},
            {3, 5, 5}
    };

    /**
     * Edge Structure
     */
    public static class Edge {
        public int source;
        public int destination;
        public int weight;

        public Edge(int source, int destination) {
            this(source, destination, 1);
        }

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + source + " -> " + destination + " | wt: " + weight + ")";
        }
    }

    // =========================================================================
    // 1. ADJACENCY LIST
    // =========================================================================

    public static class AdjacencyListGraph {

        private final int numVertices;
        private final ArrayList<Edge>[] list;
        private final boolean isDirected;

        @SuppressWarnings("unchecked")
        public AdjacencyListGraph(int vertices, boolean isDirected) {
            this.numVertices = vertices;
            this.isDirected = isDirected;

            this.list = new ArrayList[vertices];

            for (int i = 0; i < vertices; i++) {
                list[i] = new ArrayList<>();
            }
        }

        public void addEdge(int src, int dest) {
            addEdge(src, dest, 1);
        }

        public void addEdge(int src, int dest, int weight) {

            list[src].add(new Edge(src, dest, weight));

            if (!isDirected) {
                list[dest].add(new Edge(dest, src, weight));
            }
        }

        public void printGraph() {

            for (int i = 0; i < numVertices; i++) {

                System.out.print("Vertex " + i + " : ");

                for (Edge edge : list[i]) {
                    System.out.print(
                            edge.destination +
                                    "(wt:" + edge.weight + ") -> "
                    );
                }

                System.out.println("X");
            }
        }
    }

    // =========================================================================
    // 2. ADJACENCY MATRIX
    // =========================================================================

    public static class AdjacencyMatrixGraph {

        private final int numVertices;
        private final int[][] matrix;
        private final boolean isDirected;

        public AdjacencyMatrixGraph(int vertices, boolean isDirected) {

            this.numVertices = vertices;
            this.isDirected = isDirected;
            this.matrix = new int[vertices][vertices];
        }

        public void addEdge(int src, int dest) {
            addEdge(src, dest, 1);
        }

        public void addEdge(int src, int dest, int weight) {

            matrix[src][dest] = weight;

            if (!isDirected) {
                matrix[dest][src] = weight;
            }
        }

        public void printGraph() {

            System.out.print("     ");

            for (int i = 0; i < numVertices; i++) {
                System.out.printf("%4d", i);
            }

            System.out.println();

            for (int i = 0; i < numVertices; i++) {

                System.out.printf("%2d : ", i);

                for (int j = 0; j < numVertices; j++) {
                    System.out.printf("%4d", matrix[i][j]);
                }

                System.out.println();
            }
        }
    }

    // =========================================================================
    // 3. EDGE LIST
    // =========================================================================

    public static class EdgeListGraph {

        private final int numVertices;
        private final List<Edge> edges;

        public EdgeListGraph(int vertices) {
            this.numVertices = vertices;
            this.edges = new ArrayList<>();
        }

        public void addEdge(int src, int dest) {
            addEdge(src, dest, 1);
        }

        public void addEdge(int src, int dest, int weight) {
            edges.add(new Edge(src, dest, weight));
        }

        public void printGraph() {

            System.out.println("Vertices : " + numVertices);
            System.out.println("Edges    : " + edges.size());

            for (Edge edge : edges) {
                System.out.println(edge);
            }
        }
    }

    // =========================================================================
    // GRAPH VISUALIZATION
    // =========================================================================

    public static void printGraphVisualization() {

        System.out.println("""
        
        =====================================================
                   GRAPH VISUALIZATION
        =====================================================
        
          0 ----------(10)------------1
          |                           |
        (15)                         (12)
          |                           |
          2 ----(8)---- 4 ----(2)---- 3
                                      |
                                     (5)
                                      |
                                      5
        
        """);
    }

    public static void main(String[] args) {

        printGraphVisualization();

        int vertices = 6;

        AdjacencyListGraph adjacencyListGraph =
                new AdjacencyListGraph(vertices, false);

        AdjacencyMatrixGraph adjacencyMatrixGraph =
                new AdjacencyMatrixGraph(vertices, false);

        EdgeListGraph edgeListGraph =
                new EdgeListGraph(vertices);

        // Build SAME graph in all representations
        for (int[] edge : EDGES) {

            int src = edge[0];
            int dest = edge[1];
            int weight = edge[2];

            adjacencyListGraph.addEdge(src, dest, weight);
            adjacencyMatrixGraph.addEdge(src, dest, weight);
            edgeListGraph.addEdge(src, dest, weight);
        }

        System.out.println();
        System.out.println("=====================================================");
        System.out.println("1. ADJACENCY LIST REPRESENTATION");
        System.out.println("=====================================================");
        adjacencyListGraph.printGraph();

        System.out.println();
        System.out.println("=====================================================");
        System.out.println("2. ADJACENCY MATRIX REPRESENTATION");
        System.out.println("=====================================================");
        adjacencyMatrixGraph.printGraph();

        System.out.println();
        System.out.println("=====================================================");
        System.out.println("3. EDGE LIST REPRESENTATION");
        System.out.println("=====================================================");
        edgeListGraph.printGraph();
    }
}