package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-09-29
 * @Des 给定一个头结点 删除一个节点 具体为n
 */
public class DeleteNode {


    static class ListNode {
        int data;
        ListNode nextNode;
    }

    /**
     *
     * @param head  头结点
     * @param deListNode 需要删除的节点
     *
     * 从头结点开始判断一个一个排产
     */
    public static void deleteNode(ListNode head, ListNode deListNode) {
        if (head == null || deListNode == null){
            return;
        }

        if (head == deListNode){
            head = null;
        }else {
            if (deListNode.nextNode == null){//删除的是尾节点
                ListNode pointListNode = head;
                while (pointListNode.nextNode.nextNode != null) {
                    pointListNode = pointListNode.nextNode;
                }
                pointListNode.nextNode = null;//此时pointListNode 倒数第二个节点
            }else {
                deListNode.data = deListNode.nextNode.data;
                deListNode.nextNode = deListNode.nextNode.nextNode;
            }
        }



    }

}
