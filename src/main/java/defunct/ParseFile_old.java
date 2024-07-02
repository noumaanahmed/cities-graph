package defunct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ParseFile_old {
     private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("M/d/yy H:mm");

    // Method to parse the CSV and return a list of Ticket objects
    public static List<Ticket> parseCsv(String csvFilePath) {
        List<Ticket> tickets = new ArrayList<>();
        try (Reader reader = new FileReader(csvFilePath)) {
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            
            for (CSVRecord record : parser) {
                
                                // Parse date-time strings into numerical values representing minutes
                long firstResponseMinutes = parseDateTimeToMinutes(record.get("First Response Time"));
                long timeToResolutionMinutes = parseDateTimeToMinutes(record.get("Time to Resolution"));

                
                // Create a Ticket object from each record and add it to the list
                Ticket ticket = new Ticket(
                    record.get("Ticket ID"),
                    record.get("Customer Name"),
                    record.get("Customer Email"),
                    Integer.parseInt(record.get("Customer Age")),
                    record.get("Customer Gender"),
                    record.get("Product Purchased"),
                    record.get("Date of Purchase"),
                    record.get("Ticket Type"),
                    record.get("Ticket Subject"),
                    record.get("Ticket Description"),
                    record.get("Ticket Status"),
                    record.get("Resolution"),
                    record.get("Ticket Priority"),
                    record.get("Ticket Channel"),
                    firstResponseMinutes,
                    timeToResolutionMinutes,
                    record.isSet("Customer Satisfaction Rating") ? Integer.parseInt(record.get("Customer Satisfaction Rating")) : null
                    // Handle potential missing values for Customer Satisfaction Rating
                );
                tickets.add(ticket);
            }
            parser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets; // Return the list of ticket objects
    }
    
    
    
        private static long parseDateTimeToMinutes(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return 0; // Default value for missing or empty date-time
        }
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        LocalDateTime startOfYear = LocalDateTime.of(dateTime.getYear(), 1, 1, 0, 0);
        return ChronoUnit.MINUTES.between(startOfYear, dateTime);
    }

    
    // Class to represent the details of a ticket
    public static class Ticket {
        private String ticketId;
        private String customerName;
        private String customerEmail;
        private int customerAge;
        private String customerGender;
        private String productPurchased;
        private String dateOfPurchase;
        private String ticketType;
        private String ticketSubject;
        private String ticketDescription;
        private String ticketStatus;
        private String resolution;
        private String ticketPriority;
        private String ticketChannel;
        private long firstResponseMinutes;
        private long timeToResolutionMinutes;
        private Integer customerSatisfactionRating;

        public Ticket(String ticketId, String customerName, String customerEmail, int customerAge,
                      String customerGender, String productPurchased, String dateOfPurchase, String ticketType,
                      String ticketSubject, String ticketDescription, String ticketStatus, String resolution,
                      String ticketPriority, String ticketChannel, long firstResponseMinutes, long timeToResolutionMinutes,
                      Integer customerSatisfactionRating) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.customerEmail = customerEmail;
            this.customerAge = customerAge;
            this.customerGender = customerGender;
            this.productPurchased = productPurchased;
            this.dateOfPurchase = dateOfPurchase;
            this.ticketType = ticketType;
            this.ticketSubject = ticketSubject;
            this.ticketDescription = ticketDescription;
            this.ticketStatus = ticketStatus;
            this.resolution = resolution;
            this.ticketPriority = ticketPriority;
            this.ticketChannel = ticketChannel;
            this.firstResponseMinutes = firstResponseMinutes;
            this.timeToResolutionMinutes = timeToResolutionMinutes;
            this.customerSatisfactionRating = customerSatisfactionRating;
        }

        public String getTicketId() {
            return ticketId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public int getCustomerAge() {
            return customerAge;
        }

        public String getCustomerGender() {
            return customerGender;
        }

        public String getProductPurchased() {
            return productPurchased;
        }

        public String getDateOfPurchase() {
            return dateOfPurchase;
        }

        public String getTicketType() {
            return ticketType;
        }

        public String getTicketSubject() {
            return ticketSubject;
        }

        public String getTicketDescription() {
            return ticketDescription;
        }

        public String getTicketStatus() {
            return ticketStatus;
        }

        public String getResolution() {
            return resolution;
        }

        public String getTicketPriority() {
            return ticketPriority;
        }

        public String getTicketChannel() {
            return ticketChannel;
        }

        public long getFirstResponseMinutes() {
            return firstResponseMinutes;
        }

        public long getTimeToResolutionMinutes() {
            return timeToResolutionMinutes;
        }


        public Integer getCustomerSatisfactionRating() {
            return customerSatisfactionRating;
        }


    }
}






//package com.info6250.cities;
//
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;
//import org.jgrapht.Graph;
//import org.jgrapht.graph.DefaultWeightedEdge;
//import org.jgrapht.graph.SimpleDirectedWeightedGraph;
//
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Reader;
//
//public class ParseFile {
//
//    
//    
//    
//    public static void main(String[] args) {
//        String csvFilePath = "/Users/noumaanahmed/Downloads/synthetic_dataset.csv"; // Update with your dataset path
//        Graph<String, DefaultWeightedEdge> graph = parseCsv(csvFilePath);
//
//        // Output the graph to a text file
//        writeGraphToFile(graph, "/Users/noumaanahmed/Downloads/graph_output_23_04_6:03pm.txt");
//
//        System.out.println("Graph created and written to graph_output.txt");
//    }
//    
//    
//    
//    
//
//    public static Graph<String, DefaultWeightedEdge> parseCsv(String csvFilePath) {
//    Graph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
//    try (Reader reader = new FileReader(csvFilePath)) {
//        System.out.println("Parsing CSV file: " + csvFilePath);
//        CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
//
//        for (CSVRecord record : parser) {
//            String origin = record.get("Origin");
//            String destination = record.get("Destination");
//            double distance = Double.parseDouble(record.get("Distance"));
//
//            // Add origin and destination as vertices if they don't already exist
//            if (!graph.containsVertex(origin)) {
//                graph.addVertex(origin);
//            }
//            if (!graph.containsVertex(destination)) {
//                graph.addVertex(destination);
//            }
//
//            // Add edge between origin and destination with distance as weight
//            DefaultWeightedEdge edge = graph.addEdge(origin, destination);
//            if (edge != null) {
//                graph.setEdgeWeight(edge, distance);
//            }
//        }
//        parser.close();
//
//        System.out.println("Graph populated. Total vertices: " + graph.vertexSet().size());
//        System.out.println("Graph populated. Total edges: " + graph.edgeSet().size());
//    } catch (Exception e) {
//        System.out.println("An error occurred while parsing the CSV file:");
//        e.printStackTrace();
//    }
//    return graph;
//}
//
//    
//    
//    
//
//    private static void writeGraphToFile(Graph<String, DefaultWeightedEdge> graph, String filePath) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
////            writer.write("Graph populated. Total vertices: " + graph.vertexSet().size());
////            writer.newLine();
////            writer.write("Graph populated. Total edges: " + graph.edgeSet().size());
////            writer.newLine();
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
////            writer.write("Graph visualization finished.");
////            writer.newLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}





