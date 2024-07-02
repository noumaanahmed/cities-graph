/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.cities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author noumaanahmed
 */
public class RunFloydWarshall {
    
    

    /*   2. Floyd-Warshall Algorithm  */


    // Run the Floyd-Warshall algorithm
    public static void runFloydWarshall(List<String> vertices, double[][] matrix) {
        int V = matrix.length;
        double[][] dist = new double[V][V];
        String[][] next = new String[V][V];

        // Initialize matrices for distance and next hop
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = matrix[i][j];
                if (matrix[i][j] != Double.POSITIVE_INFINITY && i != j) {
                    next[i][j] = vertices.get(j);
                } else {
                    next[i][j] = "-";
                }
            }
        }

        // Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        printSolutionFloydWarshall(dist, vertices, next);
    }

    // Print the matrix of shortest paths
    private static void printSolutionFloydWarshall(double[][] dist, List<String> vertices, String[][] next) {
                System.out.println("");
        System.out.println("=================");
        System.out.println("The following matrix shows the shortest distances between every pair of cities:");
        System.out.print("    ");
        for (String vertex : vertices) {
            System.out.printf("%-15s", vertex);
        }
        System.out.println();

        for (int i = 0; i < dist.length; i++) {
            System.out.printf("%-4s", vertices.get(i));
            for (int j = 0; j < dist[i].length; j++) {
                if (dist[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.printf("%-15s", "INF");
                } else {
                    System.out.printf("%-15.2f", dist[i][j]);
                }
            }
            System.out.println();
        }
    }

///////////////////////////////

//    private static void printSolutionFloydWarshall(double[][] dist, List<String> vertices, String[][] next) {
//        System.out.println("The following matrix shows the shortest distances between every pair of cities");
//        for (int i = 0; i < dist.length; i++) {
//            for (int j = 0; j < dist[i].length; j++) {
//                if (i != j) {
//                    System.out.print("Shortest path from " + vertices.get(i) + " to " + vertices.get(j) + " is ");
//                    if (dist[i][j] == Double.POSITIVE_INFINITY) {
//                        System.out.print("not reachable");
//                    } else {
//                        System.out.print(dist[i][j] + " with path " + getPath(i, j, next, vertices));
//                    }
//                    System.out.println();
//                }
//            }
//        }
//    }
//    
//    private static String getPath(int i, int j, String[][] next, List<String> vertices) {
//    if (next[i][j].equals ("-")) {
//        return "No path";
//    }
//    List<String> path = new ArrayList<>();
//    path.add(vertices.get(i));
//    while (i != j) {
//        i = vertices.indexOf(next[i][j]);
//        path.add(vertices.get(i));
//    }
//    return String.join(" -> ", path);
//}


///////////////////////////////



}
