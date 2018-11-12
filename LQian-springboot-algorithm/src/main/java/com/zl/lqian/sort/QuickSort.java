package com.zl.lqian.sort;


import java.util.Arrays;
import java.util.Stack;

/**
 *
 * 快速排序
 *
 *
 */
public class QuickSort {


    /**
     * 快速排序（递归）
     * 从数组取出一个元素为基准
     * 重新排列数组 比基准小的放基准前边，比基准大的放在基准后边
     * 递归的把基准值小于的自数列和大于大于基准值的子树列排序
     */
    public static void quickSort(int[] arr,int low,int high){

        if(arr.length <= 0) return;
        if(low >= high) return;
        int left = low;
        int right = high;

        int temp = arr[left];   //挖坑1：保存基准的值
        while (left < right){
            while(left < right && arr[right] >= temp){  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                right--;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= temp){   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = temp;   //基准值填补到坑3中，准备分治递归快排
        System.out.println("Sorting: " + Arrays.toString(arr));
        quickSort(arr, low, left-1);
        quickSort(arr, left+1, high);
    }


    /**
     * 快速排序递归
     *
     * 1从数列挑出一个元素为基准
     *
     * 2重新排列 所有比基准小的放在基准前边，所有比基准值大的元素放在基准后边
     *
     * 3把分区后的俩个区的边界（low high）压入栈中保存，并循环
     *
     *
     * @param arr
     */
    public static void quickSortByStack(int[] arr){
        if(arr.length <= 0) return;
        Stack<Integer> stack = new Stack<Integer>();

        //初始状态的左右指针入栈
        stack.push(0);
        stack.push(arr.length - 1);
        while(!stack.isEmpty()){
            int high = stack.pop();     //出栈进行划分
            int low = stack.pop();

            int pivotIdx = partition(arr, low, high);

            //保存中间变量
            if(pivotIdx > low) {
                stack.push(low);
                stack.push(pivotIdx - 1);
            }
            if(pivotIdx < high && pivotIdx >= 0){
                stack.push(pivotIdx + 1);
                stack.push(high);
            }
        }
    }

    private static int partition(int[] arr, int low, int high){
        if(arr.length <= 0) return -1;
        if(low >= high) return -1;
        int l = low;
        int r = high;

        int pivot = arr[l];    //挖坑1：保存基准的值
        while(l < r){
            while(l < r && arr[r] >= pivot){  //坑2：从后向前找到比基准小的元素，插入到基准位置坑1中
                r--;
            }
            arr[l] = arr[r];
            while(l < r && arr[l] <= pivot){   //坑3：从前往后找到比基准大的元素，放到刚才挖的坑2中
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;   //基准值填补到坑3中，准备分治递归快排
        return l;
    }


    public static void main(String[] args){
        int[] array = {1,4,2,5,6,3,8};
        quickSort(array,0,array.length - 1);

    }



}
