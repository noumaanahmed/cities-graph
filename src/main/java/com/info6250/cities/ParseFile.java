/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.info6250.cities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author noumaanahmed
 */


public class ParseFile {
    
    
    
    

    public static void main(String[] args) {

        // Path to the CSV file with city data
        String csvFilePath = "indian-cities-dataset 2.csv";

        // Create a graph from CSV data
        Graph<String, DefaultWeightedEdge> graph = parseCsv(csvFilePath);

        // Extract city names from the graph
        List<String> vertices = new ArrayList<>(graph.vertexSet());

        // Convert graph to adjacency matrix
        double[][] matrix = parseCsvToMatrix(csvFilePath, graph);

        // Output files with graph data
        writeGraphToFile(graph, "graph_output_813pm.txt");
        writeMatrixToFile(matrix, "matrix_output.xls");

        // Visualize the graph
        VisualizeGraph.visualizeAndExportGraph(graph, "graph_image.jpg");

        // Menu to choose graph algorithms
        runGraphAlgorithms(vertices, matrix);
    }
    
    
    
    

    // Parse CSV to construct a graph
    private static Graph<String, DefaultWeightedEdge> parseCsv(String csvFilePath) {
        Graph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        try (Reader reader = new FileReader(csvFilePath)) {
            System.out.println("Parsing CSV file: " + csvFilePath);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            for (CSVRecord record : parser) {
                String origin = record.get("Origin");
                String destination = record.get("Destination");
                double distance = Double.parseDouble(record.get("Distance"));
                if (!graph.containsVertex(origin)) {
                    graph.addVertex(origin);
                }
                if (!graph.containsVertex(destination)) {
                    graph.addVertex(destination);
                }
                DefaultWeightedEdge edge = graph.addEdge(origin, destination);
                if (edge != null) {
                    graph.setEdgeWeight(edge, distance);
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred while parsing the CSV file:");
            e.printStackTrace();
        }
        return graph;
    }

    
    
    
    
    

    // Convert CSV data to a matrix for graph algorithms
    private static double[][] parseCsvToMatrix(String csvFilePath, Graph<String, DefaultWeightedEdge> graph) {
        List<String> vertices = new ArrayList<>(graph.vertexSet());
        double[][] matrix = new double[vertices.size()][vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            matrix[i][i] = 0; // Distance from a vertex to itself is 0
            for (int j = 0; j < vertices.size(); j++) {
                if (i != j) {
                    matrix[i][j] = Double.POSITIVE_INFINITY; // Initialize with infinity
                }
            }
        }
        try (Reader reader = new FileReader(csvFilePath)) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            for (CSVRecord record : parser) {
                String origin = record.get("Origin");
                String destination = record.get("Destination");
                double distance = Double.parseDouble(record.get("Distance"));
                int originIndex = vertices.indexOf(origin);
                int destinationIndex = vertices.indexOf(destination);
                matrix[originIndex][destinationIndex] = distance;
            }
        } catch (Exception e) {
            System.err.println("An error occurred while parsing the CSV file to matrix:");
            e.printStackTrace();
        }
        return matrix;
    }
    
    
    
    
    

    // Write graph details to a text file
    private static void writeGraphToFile(Graph<String, DefaultWeightedEdge> graph, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String vertex : graph.vertexSet()) {
                writer.write("Vertex " + vertex + " is connected to:");
                writer.newLine();
                graph.outgoingEdgesOf(vertex).forEach(edge -> {
                    try {
                        String target = graph.getEdgeTarget(edge);
                        double weight = graph.getEdgeWeight(edge);
                        writer.write(" - " + target + " with weight " + weight);
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.write("==============================");
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing graph to file: " + e.getMessage());
        }
    }
    
    
    
    
    

    // Write the graph matrix to a file
    private static void writeMatrixToFile(double[][] matrix, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (double[] row : matrix) {
                for (double val : row) {
                    writer.write((val == Double.POSITIVE_INFINITY ? "Inf" : Double.toString(val)) + "\t");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing matrix to file: " + e.getMessage());
        }
    }
    
    
    
    

    // Method to run graph algorithms based on user input
    private static void runGraphAlgorithms(List<String> vertices, double[][] matrix) {
        Scanner scanner = new Scanner(System.in);
        String continueRunning;
        do {
            System.out.println("Graph Algorithms Menu:");
            System.out.println("1. Find single-source shortest path from a vertex (Dijkstra's Algorithm)");
            System.out.println("2. Calculate shortest paths between all pairs of cities (Floyd-Warshall Algorithm)");
            System.out.println("3. Construct a minimum spanning tree connecting all cities (Kruskal's Algorithm)");
            System.out.println("4. Construct a minimum spanning tree from a root city (Prim's Algorithm)");
            System.out.println("5. Explore the network breadth-first from a given city (BFS)");
            System.out.println("6. Explore all possible paths from one city to another (DFS)");
            System.out.print("Please enter your choice (1-6): ");
            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice) {
                case 1:
                    RunDijkstra.runDijkstraInteractive(vertices, matrix);
                    break;
                case 2:
                    RunFloydWarshall.runFloydWarshall(vertices, matrix);
                    break;
                case 3:
                    RunKruskal.runKruskal(vertices, matrix);
                    break;
                case 4:
                    RunPrim.runPrim(vertices, matrix);
                    break;
                case 5:
                    RunBFS.runBFS(vertices, matrix);
                    break;
                case 6:
                    RunDFS.runDFS(vertices, matrix);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 6.");
                    break;
            }
            System.out.println("");
            System.out.println("=================");
            System.out.println("Do you want to run another algorithm? (y/n)");
            continueRunning = scanner.nextLine().trim().toLowerCase();
        } while (continueRunning.equals("y"));

        scanner.close();
        System.out.println("Exiting program.");
        System.exit(0);
    }
}
