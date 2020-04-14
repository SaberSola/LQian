package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

/**
 * @Author zl
 * @Date 2020-04-13
 * @Des ${todo}
 */
public class Solution57 {

    /**
     * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
     *
     *
     *                                   1
     *                              2         3
     *                           4     5    6    7
     *
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print(Common.TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        HashMap<Common.TreeNode, Integer> map = new HashMap<>();
        if (pRoot == null){
            return lists;
        }
        Deque<Common.TreeNode> queue = new ArrayDeque<>();//双端队列
        queue.addFirst(pRoot);
        map.put(pRoot,0);
        while (!queue.isEmpty()){
            pRoot = queue.pollLast();//从队列尾部取数据
            int deep = map.get(pRoot);//获取层数二叉树的层数
            if (pRoot.left != null){
                queue.addFirst(pRoot.left);
                map.put(pRoot.left, deep + 1);
            }
            if (pRoot.right != null){
                queue.addFirst(pRoot.right);
                map.put(pRoot.right, deep + 1);
            }
            if (lists.size() <= deep) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(pRoot.val);
                lists.add(list);
            } else {
                ArrayList<Integer> list = lists.get(deep);
                list.add(pRoot.val);
            }

        }
        return lists;
    }
}
