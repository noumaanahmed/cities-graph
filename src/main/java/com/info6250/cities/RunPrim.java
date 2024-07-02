/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.cities;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author noumaanahmed
 */
public class RunPrim {
    // Function to construct and print MST for a graph represented using adjacency matrix representation
    public static void runPrim(List<String> vertices, double[][] graph) {
        int V = vertices.size();
        int[] parent = new int[V]; // Array to store constructed MST
        double[] key = new double[V]; // Key values used to pick minimum weight edge in cut
        Boolean[] mstSet = new Boolean[V]; // To represent set of vertices included in MST

        // Initialize all keys as INFINITE and mstSet[] as false
        Arrays.fill(key, Double.POSITIVE_INFINITY);
        Arrays.fill(mstSet, false);

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick th minimum key vertex from the set of vertices not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent vertices of the picked vertex.
            for (int v = 0; v < V; v++) {
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the constructed MST
        printMST(parent, graph, vertices);
    }

    // A utility function to find the vertex with minimum key value, from the set of vertices not yet included in MST
    private static int minKey(double[] key, Boolean[] mstSet) {
        double min = Double.POSITIVE_INFINITY;
        int minIndex = -1;

        for (int v = 0; v < key.length; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }

        return minIndex;
    }

    // A utility function to print the constructed MST stored in parent[]
    private static void printMST(int[] parent, double[][] graph, List<String> vertices) {
        System.out.println("");
        System.out.println("=================");
        System.out.println("Edge \tWeight");
        for (int i = 1; i < vertices.size(); i++)
            System.out.println(vertices.get(parent[i]) + " - " + vertices.get(i) + "\t" + graph[i][parent[i]]);
    }
}
