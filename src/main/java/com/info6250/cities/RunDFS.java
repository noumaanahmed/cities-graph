/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.info6250.cities;

import java.util.*;

/**
 *
 * @author noumaanahmed
 */

public class RunDFS {

    // Function to perform Depth First Search using an adjacency matrix
    public static void runDFS(List<String> vertices, double[][] graph) {
        System.out.println("");
        System.out.println("=================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the source vertex for DFS:");
        String sourceVertex = scanner.nextLine();

        // Check if the vertex exists in the list
        int startNode = vertices.indexOf(sourceVertex);
        if (startNode == -1) {
            System.out.println("Vertex not found in the graph.");
            return;
        }

        // Array to keep track of visited nodes
        boolean[] visited = new boolean[vertices.size()];

        System.out.println("DFS starting from vertex: " + sourceVertex);

        // Start DFS from the given source vertex
        dfsUtil(startNode, visited, vertices, graph);
        System.out.println();
    }

    // Recursive function to perform the DFS traversal
    private static void dfsUtil(int v, boolean[] visited, List<String> vertices, double[][] graph) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(vertices.get(v) + " " + " -- ");

        // Recur for all the vertices adjacent to this vertex
        for (int i = 0; i < vertices.size(); i++) {
            if (graph[v][i] != Double.POSITIVE_INFINITY && !visited[i]) {
                dfsUtil(i, visited, vertices, graph);
            }
        }
    }
}
