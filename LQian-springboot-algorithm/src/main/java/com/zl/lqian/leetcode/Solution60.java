package com.zl.lqian.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zl
 * @Date 2020-05-28
 * @Des ${todo}
 */
public class Solution60 {


    /**
     * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
     * <p>
     * 要求返回这个链表的 深拷贝。 
     * <p>
     * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
     * <p>
     * val：一个表示 Node.val 的整数。
     * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
     * hash表方式
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> lookup = new HashMap<>();
        Node node = head;
        while (node != null){
            lookup.put(node,new Node(node.val));
            node = node.next;
        }
        node = head;
        while (node != null){
            lookup.get(node).next = lookup.get(node.next);
            lookup.get(node).random = lookup.get(node.random);
            node = node.next;
        }
        return lookup.get(head);
    }

}
