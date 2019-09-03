package com.zl.lqian.daypractice.find;

/**
 * @Author zl
 * @Date 2019-09-03
 * @Des ${todo}
 */
public class SequentialSearchST<KEY,VALUE> {

    private Node first;

    public VALUE get(KEY key){
        for (Node x = first ; x != null; x= x.next){
            if (key.equals(x.key)){
                return x.val;
            }
        }
        return null;
    }

    public void put(KEY key, VALUE val) {
        // 查找给定的键，找到则更新其值，否则在表中新建结点
        for(Node x = first; x != null; x = x.next)
            if(key.equals(x.key)) {
                x.val = val;
                return;    // 命中，更新
            }
        first = new Node(key, val, first);    // 未命中，新建结点
    }

    private class Node {
        KEY key;
        VALUE val;
        Node next;
        public Node(KEY key, VALUE val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
}
