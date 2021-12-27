package com.zl.lqian.tencent;

import com.zl.lqian.leetcode.ListNode;
import com.zl.lqian.leetcode.TreeNode;

import java.util.*;

public class Solution {

    /**
     * 无重复的最长字符串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        /**
         * 输入: s = "abcabcbb"
         * 输出: 3
         * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3
         */
        if (s.length() < 1) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int index = -1;
        int res = -1;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                //这里开始重复
                index = Math.max(map.get(s.charAt(i)), index);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - index);
        }
        return res;
    }

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 1->3->5->2->4->NULL
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        //奇偶链表, 需要把奇数和偶数拆开
        if (head == null) {
            return head;
        }
        //最终变为奇节点的尾巴结点
        ListNode old = head;
        //偶数节点的头结点
        ListNode evenHead = head.next;
        //最终变为偶数节点的尾节点
        ListNode even = evenHead;
        /**             old
         *     head ---> 1      2        3         4        5
         *                     even
         *
         *     head---> 1      2         3         4        5
         *                               old      even
         *
         *
         *
         *
         */
        while (even != null && even.next != null) {
            old.next = even.next;
            old = old.next;
            even.next = old.next;
            even = even.next;
        }
        old.next = evenHead;
        return head;
    }

    public boolean isBalanced(TreeNode root) {
        //平衡二叉树 左右两个子树不超过1
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.right), height(root.left)) + 1;
        }
    }

    /**
     * 打家劫舍
     * [1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 不能偷相邻的
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        /**
         *             2     7     9      3      1
         *
         *    偷       2     7     11      10    12
         *    不偷     0     2     7       11    11
         *
         *    max     2     7      11     11    12
         */


        if (nums.length == 0) {
            return 0;
        }
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }

        int N = nums.length;
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= N; k++) {
            dp[k] = Math.max(dp[k-1], nums[k-1] + dp[k-2]);
        }
        return dp[N];
    }

    /**
     * 二叉树是否存在路径和为
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        //递归去判断
        if (root == null) {
            return false;
        }
        //到达叶子节点
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        //开始递归左子树,右子树
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 买卖股票的最佳时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        // 特殊判断
        if (len < 2) {
            return 0;
        }
        int[][] dp = new int[len][2];
        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数

        // 初始化：不持股显然为 0，持股就需要减去第 1 天（下标为 0）的股价
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        /**
         *   0:两种情况：1:昨天不持股,今天什么都不做, 2:昨天持股,今天卖了
         *
         *   1:两种情况 1:昨天持股,今天什么都不做 2:昨天不持股,今天卖了
         *
         *
         */

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //注意 只能交易一次
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);

        }

        return dp[len - 1][0];
    }

    /**
     * 不同路径
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * <p>
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /**
         * 对于不同路径
         * f[i][j] = f[i-1][j] + f[i][j-1]
         */
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        //定义dp数组
        int[][] dp = new int[m][n];
        //填充第一行
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        //填充第一列
        for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }
        //开始循环
        for (int i = 0; i < m; i++) {
            for (int j = 0; i < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 或者异或
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            Integer count = map.get(i);
            count = count == null ? 1 : ++count;
            map.put(i, count);
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) {
                return i;
            }
        }
        return -1; // can't find it.
    }

    Random random = new Random();

    public int[] quickSort(int nums[], int l, int r) {
        /**
         *  快速排序的思想
         *   [小,小,小,i,大,大,大,j,未排序,未排序，pivot]
         *   一种折中思想
         *
         */
        int partion = randomPartion(nums, l, r);
        quickSort(nums, partion + 1, r);
        quickSort(nums, l, partion - 1);
        return nums;
    }

    public int randomPartion(int nums[], int l, int r) {
        //l = 0; r = 5 i = [0,5]
        int pivot = random.nextInt(r - l + 1) + l;
        //交换到右边
        swap(nums, pivot, r);
        return partion(nums, l, r);
    }

    //小于pivot的都放在i的左边,大于pivot的都放在i的右边
    public int partion(int[] nums, int l, int r) {
        int pivot = nums[r];
        //从最边界开始
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (nums[j] <= pivot) {//放在i的位子
                //交换位置
                swap(nums, j, ++i);
            }
        }
        //循环走完,换回pivot的位置
        swap(nums, ++i, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int a[] = new int[]{1, 2};
        System.out.println(a.length);
    }

    private int[] array;
    private int[] original;
    //打乱数组

    private void swapAt(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public Solution(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        array = original;
        original = original.clone();
        return original;
    }

    public int randRange(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swapAt(i, randRange(i, array.length));
        }
        return array;
    }

    /**
     * 最长的回文字符串
     * <p>
     * dp[i][j] = true;
     * 判断 dp[i-1][l+1] 是否未true;
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];
        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; r++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    maxLen = r - l + 1;
                    maxStart = l;
                    maxEnd = r;
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

    /**
     * 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        //需要一个数组判断是否使用过
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        search(nums, len, 0, path, used, res);
        return res;
    }

    private void search(int[] nums, int len, int deep, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (deep == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) {
                path.add(nums[i]);
                used[i] = true;
                search(nums, len, deep + 1, path, used, res);
                path.remove(path.size() - 1);
                used[i]= false;
            }
        }
    }
}

