package com.zl.lqian.bytedance;

import com.zl.lqian.leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class WidthOfBinaryTree {

    /**
     * 662. 二叉树最大宽度
     * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
     *
     * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
     *
     * 示例 1:
     *
     * 输入:
     *
     *            1
     *          /   \
     *         3     2
     *        / \     \
     *       5   3     9
     *
     * 输出: 4
     * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    int ans = 0;
    Map<Integer, Integer> left = new HashMap<>();
    /**
     * 二叉树的宽度
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        /**
         * 给每个树的节点增加一个编号
         * 根节点是 i  左子树是 2*i, 右子树是 2*i +1
         */

        ans = 0;
        left = new HashMap();
        dfs(root, 0, 0);
        return ans;
    }
    public void dfs(TreeNode root, int depth, int pos) {
        if (root == null) return;
        left.computeIfAbsent(depth, x-> pos);
        ans = Math.max(ans, pos - left.get(depth) + 1);
        dfs(root.left, depth + 1, 2 * pos);
        dfs(root.right, depth + 1, 2 * pos + 1);
    }
}
