package com.zl.lqian.jianzhi;

import apple.laf.JRSUIUtils;
import com.zl.lqian.jianzhioffer.Common;
import jdk.internal.dynalink.linker.LinkerServices;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zl
 * @Date 2020-04-07
 * @Des 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class Solution26 {


    /**
     * 中序遍历
     * @param pRootOfTree
     * @return
     */
    public Common.TreeNode Convert(Common.TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        ArrayList<Common.TreeNode> list = new ArrayList<>();
        Common.TreeNode head = list.get(0);
        Common.TreeNode pre = head;
        head.left = null;
        for (int i=1;i<list.size();i++){
            pre.right = list.get(i);
            list.get(i).left = pre;
            pre.right = pre;

        }
        return head;
    }


    public void mid(Common.TreeNode root, ArrayList<Common.TreeNode> tmpList){
        if (root == null){
            return;
        }
        mid(root.left, tmpList);
        tmpList.add(root);
        mid(root.right, tmpList);
    }

}
