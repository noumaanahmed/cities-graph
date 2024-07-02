/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.cities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author noumaanahmed
 */
public class RunDijkstra {
    
    
    /*   1. Dijkstra's Algorithm   */
    
    
    

// Run Dijkstra's algorithm interactively
    public static void runDijkstraInteractive(List<String> vertices, double[][] matrix) {
        Scanner scanner = new Scanner(System.in);
                System.out.println("");
        System.out.println("=================");
        System.out.println("Enter the source City name:");
                System.out.println("");
        String sourceVertex = scanner.nextLine();
        int srcIndex = vertices.indexOf(sourceVertex);

        if (srcIndex == -1) {
            System.out.println("City not found.");
        } else {
            runDijkstra(matrix, srcIndex, vertices);
        }
    }

    
    
    
    
    
    // Implementation of Dijkstra's Algorithm
    public static void runDijkstra(double[][] graph, int src, List<String> vertices) {
        int V = graph.length;
        double[] dist = new double[V];
        int[] prev = new int[V];
        Boolean[] sptSet = new Boolean[V];

        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(sptSet, false);
        Arrays.fill(prev, -1);

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet, V);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Double.POSITIVE_INFINITY && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }

        printSolution(dist, prev, vertices, src);
    }

    
    
    
    // Helper method to find the vertex with minimum distance
    private static int minDistance(double[] dist, Boolean[] sptSet, int V) {
        double min = Double.POSITIVE_INFINITY;
        int min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    
    
    
    
    // Print the result of Dijkstra's algorithm
    private static void printSolution(double[] dist, int[] prev, List<String> vertices, int src) {
        System.out.println("City \t\t Distance from Source \t\t Path");
        for (int i = 0; i < dist.length; i++) {
            if (i != src) {
                List<String> path = new ArrayList<>();
                for (int crawl = i; crawl != -1; crawl = prev[crawl]) {
                    path.add(vertices.get(crawl));
                }
                Collections.reverse(path);
                String pathStr = String.join(" -> ", path);
                System.out.println(vertices.get(i) + " \t\t " + (dist[i] == Double.POSITIVE_INFINITY ? "INF" : String.format("%.2f", dist[i])) + " \t\t " + pathStr);
            }
        }
    }
}
