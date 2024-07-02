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

public class RunBFS {

    // Function to perform Breadth First Search on a graph represented using an adjacency matrix
    public static void runBFS(List<String> vertices, double[][] graph) {
        System.out.println("");
        System.out.println("=================");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the source vertex for BFS:");
        String sourceVertex = scanner.nextLine();

        // Check if the vertex exists in the list
        int startNode = vertices.indexOf(sourceVertex);
        if (startNode == -1) {
            System.out.println("Vertex not found in the graph.");
            return;
        }

        // Array to keep track of visited nodes
        boolean[] visited = new boolean[vertices.size()];
        Queue<Integer> queue = new LinkedList<>();

        // Mark the source node as visited and enqueue it
        visited[startNode] = true;
        queue.add(startNode);

        System.out.println("BFS starting from vertex: " + sourceVertex);

        // BFS Algorithm
        while (!queue.isEmpty()) {
            // Dequeue a vertex from the queue and print it
            int currentNode = queue.poll();
            System.out.print(vertices.get(currentNode) + " " + " -- ");

            // Get all adjacent vertices of the dequeued vertex
            for (int i = 0; i < vertices.size(); i++) {
                // Check if there is an adjacent vertex and if it has not been visited yet
                if (graph[currentNode][i] != Double.POSITIVE_INFINITY && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }
}
