package com.zl.lqian.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-05-12
 * @Des ${todo}
 */
public class Solution29 {


    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * 示例:
     *
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     *
     * 采用hash映射
     * 说明：
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> hash = new HashMap<>();
        for (int i = 0; i< strs.length; i++){
            char[] s_arr = strs[i].toCharArray();
            //排序
            Arrays.sort(s_arr);
            String key = String.valueOf(s_arr);
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }
        }
        return new ArrayList<List<String>>(hash.values());
    }
}
