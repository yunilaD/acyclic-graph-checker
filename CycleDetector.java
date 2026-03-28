// Name: Yunila Dissanayake
// Student ID: 20231664

import java.util.*;

public class CycleDetector {

    public void printCycle(Graph graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> stack = new HashSet<>();
        List<Integer> path = new ArrayList<>();

        for (int v : graph.getVertices()) {
            if (dfs(graph, v, visited, stack, path)) {
                return;
            }
        }

        System.out.println("No cycle found.");
    }

    private boolean dfs(Graph graph, int v, Set<Integer> visited,
                        Set<Integer> stack, List<Integer> path) {

        if (stack.contains(v)) {
            int index = path.indexOf(v);
            System.out.print("Cycle found: ");
            for (int i = index; i < path.size(); i++) {
                System.out.print(path.get(i) + " -> ");
            }
            System.out.println(v);
            return true;
        }

        if (visited.contains(v)) return false;

        visited.add(v);
        stack.add(v);
        path.add(v);

        for (int neighbor : graph.getNeighbors(v)) {
            if (dfs(graph, neighbor, visited, stack, path)) {
                return true;
            }
        }

        stack.remove(v);
        path.remove(path.size() - 1);

        return false;
    }
}