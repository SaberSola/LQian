package com.zl.lqian.bytedance;

import java.util.Arrays;

/**
 * 179. 最大数
 */
public class LargestNumber {


    /**
     * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
     * <p>
     * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
     *
     * 输入：nums = [10,2]
     * 输出："210"
     *
     * @param nums
     * @return
     */
    public static String largestNumber(int[] nums) {
        /**
         *  p排序   如果 a+b > b+a 那么就认为 a > b
         *
         */
        int n = nums.length;
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = nums[i] + "";
        }
        Arrays.sort(ss, (a, b) -> {
            String aa = a + b;
            String bb = b + a;
            return bb.compareTo(aa);
        });
        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            sb.append(s);
        }
        int len = sb.length();
        int k = 0;
        while (k < len - 1 && sb.charAt(k) == '0') {
            k++;
        }
        return sb.substring(k);
    }

    public static void main(String[] args) {
        int [] nums = new int[]{10,2};
        System.out.println(largestNumber(nums));
    }
}
