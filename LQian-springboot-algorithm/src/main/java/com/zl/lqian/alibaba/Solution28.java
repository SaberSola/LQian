package com.zl.lqian.alibaba;

import com.zl.lqian.jianzhioffer.Common;
import com.zl.lqian.leetcode.ListNode;
import com.zl.lqian.leetcode.TreeNode;

import java.util.*;

public class Solution28 {

    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        /**
         * 环形链表, 快慢指针
         */
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        //回归到圆点
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public ListNode detectCycle2(ListNode head) {
        //先判断是否有环
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    /**
     * 左下角的值
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        queue.offer(root);
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
        }
        return node.val;
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }


    /**
     * 单词搜索
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] visit = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (solve(board, word, i, j, visit, 0)) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean solve(char[][] board, String word, int x, int y, boolean[][] visit, int index) {
        //退出循环
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visit[x][y]) {
            return false;
        }
        if (word.charAt(index) != board[x][y]) {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }
        visit[x][y] = true;
        boolean flag = solve(board, word, x - 1, y, visit, index + 1) ||
                solve(board, word, x + 1, y, visit, index + 1) ||
                solve(board, word, x, y - 1, visit, index + 1) ||
                solve(board, word, x, y + 1, visit, index + 1);
        visit[x][y] = false;
        return flag;
    }

    /**
     * 第k个缺失的正整数
     *
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositive(int[] arr, int k) {
        if (arr[0] < k) {
            return k;
        }
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int mixNum = arr[mid];
            if (mixNum - mid - 1 < k) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return k - (arr[l - 1] - (l - 1) - 1) + arr[l - 1];
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];    // 结果数组
        Map<Integer, Integer> map = new HashMap<>();
        // 统计数组中各元素出现的次数
        int max = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
            max = Math.max(map.get(num), max);
        }
        while (k > 0) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == max) {
                    res[k - 1] = entry.getKey();
                    k--;
                }
            }
            max--;
        }
        return res;
    }

    /**
     * 最长的重复子数组
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int max = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

    HashMap<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        HashSet<TreeNode> set = new HashSet<TreeNode>();
        List<Integer> list = new LinkedList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if (root == null || target == null) {
            return list;
        }
        queue.add(target);
        set.add(target);
        while (!queue.isEmpty() && K > 0) {
            int size = queue.size();
            K--;
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null && set.add(node.left) == true) {
                    queue.add(node.left);
                }
                if (node.right != null && set.add(node.right) == true) {
                    queue.add(node.right);
                }
                if (map.containsKey(node) && set.add(map.get(node)) == true) {
                    queue.add(map.get(node));
                }
                size--;
            }
        }
        while (!queue.isEmpty()) {
            list.add(queue.poll().val);
        }

        return list;

    }

    public void setParent(TreeNode root, TreeNode par) {
        if (par != null) {
            map.put(root, par);
        }
        if (root.left != null) {
            setParent(root.left, root);
        }
        if (root.right != null) {
            setParent(root.right, root);
        }
    }

    /**
     * 旋转链表 旋转K个
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = head;
        int length = 1;
        while (dummy != null) {
            dummy = dummy.next;
            length = length + 1;
        }
        //将此时的节点指向head 形成环状
        k = k % length;
        dummy.next = head;
        for (int i = 0; i < length - k; i++) {
            dummy = dummy.next;
        }
        head = dummy.next;
        dummy.next = null;
        return head;
    }

    static Random random = new Random();

    /**
     * 输入: [10,2]
     * 输出: "102"
     * //定义好规则
     *
     * @param nums
     * @return
     */
    public static String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        quickSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        return res.toString();

    }

    static void quickSort(String[] strs, int l, int r) {
        if (l >= r) {
            return;
        }
        int partion = randomPartion(strs, l, r);
        quickSort(strs, l, partion - 1);
        quickSort(strs, partion + 1, r);
    }

    static int randomPartion(String[] strs, int l, int r) {
        int pivot = random.nextInt(r - l + 1) + l;
        swap(strs, pivot, r);
        return partion(strs, l, r);
    }

    static int partion(String[] strs, int l, int r) {
        String pivot = strs[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            // x +y < y + x 说明 x < y
            if ((strs[j] + pivot).compareTo(pivot + strs[j]) < 0) {
                swap(strs, j, ++i);
            }
        }
        swap(strs, ++i, r);
        return i + 1;
    }


    static void swap(String[] strs, int i, int j) {
        String temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 1};
        System.out.println(minNumber(nums));
    }

    void quickSort2(String[] strs, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l, j = r;
        String pivot = strs[i];
        //找出第一个比temp的,
        while (i < j) {
            while ((strs[i] + pivot).compareTo(pivot + strs[i]) <= 0 && i < j) {
                i++;
            }

            while ((strs[j] + pivot).compareTo(pivot + strs[j]) >= 0 && i < j) {
                j--;
            }
            pivot = strs[i];
            strs[i] = strs[j];
            strs[j] = pivot;
        }
        strs[i] = strs[l];
        strs[l] = pivot;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }

    /**
     * 根据前序,中序 重建而茶树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
       return reConstructBinaryTree2(preorder,0,preorder.length-1,inorder,0,inorder.length-1);

    }

    private static TreeNode reConstructBinaryTree2(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        //定义边界,防止越界
        if(startPre>endPre||startIn>endIn) {
            return null;
        }
        //前序遍历的第一个节点为root
        TreeNode root = new TreeNode(pre[startPre]);
        for(int i=startIn;i<=endIn;i++){
            if (in[i] == pre[startPre]){//i就是跟几点所在范围
                //构建左子树
                root.left = reConstructBinaryTree2(pre,startPre + 1,startPre + i - startIn,in,startIn,i-1);
                //构建由子树
                root.right = reConstructBinaryTree2(pre,i-startIn + startPre +1,endPre,in,i+1,endIn);
                break;
            }

        }
        return root;
    }

    /**
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        while (l1 != null || l2 != null){
            int num1 = l1!= null?l1.val:0;
            int num2 = l2!= null?l2.val:0;
            int sum = num1 + num2;
            if(head == null){
                head =tail = new ListNode(sum%10);
            }else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res= new ArrayList<>();
        if(n < 1){
            return res;
        }
        boolean used[] = new boolean[n];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums,0,path,res,used);
        return res;
    }

    private void dfs(int[] nums, int depth, Deque<Integer> path, List<List<Integer>> res, boolean[] used) {
        if(depth == nums.length){
            res.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i< nums.length; i++){
            if (used[i]){
                continue;
            }
            used[i] = true;
            path.addLast(nums[i]);
            dfs(nums,depth+1,path,res,used);
            path.removeLast();
            used[i] = false;
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res= new ArrayList<>();
        if(n < 1){
            return res;
        }
        boolean used[] = new boolean[n];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums,0,path,res,used);
        return res;
    }


}
