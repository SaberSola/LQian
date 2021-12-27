package com.zl.lqian.bytedance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {

    /**
     * 克隆图
     *
     * @param node
     * @return
     */
    private HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        //找一个hashmap存储图的节点
        if (node == null) {
            return null;
        }
        //该节点已经访问过了，直接返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        //创建clone的node
        Node cloneNode = new Node(node.val, new ArrayList<>());
        //开始处理neighbors
        visited.put(node,cloneNode);
        //遍历邻居节点
        for (Node n : node.neighbors){
            cloneNode.neighbors.add(cloneGraph(n));
        }
        return cloneNode;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
