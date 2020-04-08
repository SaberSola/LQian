package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-08
 * @Des ${todo}
 * 数组中的逆序对
 * 其实就是归并排序
 */
public class Solution35 {


    private int cnt;

    private void MergeSort(int[] array, int start, int end) {
        if (start >= end) return;
        int mid = (start + end) / 2;
        MergeSort(array, start, mid);
        MergeSort(array, mid + 1, end);
        MergeOne(array, start, mid, end);
    }

    private void MergeOne(int[] array, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int k = 0, i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            //如果前面的元素小于后面的不能构成逆序对
            if (array[i] <= array[j])
                temp[k++] = array[i++];
            else {
                //如果前面的元素大于后面的，那么在前面元素之后的元素都能和后面的元素构成逆序对
                temp[k++] = array[j++];
                cnt = (cnt + (mid - i + 1)) % 1000000007;
            }
        }
        while (i <= mid)
            temp[k++] = array[i++];
        while (j <= end)
            temp[k++] = array[j++];
        for (int l = 0; l < k; l++) {
            array[start + l] = temp[l];
        }
    }

    public int InversePairs(int[] array) {
        MergeSort(array, 0, array.length - 1);
        return cnt;
    }

    public static void main(String[] args) {
        int a[] = {1,3,2,6,4,5};
        Solution35 solution35 = new Solution35();
        solution35.InversePairs(a);
    }
}
