// Name: Yunila Dissanayake
// Student ID: 20231664

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Type the file path (ex: benchmarks/cyclic/c_40_0.txt)
            String fileName = "benchmarks/acyclic/a_40_0.txt";

            Graph graph = GraphParser.parseFile(fileName);

            System.out.println("Checking if graph is acyclic...\n");

            AcyclicChecker checker = new AcyclicChecker();
            boolean isAcyclic = checker.isAcyclic(graph);

            if (isAcyclic) {
                System.out.println("\nResult: Yes\nGraph is ACYCLIC");
            } else {
                System.out.println("\nResult: No\nGraph has a CYCLE");

                CycleDetector detector = new CycleDetector();
                detector.printCycle(graph);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}