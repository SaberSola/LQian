package com.zl.lqian.bytedance;

import java.util.LinkedList;

/**
 * 71. 简化路径
 */
public class SimplifyPath {


    /**
     * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
     * <p>
     * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
     * <p>
     * 请注意，返回的 规范路径 必须遵循下述格式：
     * <p>
     * 始终以斜杠 '/' 开头。
     * 两个目录名之间必须只有一个斜杠 '/' 。
     * 最后一个目录名（如果存在）不能 以 '/' 结尾。
     * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        /**
         *
         * .输入：path = "/home/"
         * 输出："/home"
         * 解释：注意，最后一个目录名后面没有斜杠。
         *
         * 思路,双端队列 . 和''直接跳过就好
         * ..返回上一级,弹出队尾
         * 其他情况则入栈
         */
        String[] res = path.split("/");
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < res.length; i++) {
            String s = res[i];
            if (s.equalsIgnoreCase(".") || s.equalsIgnoreCase("")) {
                continue;
            } else if (s.equalsIgnoreCase("..")) {
                //需要弹出队尾,返回上一级
                if (!linkedList.isEmpty()) {
                    //移除队尾的元素,返回上一级
                    linkedList.pollLast();
                }
            } else {
                linkedList.offer(s);
            }
        }
        //还原字符串
        StringBuilder stringBuilder = new StringBuilder("/");
        while (!linkedList.isEmpty()) {
            String s = linkedList.poll();
            stringBuilder.append(s);
            if (!linkedList.isEmpty()) {
                stringBuilder.append("/");
            }
        }

        String result = stringBuilder.toString();
        return result.equalsIgnoreCase("") ? "/" : result;

    }
}
