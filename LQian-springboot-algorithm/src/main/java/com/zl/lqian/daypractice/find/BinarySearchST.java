package com.zl.lqian.daypractice.find;

/**
 * @Author zl
 * @Date 2019-09-03
 * @Des ${todo}
 */
public class BinarySearchST<KEY extends Comparable<KEY>, VALUE> {

    private KEY[] keys;

    private VALUE[] values;

    private int N;

    public BinarySearchST(int capacity) {
        keys = (KEY[]) new Comparable[capacity];
        values = (VALUE[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }


    public VALUE get(KEY key) {
        if (isEmpty())
            return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0)
            return values[i];
        else
            return null;
    }

    public void put(KEY key,VALUE value){
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        if(N == keys.length)    //扩容
            resize(2 * keys.length);
        for(int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = value;
        N++;

    }

    /**
     * 二分查找法
     * @param key
     * @return
     */
    public int rank(KEY key){
        int low = 0,high = N -1 ; //低位指针
        while (low <= high){
            int middle = low + (high - low) / 2;
            if (keys[middle].compareTo(key) == 0){
                return middle;
            }else if (keys[middle].compareTo(key) > 0){  //middle > 当前的值 high指针开始移动
                high = middle -1;
            }else {
                low = middle + 1;
            }
        }
        return -1;
    }
    private void resize(int max) {
        // 将栈移动到一个大小为 max 的新数组
        KEY[] tempKeys = (KEY[]) new Comparable[max];
        VALUE[] tempValues = (VALUE[]) new Object[max];
        for(int i = 0; i < N; i++) {
            tempKeys[i] = keys[i];
            tempValues[i] = values[i];
        }
        keys = tempKeys;
        values = tempValues;
    }

}
