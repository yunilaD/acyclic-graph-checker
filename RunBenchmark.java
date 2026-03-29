// Name: Yunila Dissanayake
// Student ID: 20231664

// Description: Runs performance benchmarks on multiple graph files and reports runtime statistics.

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class RunBenchmark {

    // Stores statistics for benchmarking
    static class BenchmarkStats {
        int fileCount;
        long totalRuntime;
        long minRuntime = Long.MAX_VALUE;
        long maxRuntime = Long.MIN_VALUE;
    }

    // Counts number of edges in the graph
    static int countEdges(Graph graph) {
        int edgeCount = 0;

        for (int v : graph.getVertices()) {
            edgeCount += graph.getNeighbors(v).size();
        }

        return edgeCount;
    }

    // Processes all .txt files inside a folder
    static void processFolder(String folderPath, String expectedType, BenchmarkStats stats) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder: " + folderPath);
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("No .txt files found in: " + folderPath);
            return;
        }

        // Sort files by name
        Arrays.sort(files, Comparator.comparing(File::getName));

        System.out.println("\n===== " + expectedType.toUpperCase() + " FILES =====");

        for (File file : files) {
            try {
                Graph graph = GraphParser.parseFile(file.getPath());

                int vertexCount = graph.getVertices().size();
                int edgeCount = countEdges(graph);

                // Measure runtime
                long startTime = System.nanoTime();
                AcyclicChecker checker = new AcyclicChecker();
                boolean isAcyclic = checker.isAcyclicSilent(graph);
                long endTime = System.nanoTime();

                long runtime = endTime - startTime;

                // Check correctness
                boolean expectedAcyclic = expectedType.equalsIgnoreCase("acyclic");
                boolean correct = (isAcyclic == expectedAcyclic);

                // Update stats
                stats.fileCount++;
                stats.totalRuntime += runtime;
                stats.minRuntime = Math.min(stats.minRuntime, runtime);
                stats.maxRuntime = Math.max(stats.maxRuntime, runtime);

                // Print result for each file
                System.out.println(
                        file.getName()
                                + " | V=" + vertexCount
                                + " | E=" + edgeCount
                                + " | Time(ns)=" + runtime
                                + " | Output=" + (isAcyclic ? "Acyclic" : "Cyclic")
                                + " | Correct=" + (correct ? "Yes" : "No")
                );

            } catch (IOException e) {
                System.out.println("Error reading file: " + file.getName());
                System.out.println("Reason: " + e.getMessage());
            }
        }
    }

    // Prints summary statistics
    static void printSummary(String graphType, BenchmarkStats stats) {
        if (stats.fileCount == 0) {
            System.out.println("\nNo files processed for " + graphType + ".");
            return;
        }

        double averageRuntime = (double) stats.totalRuntime / stats.fileCount;

        System.out.println("\n----- " + graphType.toUpperCase() + " SUMMARY -----");
        System.out.println("Files tested: " + stats.fileCount);
        System.out.println("Average runtime (ns): " + averageRuntime);
        System.out.println("Minimum runtime (ns): " + stats.minRuntime);
        System.out.println("Maximum runtime (ns): " + stats.maxRuntime);
    }

    // Runs benchmark on both acyclic and cyclic datasets
    static void runBenchmark() {
        String acyclicFolder = "benchmarks/acyclic";
        String cyclicFolder = "benchmarks/cyclic";

        BenchmarkStats acyclicStats = new BenchmarkStats();
        BenchmarkStats cyclicStats = new BenchmarkStats();

        processFolder(acyclicFolder, "acyclic", acyclicStats);
        processFolder(cyclicFolder, "cyclic", cyclicStats);

        printSummary("acyclic", acyclicStats);
        printSummary("cyclic", cyclicStats);
    }

    public static void main(String[] args) {
        runBenchmark();
    }
}