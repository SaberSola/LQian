package com.zl.lqian.leetcode;

/**
 * @Author zl
 * @Date 2020-04-27
 * @Des ${todo}
 */
public class Solution6 {


    /**
     * LEETCODEISHIRING
     * numRows:4
     * L     D     R     0 ldr     长度为三的数据
     * E   O E   I I     1 eoeii
     * E C   I H   N     2 ecihn
     * T     S     G     3 tsg
     * down= false 为向下云顶
     * loc = 3 是 1 2 3
     * 继续循环
     * loc = 4
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        int len = Math.min(s.length(), numRows);
        String[] rows = new String[len];
        for (int i = 0; i < len; i++) rows[i] = "";
        int loc = 0;
        //true 表示向下
        boolean down = false;

        for (int i = 0; i < s.length(); i++) {
            rows[loc] += s.substring(i, i + 1);
            if (loc == 0 || loc == numRows - 1) {
                down = !down;
            }
            loc += down ? 1 : -1;//一直加到3的时候 down变为false  从 3 到 0 循环
        }

        String ans = "";
        for (String row : rows) {
            ans += row;
        }
        return ans;
    }

    public static void main(String[] args) {
        convert("LEETCODEISHIRING", 4);
    }
}
