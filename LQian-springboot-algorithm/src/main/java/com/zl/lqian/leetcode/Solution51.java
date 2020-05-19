package com.zl.lqian.leetcode;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author zl
 * @Date 2020-05-18
 * @Des ${todo}
 */
public class Solution51 {


    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     * <p>
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * 说明:
     * <p>
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * 示例 1:
     * <p>
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * <p>
     * 输出: 5
     * <p>
     * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * 返回它的长度 5。
     * 示例 2:
     * <p>
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * <p>
     * 输出: 0
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    private int length;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int n = wordList.size();
        if (n == 0) {
            return 0;
        }
        length = beginWord.length();
        boolean[] marked = new boolean[n];
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(beginWord, 1));
        while (!queue.isEmpty()) {
            Pair<String, Integer> temp = queue.remove();
            for (int i = 0; i < n; i++) {
                String next = wordList.get(i);
                if (!marked[i] && canTransform(temp.getKey(), next)) {//没有使用过 && 可以转换
                    if(next.equals(endWord)){
                        return temp.getValue() + 1;
                    }
                    marked[i] = true;
                    queue.add(new Pair<>(wordList.get(i), temp.getValue() + 1));
                }
            }
        }
        return 0;
    }

    private boolean canTransform(String from, String to) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (from.charAt(i) != to.charAt(i)) {
                if (count == 1)
                    return false;
                else count++;
            }
        }
        return true;
    }


}
