// Name: Yunila Dissanayake
// Student ID: 20231664

public class AcyclicChecker {

    public boolean isAcyclic(Graph originalGraph) {
        Graph graph = originalGraph.cloneGraph();

        while (true) {
            if (graph.isEmpty()) {
                return true;
            }

            Integer sink = graph.findSink();

            if (sink == null) {
                return false;
            }

            System.out.println("Removing sink: " + sink);
            graph.removeVertex(sink);
        }
    }

    // Silent version (used only for benchmarking)
public boolean isAcyclicSilent(Graph originalGraph) {
    Graph graph = originalGraph.cloneGraph();

    while (true) {
        if (graph.isEmpty()) {
            return true;
        }

        Integer sink = graph.findSink();

        if (sink == null) {
            return false;
        }

        // NO PRINT HERE
        graph.removeVertex(sink);
    }
}
}