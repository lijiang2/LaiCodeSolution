package com.example.laicode;

import com.sun.javafx.font.directwrite.DWFactory;

import java.util.*;

/**
 * Created by lijiang on 6/4/17.
 */
public class DeepCopyUndirectedGraph {
    class GraphNode {
        public int key;
        public List<GraphNode> neighbors;

        public GraphNode(int key) {
            this.key = key;
            this.neighbors = new ArrayList<GraphNode>();
        }
    }

    public List<GraphNode> copy(List<GraphNode> graph) {
        // Corner Case:
        if (graph == null) {
            return null;
        }
        // Allocate memory for copy
//        List<GraphNode> newGraph = new ArrayList<>();
        // Map with original graph and copy
        Map<GraphNode,GraphNode> map = new HashMap<>();

        for (GraphNode curr : graph) {
            // Check map
            if (!map.containsKey(curr)) {
                GraphNode newNode = new GraphNode(curr.key);
                map.put(curr, newNode);
                // DFS; Check neighbor in map
                DFS(curr, map);
            }
            System.out.printf("Copying %d ... \n", curr.key );
//            System.out.println(curr.key);
            System.out.println("===========");
        }

//        return newGraph;
        /*************/
        // 不能用newGraph, 研究一下
        return new ArrayList<GraphNode>(map.values());
    }

    private void DFS(GraphNode seed, Map<GraphNode, GraphNode> map) {
        GraphNode seedCopy = map.get(seed);
        // Expand seed
        for (GraphNode nb : seed.neighbors) {
            if (!map.containsKey(nb)) {
                GraphNode newNode = new GraphNode(nb.key);
                map.put(nb, newNode);
                /**********/
                DFS(nb, map);
            }
            seedCopy.neighbors.add(map.get(nb));

        }
    }

    public static void main(String[] args) {


//        10 ---- 20
//          \     /
//           \   /
//            \ /
//             30 ---- 99

        List<GraphNode> graph = new ArrayList<>();
        DeepCopyUndirectedGraph input = new DeepCopyUndirectedGraph();
        DeepCopyUndirectedGraph.GraphNode n1 = input.new GraphNode(10);
        DeepCopyUndirectedGraph.GraphNode n2 = input.new GraphNode(20);
        DeepCopyUndirectedGraph.GraphNode n3 = input.new GraphNode(30);
        DeepCopyUndirectedGraph.GraphNode n4 = input.new GraphNode(99);
        graph.add(n1);
        graph.add(n2);
        graph.add(n3);
        graph.add(n4);
        n1.neighbors.add(n2);
        n1.neighbors.add(n3);
        n2.neighbors.add(n1);
        n2.neighbors.add(n3);
        n3.neighbors.add(n1);
        n3.neighbors.add(n2);
        n3.neighbors.add(n4);
        n4.neighbors.add(n3);

        DeepCopyUndirectedGraph test = new DeepCopyUndirectedGraph();
        List<GraphNode> output = test.copy(graph);
        Set<GraphNode> popSet = new HashSet<>();
        Deque<GraphNode> deque = new LinkedList<>();

        // start from n1
        System.out.println("Traverse output graph: ");
        deque.addLast(n1);
        while (!deque.isEmpty()) {
            // expand popped node
            GraphNode head = deque.pollFirst();
            System.out.println(head.key);
            // update popped set
            popSet.add(head);
            for (GraphNode nb : head.neighbors) {
                if (!popSet.contains(nb) && !deque.contains(nb)) {
                    deque.addLast(nb);
                }
            }
        }
    }
}
