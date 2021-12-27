package com.zl.lqian.bytedance;

public class MaximumSwap {

    /**
     * 670.最大交换
     * <p>
     * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
     * <p>
     * 输入: 2736
     * 输出: 7236
     * 解释: 交换数字2和数字7。
     * 暴力的做法很简单，一句话总结就是：
     * <p>
     * 从左往右枚举每个位上的数字，找到比当前数字大且最大，最靠右(最低位)的数字，进行交换。没有则不交换
     * <p>
     * 举几个例子先：
     * <p>
     * 2736：2找到后面最大的7👉交换👉7236
     * 98368：9后面没有比它大的，8也是。3找到后面最大的8👉交换👉98863
     * 1993：这个例子就体现最靠右，1找到后面最大的9，而且要是最靠右的9👉交换👉9913（❌9193）
     * <p>
     * 作者：Ben_
     * 链接：https://leetcode-cn.com/problems/maximum-swap/solution/xiang-jie-yong-bao-li-mei-ju-tan-xin-ko6-wj3g/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char maxTemp = c[i]; //最大的值,
            int maxTempIndex = i;//最大值的index
            for (int j = i + 1; j < s.length(); j++) {
                if (c[j] >= maxTemp) {
                    maxTemp = c[j];
                    maxTempIndex = j;
                }
            }
            //找打比他大的就交换
            if (maxTemp > c[i]) {
                char temp = c[i];
                c[i] = c[maxTempIndex];
                c[maxTempIndex] = temp;
                break;
            }
        }
        return Integer.parseInt(new String(c));
    }
}
