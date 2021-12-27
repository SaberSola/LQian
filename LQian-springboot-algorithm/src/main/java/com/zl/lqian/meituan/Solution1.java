package com.zl.lqian.meituan;

public class Solution1 {

    /**
     * 记一开始有 bb 瓶酒，ee 个空瓶换一瓶酒。
     * @param numBottles
     * @param numExchange
     * @return
     */
    public int numWaterBottles(int numBottles, int numExchange) {
        int bottle = numBottles;
        int ans = numBottles;

        while (bottle >= numExchange){
            bottle -= numExchange;
            bottle++;
            ans++;
        }
        return ans;
    }
}
