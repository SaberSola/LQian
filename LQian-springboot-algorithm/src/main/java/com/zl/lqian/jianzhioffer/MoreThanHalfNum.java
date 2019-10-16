package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-10-15
 * @Des shuzuzhogn
 */
public class MoreThanHalfNum {


    public static int moreThanHalfNum(int[] nums) {

        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return checkMoreThanHalf(nums, candidate) ? candidate : 0;
    }

    /**
     * 检查输入的数字出现的次数是否超过数组长度一半
     *
     * @param array
     * @param number
     * @return
     */
    private static boolean checkMoreThanHalf(int[] array, Integer number) {
        int times = 0;
        for (int i : array) {
            if (i == number) {
                times++;
            }
        }
        return times * 2 >= array.length;
    }
}
