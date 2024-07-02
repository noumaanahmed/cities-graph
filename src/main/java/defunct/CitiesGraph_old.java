package defunct;

//package com.info6250.cities;
//
//import org.jgrapht.Graph;
//import org.jgrapht.graph.DefaultDirectedWeightedGraph;
//import org.jgrapht.graph.DefaultWeightedEdge;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//public class CitiesGraph {
//
//    public static void main(String[] args) {
//        System.out.println("Parsing CSV file.");
//        String csvFilePath = "/path/to/your/dataset.csv"; // Update with your dataset path
//        List<Edge> edges = ParseFile.parseCsv(csvFilePath);
//        System.out.println("CSV file parsed. Number of records: " + edges.size());
//
//        // Creating a directed weighted graph
//        Graph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
//        System.out.println("Building graph.");
//
//        // Building the graph
//        for (Edge edge : edges) {
//            String source = edge.getOrigin();
//            String target = edge.getDestination();
//            double distance = edge.getDistance();
//
//            // Ensure all vertices exist in the graph
//            graph.addVertex(source);
//            graph.addVertex(target);
//
//            // Add and weight the edge
//            DefaultWeightedEdge transportationEdge = graph.addEdge(source, target);
//            if (transportationEdge != null) {
//                graph.setEdgeWeight(transportationEdge, distance);
//            }
//        }
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/path/to/graph_output.txt"))) {
//            writer.write("Graph populated. Total vertices: " + graph.vertexSet().size());
//            writer.newLine();
//            writer.write("Graph populated. Total edges: " + graph.edgeSet().size());
//            writer.newLine();
//
//            // Iterate over the vertices and edges to write the graph structure to the file
//            for (String vertex : graph.vertexSet()) {
//                writer.write("Vertex " + vertex + " is connected to:");
//                writer.newLine();
//                for (DefaultWeightedEdge edge : graph.edgesOf(vertex)) {
//                    String target = graph.getEdgeTarget(edge);
//                    double weight = graph.getEdgeWeight(edge);
//                    writer.write(" - " + target + " with weight " + weight);
//                    writer.newLine();
//                }
//                writer.write("==============================");
//                writer.newLine();
//            }
//
//            writer.write("Graph visualization finished.");
//            writer.newLine();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Graph output has been written to graph_output.txt");
//    }
//
//    // Class to represent the details of an edge
//    public static class Edge {
//        private String origin;
//        private String destination;
//        private double distance;
//
//        public Edge(String origin, String destination, double distance) {
//            this.origin = origin;
//            this.destination = destination;
//            this.distance = distance;
//        }
//
//        public String getOrigin() {
//            return origin;
//        }
//
//        public String getDestination() {
//            return destination;
//        }
//
//        public double getDistance() {
//            return distance;
//        }
//    }
//}
