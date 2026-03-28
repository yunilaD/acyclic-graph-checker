// Name: Yunila Dissanayake
// Student ID: 20231664

import java.io.*;

public class GraphParser {

    public static Graph parseFile(String fileName) throws IOException {
        Graph graph = new Graph();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 2) {
                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);
                graph.addEdge(u, v);
            }
        }

        br.close();
        return graph;
    }
}