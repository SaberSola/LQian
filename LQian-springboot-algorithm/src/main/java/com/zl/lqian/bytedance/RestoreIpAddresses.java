package com.zl.lqian.bytedance;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {

    //ip总共有四段,代表ip的四段
    static final int SEG_COUNT = 4;
    static List<String> ans = new ArrayList<>();
    //每一段的数组
    static int[] segments = new int[SEG_COUNT];

    /**
     * 复原ip地址
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return ans;
    }

    /**
     * @param s        完整字符串
     * @param segId    segId ip四段中的第几段,
     * @param segStart 字符串的起始值
     */
    public static void dfs(String s, int segId, int segStart) {
        //如果找到了四个字段,并且遍历完了字符串,那么递归返回
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuilder ipAdd = new StringBuilder();
                for (int i = 0; i < SEG_COUNT; i++) {
                    ipAdd.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAdd.append('.');
                    }
                }
                ans.add(ipAdd.toString());
            }
            return;
        }
        //字符串遍历结束
        if (segStart == s.length()) {
            return;
        }
        //判断为0的情况
        if (s.charAt(segStart) == '0'){
            segments[segId] = 0;
            //继续搜索
            dfs(s,segId+1,segStart+1);
        }
        int addr = 0;
        //判断是不是0的情况
        for (int segEnd = segStart; segEnd < s.length(); segEnd++){
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            //判断是否 0< add < 255
            if (addr > 0 && addr <= 255) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String s = "25525511135";
        System.out.println(restoreIpAddresses(s));
    }
}
