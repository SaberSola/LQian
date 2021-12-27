package com.zl.lqian.bytedance;

import java.util.List;

/**
 * 559. N 叉树的最大深度
 */
public class MaxDepth {

    /**
     * 给定一个 N 叉树，找到其最大深度。
     * <p>
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     * <p>
     * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        //递归的临界点
        if (root == null) {
            return 0;
        }
        int maxChildDepth = 0;
        List<Node> child = root.children;
        for (Node c : child) {
            int childDepth = maxDepth(c);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }
        return maxChildDepth + 1;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
