package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-09-29
 * @Des  输入一个数组 使奇数排在偶数的前面
 */
public class OddEvenNumber {


    public void reOrderArray(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            if (array[i] % 2 != 0) {
                while (j >= 0) {
                    if (array[j] % 2 != 0) {
                        break;
                    }
                    if (array[j] % 2 == 0) {
                        int t = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = t;
                        j--;
                    }
                }
            }
        }
    }

    /**
     * 解法二：双指针法
     * 时间复杂度 O（n)，空间复杂度 O（1)
     *
     * @param array
     */
    public void reOrderArray2(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            while (left < right && array[left] % 2 != 0) {
                left++;
            }
            while (left < right && array[right] % 2 == 0) {
                right--;
            }

            if (left < right) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
            }
        }
    }

}
