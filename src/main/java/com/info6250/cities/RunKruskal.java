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


public class RunKruskal {

    static class Edge implements Comparable<Edge> {
        int src, dest;
        double weight;

        Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.weight, other.weight);
        }
    }

    static class Subset {
        int parent, rank;
    }

    // Function to find the MST using Kruskal's algorithm
    public static void runKruskal(List<String> vertices, double[][] matrix) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != Double.POSITIVE_INFINITY && matrix[i][j] != 0) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }

        Collections.sort(edges);

        Subset[] subsets = new Subset[vertices.size()];
        for (int i = 0; i < vertices.size(); ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        List<Edge> result = new ArrayList<>();
        double totalMinCost = 0;
        for (Edge e : edges) {
            int x = find(subsets, e.src);
            int y = find(subsets, e.dest);

            if (x != y) {
                result.add(e);
                 totalMinCost += e.weight;
                union(subsets, x, y);
            }
        }
        System.out.println("");
        System.out.println("=================");
        System.out.println("Edges in the MST:");
        for (Edge e : result) {
            System.out.println(vertices.get(e.src) + " - " + vertices.get(e.dest) + ": " + e.weight);
        }
            System.out.println("Total cost of MST: " + totalMinCost);
    }

    private static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    private static void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
}
