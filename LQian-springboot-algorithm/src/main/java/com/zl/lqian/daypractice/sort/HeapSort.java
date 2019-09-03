package com.zl.lqian.daypractice.sort;

import com.zl.lqian.daypractice.Example;

/**
 * @Author zl
 * @Date 2019-09-03
 * @Des 堆排序
 */
public class HeapSort extends Example {


    @Override
    protected void sort(Comparable[] a){
        //构造堆 shu
        int N = a.length - 1;
        //k 为最后一个叶子节点的根节点
        for (int k = N / 2 ; k >= 0; k--){
            heap(a,k,N); //构造
        }
        while (N > 0){
            //排序 将最大的节点和[1】交换位置
            exch(a,0,N--);
            //重新调整
            heap(a,0,N);
        }

    }
    //todo
    public void heap(Comparable[] a,Integer k,Integer N){
        while (2 * k + 1 < N){// 从最后一个叶子的根节点开始 构造堆
            //找到最K根的左叶节点
            int j = 2*k + 1;
            if (j < N){
                //判断有无右方节点
                if (a[j].compareTo(a[j+1]) < 0){
                    j++;
                }
                //判断值的大小
                if (!(a[k].compareTo(a[j]) < 0)){
                    break;
                }
                //交换 k和j的位置 相当于K根节点下沉和子节点交换位置
                exch(a,k,j);
                k = j;
            }
        }
    }

    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        Integer a[] = {1, 3, 4, 5, 2, 6, 9, 7, 8, 0};
        sort.sort(a);
        sort.show(a);
    }
}
