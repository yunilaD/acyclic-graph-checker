// Name: Yunila Dissanayake
// Student ID: 20231664

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        Graph graph = null;

        while (true) {
            System.out.println("Enter the file path (ex: benchmarks/cyclic/c_40_0.txt): ");
            String fileName = sc.nextLine();

            try {
                graph = GraphParser.parseFile(fileName);
                break; // Exit loop if parsing is successful
            } catch (IOException e) {
                System.out.println("Invalid file path. Please try again.");
            }
        }

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

        sc.close();
    }
}