package com.zl.lqian.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * 比较相邻的元素，如果第一个比第二个大 就交换他们俩个
 * 对每一对相邻的元素做同样的工作 这样 最后的元素会是最大的数
 * 针对所有的元素重复上述
 *
 *
 */
public class BubbleSort {


    /**
     * 冒泡排序需要俩个嵌套循环 外层循环移动游标 内层循环比较  外层循环控制次数
     *
     *
     * @param arrsy
     */
    public static void bubbleSort(int [] arrsy){


        for (int i =arrsy.length; i > 0 ; i --){ //外层循环移动图标 倒叙

            for (int j = 0; j < i && (j + 1) < i; j ++){
                if (arrsy[j] > arrsy[j + 1]){ //换位置
                    int temp = arrsy[j];
                    arrsy[j] = arrsy[j + 1];
                    arrsy[j + 1] = temp;
                    System.out.println("Sorting: " + Arrays.toString(arrsy));
                }

            }
        }

    }

    public static void main(String[] args){
        int[] array = {1,4,2,5,6,3,8};
        bubbleSort(array);
    }
}
