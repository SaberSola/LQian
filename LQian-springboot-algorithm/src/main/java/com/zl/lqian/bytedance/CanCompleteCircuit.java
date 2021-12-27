package com.zl.lqian.bytedance;

public class CanCompleteCircuit {

    /**
     * 在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。
     * <p>
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
     * <p>
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     * <p>
     * 说明: 
     * <p>
     * 如果题目有解，该答案即为唯一答案。
     * 输入数组均为非空数组，且长度相同。
     * 输入数组中的元素均为非负数。
     * 示例 1:
     * <p>
     * 输入:
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     * <p>
     * 输出: 3
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/gas-station
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int canCompleteCircuit(int[] gas, int[] cost) {
        //暴力法. 从每一个加油站触发
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            //当邮箱数量
            int remain = gas[i];
            //到达下一个剩余的油量
            while (remain - cost[j] >= 0) {
                //减去花费的 + 补给的
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                if (j == i) {
                    return i;
                }

            }
        }
        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;//加油站的长度
        for (int i = 0; i < n; i ++){
            int j = i;
            int remain = gas[i];
            while (remain - cost[j] >= 0){
                remain = remain - cost[j] + gas[(j + 1) % n];
                if (j == i) {
                    return i;
                }
            }
        }

        return -1;
    }
}
