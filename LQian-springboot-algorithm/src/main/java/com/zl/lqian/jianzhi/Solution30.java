package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-08
 * @Des ${todo}
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
 * 今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
 * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
 */
public class Solution30 {

    public int FindGreatestSumOfSubArray(int[] array) {
        int sum[] = new int[array.length];
        sum[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            sum[i] = sum[i - 1] + array[i];
        }
        //i 是终点 j是起点点
        int max = sum[0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    max = Math.max(max, sum[j]);
                } else {
                    max = Math.max(max,sum[i] - sum[j-1]); //j-j的和就等于七点到i位置之和减去起点到j-1位置之和
                }
            }
        }
        return max;
    }


    /**
     *
     *   1 2 3 4 5
     *
     *     i = 2
     *
     *
     * 返回最大的连续子序列之后
     * 用db去做 规划
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray2(int[] array){
        int sum[] = new int[array.length];
        sum[0] = array[0];
        for (int i= 1; i<array.length; i++){
            sum[i] = sum[i-1] + array[i];
        }
        int max = sum[0];
        for (int i = 0; i < array.length; i ++){
            for (int j = 0 ;i <=i ; j++){
                if (j == 0){
                    max = Math.max(max,sum[i]);
                }else {
                    max = Math.max(max,sum[i] - sum[j-1]);
                }
            }
        }
        return max;
    }


}
