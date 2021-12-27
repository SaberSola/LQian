package com.zl.lqian.bytedance;

import java.util.Arrays;

public class NextGreaterElement {

    /**
     * 下一个排列
     *
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        /**
         *  1. 先把字符串转为数组
         *  2. 从后往前找,第一个下降的数据K
         *  3. 重新从后往前找,第一个大于K的数字、
         *  4.两者交换,将k的部分变为升序
         *
         */
        String s = String.valueOf(n);
        char[] array = s.toCharArray();

        int len = s.length();
        for (int i = len - 1; i > 0; i--) {
            if (array[i - 1] < array[i]) {
                //继续找,找到比array[i-1]大的值
                for (int j = len - 1; j > 0; j--){
                    if (array[j] > array[i-1]){
                        //交换位置
                        swap(array,i-1,j);
                        Arrays.sort(array, i, len);
                        String res = new String(array);
                        long ans = Long.parseLong(res);
                        //最大值,返回-1
                        if (ans > Integer.MAX_VALUE){
                            return -1;
                        }
                        return (int) ans;
                    }
                }
            }

        }
        return -1;

    }
    public void swap(char[] nums, int i, int j) {
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

