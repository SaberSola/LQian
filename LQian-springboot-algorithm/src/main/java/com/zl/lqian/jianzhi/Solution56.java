package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-04-13
 * @Des ${todo}
 */
public class Solution56 {


    /**
     * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
     * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     *
     *                                1
     *                           2         3
     *                        4    5     6    7
     *
     *  打印: 1 3 2 4 5 6 6
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(Common.TreeNode pRoot) {
        LinkedList<Common.TreeNode> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        boolean rev = true; //反转标志
        q.add(pRoot);
        while(!q.isEmpty()){
            int size = q.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++){
                Common.TreeNode node = q.poll();//取第一个节点
                if(node == null){continue;}
                if(rev){
                    list.add(node.val);
                }else{
                    list.add(0, node.val); //涉及到内存的拷贝
                }
                q.offer(node.left);
                q.offer(node.right);
            }
            if(list.size()!=0){result.add(list);}
            rev=!rev;

        }
        return result;
    }
}
