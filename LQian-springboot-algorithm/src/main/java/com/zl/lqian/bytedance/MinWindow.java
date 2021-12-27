package com.zl.lqian.bytedance;


/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 难度为困难.不处理 76. 最小覆盖子串
 */
public class MinWindow {


    /**
     * 包含的最小子字符串,可以试用于滑动窗口
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {

        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        //定义字节数组
        int[] need = new int[128];
        //字符所在的下标值,标为1
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        //开始定义滑动窗口
        //左边界
        int left = 0;
        //右边界
        int right = 0;
        //滑动窗口的大小
        int size = Integer.MAX_VALUE;
        //需要包含字符的个数
        int count = t.length();
        // 滑动窗口的开始洗标
        int start = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (need[c] > 0) {
                //c是需要的字符换
                count--;
            }
            //利用正负去区分
            need[c]--;
            //滑动窗口已经全部包含了
            //需要移动窗口
            if (count == 0) {
                //移动窗口,需要判断,左边界的字符串是否是
                //这里有两种情况,一种是区间不需要的字符,还有一种是重复的需要字符
                //总之就是去掉冗余的字符,缩小区间
                while (left < right && need[s.charAt(left)] < 0) {
                    //释放左边移动出窗口的字符
                    need[s.charAt(left)]++;
                    left++;//指针右移
                }
                //更新有效区间的最小值
                if (right - left + 1 < size) {
                    size = right - left + 1;
                    start = left;
                }
                //左边界右移
                need[s.charAt(left)]++;
                left++;
                count++;
            }
            right++;
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }
}
