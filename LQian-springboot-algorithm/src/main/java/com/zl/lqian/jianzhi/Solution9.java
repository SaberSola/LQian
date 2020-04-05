package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-05
 * @Des ${todo}
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class Solution9 {


    /**
     * f(n)=f(n-1)+f(n-2)+...+f(1)
     * f(n-1)=f(n-2)+...f(1)
     * 两式相减得
     * 得:f(n)=2*f(n-1)
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        return target <= 0 ? 0 : 1 << (target - 1);

    }
}
