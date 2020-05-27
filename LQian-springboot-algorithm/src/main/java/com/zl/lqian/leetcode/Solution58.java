package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-05-25
 * @Des ${todo}
 */
public class Solution58 {
    /**
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     * 说明: 
     * 如果题目有解，该答案即为唯一答案。
     * 输入数组均为非空数组，且长度相同。
     * 输入数组中的元素均为非负数。
     * 示例 1:
     * 输入:
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     * 输出: 3
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     * @param gas
     * @param cost
     * @return
     * 考虑暴力破解，一方面是验证下自己对题目的理解是否正确，另一方面后续的优化也可以从这里入手。
     *
     * 考虑从第 0 个点出发，能否回到第 0 个点。
     *
     * 考虑从第 1 个点出发，能否回到第 1 个点。
     *
     * 考虑从第 2 个点出发，能否回到第 2 个点。
     *
     * ... ...
     *
     * 考虑从第 n 个点出发，能否回到第 n 个点。
     *
     * 由于是个圆，得到下一个点的时候我们需要取余数。
     *
     */
    //暴力法
    public int canCompleteCircuit(int[] gas, int[] cost) {

        //汽油
        int n = gas.length;
        //考虑从每个点出发
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            //说明可以到达下一个点
            while (remain -cost[j] >=0){
                //减去花费的 + 新的补给的
                remain = remain - cost[j] + gas[(j+1) % n];
                j = (j + 1) % n;
                //j 回到了 i
                if (j == i) {
                    return i;
                }
            }

        }
        return -1;
    }

    /**
     * 优化算法
     *
     *  * * * * * *
     *  i     j
     *
     *  那么 i + 1 到 j 之间的节点是不是就都不可能绕一圈了？
     *  假设 i + 1 的节点能绕一圈，那么就意味着从 i + 1 开始一定能到达 j + 1。
     *  又因为从 i 能到达 i + 1，所以从 i 也能到达 j + 1。
     *  但事实上，i 最远到达 j 。产生矛盾，所以 i + 1 的节点一定不能绕一圈。同理，其他的也是一样的证明。
     *  所以下一次的 i 我们不需要从 i + 1 开始考虑，直接从 j + 1 开始考虑即可。
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            int remain = gas[i];
            while (remain - cost[j] >= 0) {
                //减去花费的加上新的点的补给
                remain = remain - cost[j] + gas[(j + 1) % n];
                j = (j + 1) % n;
                //j 回到了 i
                if (j == i) {
                    return i;
                }
            }
            if (j < i) {
                return -1;
            }
            //i 直接跳到 j，外层 for 循环执行 i++，相当于从 j + 1 开始考虑
            i = j;
        }
        return -1;
    }
}
