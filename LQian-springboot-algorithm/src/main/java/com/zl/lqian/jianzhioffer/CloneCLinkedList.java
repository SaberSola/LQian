package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-10-15
 * @Des 复制一个复杂链表
 */
public class CloneCLinkedList {


    /**
     * 复杂链表
     * 俩个指针
     * 一个指向下一个节点
     * 一个指向随机的节点
     */
    public static class RandomListNode {
       public int label;
        public RandomListNode next = null;
        public RandomListNode random = null;

        public RandomListNode(int label) {
            this.label = label;
        }
    }

    /**
     * 1: 第一步仍然是根据原始链表的每个结点N创建对应的N'。（把N'链接在N的后面）
     * 2: 第二步设置复制出来的结点的兄弟节点
     * 3: 这个长链表拆分成两个链表：把奇数位置的结点用Next链接起来就是原始链表，偶数数值的则是复制链表。
     *
     * @param pHead
     * @return
     */
    public static RandomListNode Clone1(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode pCur = pHead;
        //复制next 如原来是A->B->C 变成A->A'->B->B'->C->C'
        while (pCur != null) {
            RandomListNode node = new RandomListNode(pCur.label);
            node.next = pCur.next;
            pCur.next = node;
            pCur = node.next;
        }
        pCur = pHead;
        //复制random pCur是原来链表的结点 pCur.next是复制pCur的结点
        while (pCur != null) {
            if (pCur.random != null) {
                pCur.next.random = pCur.random.next;
            }
            pCur = pCur.next.next;
        }
        RandomListNode head = pHead.next;
        RandomListNode cur = head;
        pCur = pHead;
        //拆分链表
        while (pCur != null) {
            pCur.next = pCur.next.next;
            if (cur.next != null) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
            pCur = pCur.next;
        }
        return head;
    }


    /**
     * 递归
     *
     * @param pHead
     * @return
     */
    public static RandomListNode Clone2(RandomListNode pHead) {

        if (pHead == null) {
            return null;
        }
        RandomListNode head = new RandomListNode(pHead.label);
        RandomListNode temp = head;

        while (pHead.next != null) {
            temp.next = new RandomListNode(pHead.next.label);
            if (pHead.random != null) {
                temp.random = new RandomListNode(pHead.random.label);
            }
            pHead = pHead.next;
            temp = temp.next;
        }
        return head;
    }


    public static void main(String[] args) {
        CloneCLinkedList.RandomListNode root = new CloneCLinkedList.RandomListNode(1);
        CloneCLinkedList.RandomListNode node1 = new CloneCLinkedList.RandomListNode(2);
        CloneCLinkedList.RandomListNode node2 = new CloneCLinkedList.RandomListNode(3);
        CloneCLinkedList.RandomListNode node3 = new CloneCLinkedList.RandomListNode(4);
        CloneCLinkedList.RandomListNode node4 = new CloneCLinkedList.RandomListNode(5);
        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        root.random = node1;
        node1.random = root;
        node3.random = node1;

        System.out.println("解法一：" + CloneCLinkedList.Clone1(root).next.label);
        //System.out.println("解法二：" + CloneCLinkedList26.Clone2(root).label);
    }
}
