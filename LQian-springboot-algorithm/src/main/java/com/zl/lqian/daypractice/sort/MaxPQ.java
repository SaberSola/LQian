package com.zl.lqian.daypractice.sort;

/**
 * @Author zl
 * @Date 2019-09-02
 * @Des 基于堆的优先队列
 * 堆是二叉树 气且有序
 * 根节点 和子节点的关系
 * root:k  left:2K right:2K + 1
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;

    private int N = 0;

    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max + 1];
    }

    /**
     * 插入一个节点
     *
     * @param v
     */
    public void insert(Key v) {
        pq[++N] = v;
        //节点上浮
        swim(N);
    }

    /**
     * 删除并返回最大元素
     */
    public Key delete() {
        Key max = pq[1];    // 从根结点得到最大元素
        exch(1, N--);     // 将其和最后一个结点交换
        pq[N + 1] = null;    // 防止对象游离
        sink(1);         // 恢复堆的有序性
        return max;
    }


    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    boolean isEmpty() { // 返回队列是否为空
        return N == 0;
    }

    /**
     * 交换下标
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 堆的上浮操作
     *
     * @param k
     */
    private void swim(int k) {
        while (k > 1 & less(k / 2, k)) {   //上浮就是和根节点比较
            //和根节点交换位置
            exch(k / 2, k);
            k = k / 2; //继续查找根节点
        }
    }

    /**
     * 对的下浮操作
     *
     * @param k
     */
    private void sink(int k) {
        while (2 * k < N) { //节点不是最后一个
            //找到下一个节点
            int j = 2 * k;
            if (j < N && less(j, j + 1)) { //处理右节点
                j++;
            }
            if (!less(k, j)) {//判断值的大小
                break;
            }
            exch(k, j);
            k = j;
        }
    }
}
