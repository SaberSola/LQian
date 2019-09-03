package com.zl.lqian.daypractice.find;

/**
 * @Author zl
 * @Date 2019-09-02
 * @Des 二分查找法
 */
public class BinarySearch {


    /**
     * 二分查找
     * 通过循环不断比较查找中值
     * @param key
     * @param a
     * @return
     */
    public static int find(int key,Integer[]a){
        int low  = 0;
        int high = a.length - 1;
        while (low < high){
            //循环查找
            int mid = low + (high - low)/2; //查找中值
            if (key > a[mid]){
                low = mid + 1;
            } //大于此次mid的值
            else if (key < a[mid]){
                high = mid -1;
            }else {
                return mid;
            }
        }
        return -1;
    }


    public static int digui(Integer []a,int low, int high,int key){
        if (low <= high){
            int mid = low + (high - low)/2; //查找中值
            if  (a[mid] == key){
                return mid;
            }else if (key > a[mid]){
                low = mid + 1;
            }else {
                high = mid -1;
            }
            return digui(a,low,high,key);
        }
        return -1;
    }

    public static void main(String[] args) {
        Integer a[] = {3, 11, 5, 6, 4, 1, 9, 30,98};
        System.out.println(find(30,a));
        System.out.println(digui(a,0,a.length - 1,30));
    }

}
