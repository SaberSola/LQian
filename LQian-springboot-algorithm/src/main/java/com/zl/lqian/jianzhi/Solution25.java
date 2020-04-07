package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.CloneCLinkedList;

import java.util.HashMap;

/**
 * @Author zl
 * @Date 2020-04-07
 * @Des ${todo}
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）
 * ，返回结果为复制后复杂链表的head。
 * （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 */
public class Solution25 {

    HashMap<CloneCLinkedList.RandomListNode,CloneCLinkedList.RandomListNode> map = new HashMap<>();
    /**
     *       node2
     *        \
     *       node1 --> node2 --> node3
     *
     * 引入一个map
     *
     * @param oldNode
     * @return
     */
    public CloneCLinkedList.RandomListNode Clone(CloneCLinkedList.RandomListNode oldNode) {
        if (oldNode == null){
            return null;
        }
        if (map.containsKey(oldNode)){
            return map.get(oldNode);
        }
        CloneCLinkedList.RandomListNode newNode = new CloneCLinkedList.RandomListNode(oldNode.label);
        map.put(oldNode,newNode);
        newNode.next = Clone(oldNode.next);
        newNode.random = Clone(oldNode.random);
        return newNode;

    }
}
