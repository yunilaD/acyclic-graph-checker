// Name: Yunila Dissanayake
// Student ID: 20231664

import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addEdge(int u, int v) {
        adjList.putIfAbsent(u, new ArrayList<>());
        adjList.putIfAbsent(v, new ArrayList<>());
        adjList.get(u).add(v);
    }

    public Set<Integer> getVertices() {
        return adjList.keySet();
    }

    public List<Integer> getNeighbors(int v) {
        return adjList.getOrDefault(v, new ArrayList<>());
    }

    public boolean isEmpty() {
        return adjList.isEmpty();
    }

    public Integer findSink() {
        for (int v : adjList.keySet()) {
            if (adjList.get(v).isEmpty()) {
                return v;
            }
        }
        return null;
    }

    public void removeVertex(int v) {
        adjList.remove(v);
        for (List<Integer> neighbors : adjList.values()) {
            neighbors.remove(Integer.valueOf(v));
        }
    }

    public Graph cloneGraph() {
        Graph newGraph = new Graph();
        for (int v : adjList.keySet()) {
            newGraph.adjList.put(v, new ArrayList<>(adjList.get(v)));
        }
        return newGraph;
    }
}