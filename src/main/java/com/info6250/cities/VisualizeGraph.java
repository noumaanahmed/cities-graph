/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.info6250.cities;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
//import com.mxgraph.layout.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author noumaanahmed
 */

public class VisualizeGraph {

    // Visualize the graph and export it as an image
    public static void visualizeAndExportGraph(Graph<String, DefaultWeightedEdge> graph, String filename) {
        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();
        mxGraph.getModel().beginUpdate();

        try {
            Map<String, Object> vertexToCellMap = new HashMap<>();
            
            // Add vertices to the mxGraph
            for (String vertex : graph.vertexSet()) {
                Object cell = mxGraph.insertVertex(parent, null, vertex, 0, 0, 80, 30);
                vertexToCellMap.put(vertex, cell);
            }
            
            // Add edges to the mxGraph
            for (DefaultWeightedEdge edge : graph.edgeSet()) {
                String sourceVertex = graph.getEdgeSource(edge);
                String targetVertex = graph.getEdgeTarget(edge);
                Object sourceCell = vertexToCellMap.get(sourceVertex);
                Object targetCell = vertexToCellMap.get(targetVertex);
                mxGraph.insertEdge(parent, null, graph.getEdgeWeight(edge), sourceCell, targetCell);
            }
        } finally {
            mxGraph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(mxGraph);
        
        // The JFrame setup starts here
        JFrame frame = new JFrame("Graph Visualization");
        frame.getContentPane().add(graphComponent, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure the application doesn't close immediately
        frame.pack(); // Pack the frame to preferred sizes
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true); // Make the frame visible
        
        exportGraphAsImage(graphComponent, filename);
    }

// Export the graph as an image
private static void exportGraphAsImage(mxGraphComponent graphComponent, String filename) {
    graphComponent.setPreferredSize(new Dimension(500, 600)); // Set the preferred size
    BufferedImage image = new BufferedImage(graphComponent.getPreferredSize().width, graphComponent.getPreferredSize().height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = image.createGraphics();
    graphComponent.paintAll(g2d); // Use paintAll for high quality
    g2d.dispose();

}
}
