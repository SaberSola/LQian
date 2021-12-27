package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.Node;

public class TreeToDoublyList {

    /**
     * 二叉树转为双向链表,中序遍历
     *
     * @param root
     * @return
     */
    Node pre;
    Node head;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == null) {
            head = root;
            pre = head;
        } else {
            pre.right = root;
            root.left = pre;
            pre = root;
        }
        dfs(root.right);
    }


    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }
    }
}
