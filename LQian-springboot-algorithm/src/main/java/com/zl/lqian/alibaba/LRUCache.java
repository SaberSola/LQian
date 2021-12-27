package com.zl.lqian.alibaba;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanglei
 */
public class LRUCache {


    /*class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }
    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }*/


   class LruNode{
       int key;
       int value;
       LruNode prev;
       LruNode next;
       public LruNode() {}
       public LruNode(int _key, int _value) {key = _key; value = _value;}
   }

    Map<Integer,LruNode> cache = new HashMap<>();
    int size;
    int capacity;
    LruNode head, tail;




    public LRUCache(int capacity) {
        //双向节点
        this.size = 0;
        this.capacity = capacity;
        head = new LruNode();
        tail = new LruNode();
        head.next = tail;
        tail.prev = head;
    }

    //cache命中就返回,并且移除到头部节点
    public int get(int key) {
        LruNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        LruNode node = cache.get(key);
        if (null != node){
            node.value = value;
            moveToHead(node);
        }else {
            node = new LruNode(key,value);
            cache.put(key, node);
            // 添加至双向链表的头部
            addHead(node);
            ++size;
            if (size > capacity){
                //移除尾部的节点
                LruNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
    }


    /**
     * 删除这个节点
     * @param node
     */
    public void removeNode(LruNode node){
        LruNode curNext = node.next;
        LruNode curPer  = node.prev;
        curPer.next = curNext;
        curNext.prev = curPer;
    }

    public void addHead(LruNode node){
        node.prev = head;
        node.next = head.next;
        head.next.prev= node;
        head.next = node;
    }

    public void  moveToHead(LruNode node){
        removeNode(node);
        addHead(node);
    }

    private LruNode removeTail() {
        LruNode res = tail.prev;
        removeNode(res);
        return res;
    }

}
