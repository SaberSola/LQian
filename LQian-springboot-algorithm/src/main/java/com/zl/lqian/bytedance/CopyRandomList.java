package com.zl.lqian.bytedance;

import java.util.HashMap;
import java.util.Map;

public class CopyRandomList {


    /**
     * 138. 复制带随机指针的链表
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        //存储所有的几点
        Map<Node, Node> lookup = new HashMap<>();
        Node dummy = head;
        while (dummy != null) {
            lookup.put(dummy, new Node(dummy.val));
            dummy = dummy.next;
        }
        dummy = head;
        while (dummy != null) {
            lookup.get(dummy).next = lookup.get(dummy.next);
            lookup.get(dummy).random = lookup.get(dummy.random);
            dummy = dummy.next;
        }
        return lookup.get(head);
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(1);

        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node.random = node3;
        node2.random = node;
        copyRandomList(node);
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
