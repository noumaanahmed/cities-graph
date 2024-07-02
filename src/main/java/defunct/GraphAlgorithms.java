package defunct;

import static defunct.ParseFile_old.parseCsv;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;








public class GraphAlgorithms {
    
/*   JGraphT implementation of algorithms  */


public static void runDijkstra(Graph<String, DefaultWeightedEdge> graph, String sourceVertex) {
 System.out.println("Dijkstra's Algorithm:");
 DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
graph.vertexSet().forEach(v -> {
System.out.println("Distance to " + v + ": " + dijkstraAlg.getPathWeight(sourceVertex, v));
 System.out.println("Path: " + dijkstraAlg.getPath(sourceVertex, v));
 System.out.println("===========================");
 });
}

    public static void runFloydWarshall(Graph<String, DefaultWeightedEdge> graph) {
        System.out.println("Floyd-Warshall Algorithm:");
        FloydWarshallShortestPaths<String, DefaultWeightedEdge> floydWarshallAlg = new FloydWarshallShortestPaths<>(graph);
        graph.vertexSet().forEach(v1 -> graph.vertexSet().forEach(v2 -> {
            if (floydWarshallAlg.getPath(v1, v2) != null) {
                System.out.println("Shortest path from " + v1 + " to " + v2 + ": " + floydWarshallAlg.getPath(v1, v2));
                System.out.println("===========================");
            }
        }));
    }

    public static void runKruskal(Graph<String, DefaultWeightedEdge> graph) {
        System.out.println("Kruskal's Minimum Spanning Tree:");
        KruskalMinimumSpanningTree<String, DefaultWeightedEdge> kruskalMST = new KruskalMinimumSpanningTree<>(graph);
        System.out.println("Total spanning tree cost: " + kruskalMST.getSpanningTree().getWeight());
        kruskalMST.getSpanningTree().getEdges().forEach(edge -> {
            System.out.println("Included in MST: " + edge + ", weight: " + graph.getEdgeWeight(edge));
        });
    }

    public static void runPrim(Graph<String, DefaultWeightedEdge> graph) {
        System.out.println("Prim's Minimum Spanning Tree:");
        PrimMinimumSpanningTree<String, DefaultWeightedEdge> primMST = new PrimMinimumSpanningTree<>(graph);
        System.out.println("Total spanning tree cost: " + primMST.getSpanningTree().getWeight());
        primMST.getSpanningTree().getEdges().forEach(edge -> {
            System.out.println("Included in MST: " + edge + ", weight: " + graph.getEdgeWeight(edge));
        });
    }

    public static void runBellmanFord(Graph<String, DefaultWeightedEdge> graph, String sourceVertex) {
        System.out.println("Bellman-Ford Algorithm:");
        BellmanFordShortestPath<String, DefaultWeightedEdge> bellmanFordAlg = new BellmanFordShortestPath<>(graph);
        graph.vertexSet().forEach(v -> {
            if (bellmanFordAlg.getPath(sourceVertex, v) != null) {
                System.out.println("Distance to " + v + ": " + bellmanFordAlg.getPathWeight(sourceVertex, v));
                System.out.println("Path: " + bellmanFordAlg.getPath(sourceVertex, v));
                System.out.println("===========================");
            }
        });
    }

    public static void runBFS(Graph<String, DefaultWeightedEdge> graph, String startVertex) {
        System.out.println("Breadth-First Search:");
        Iterator<String> bfsIterator = new BreadthFirstIterator<>(graph, startVertex);
        while (bfsIterator.hasNext()) {
            String vertex = bfsIterator.next();
            System.out.println("Visited vertex: " + vertex);
        }
    }

    public static void runDFS(Graph<String, DefaultWeightedEdge> graph, String startVertex) {
        System.out.println("Depth-First Search:");
        Iterator<String> dfsIterator = new DepthFirstIterator<>(graph, startVertex);
        while (dfsIterator.hasNext()) {
            String vertex = dfsIterator.next();
            System.out.println("Visited vertex: " + vertex);
        }
    }
    }