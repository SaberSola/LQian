package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-04-27
 * @Des 整数翻转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 */
public class Solution7 {


    /**
     * 每次取模拿到末尾数字
     * @param x
     * @return
     */
    public int reverse(int x) {
        int res = 0;
        while (x != 0){
            //取末尾数字
            int tmp = x%10;
            //判断是否 大于 最大32位整数
            if (res>214748364 || (res==214748364 && tmp>7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x = x/10;

        }
        return res;
    }

}
