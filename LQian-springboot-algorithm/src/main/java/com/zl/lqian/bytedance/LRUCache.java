package com.zl.lqian.bytedance;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 */
public class LRUCache {

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

    LruNode head,tail;

    public LRUCache(int capacity) {
        this.size= 0;
        this.capacity = capacity;
        head = new LruNode();
        tail = new LruNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        //get命中 移到头结点
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


    public void  moveToHead(LruNode node){
        //删除节点
        removeNode(node);
        //添加至头结点
        addHead(node);
    }

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


    private LruNode removeTail() {
        LruNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
