package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-28
 * @Des 带有随机指针
 *
 */
public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
