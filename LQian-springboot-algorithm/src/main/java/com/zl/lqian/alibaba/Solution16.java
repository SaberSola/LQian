package com.zl.lqian.alibaba;

import com.zl.lqian.leetcode.ListNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Solution16 {

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串
     * 根据这个思路，我们就可以完成动态规划了，最终的答案即为所有 P(i, j) = \text{true}P(i,j)=true 中 j-i+1j−i+1（即子串长度）的最大值
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }

    /**
     * 回文串
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        /**
         *
         * s[i][j]是回文串,则s[i+1][j-1] = true 并且 s[i] == s[j]
         */
        int n = s.length();
        if (n < 1) {
            return s;
        }
        boolean[][] result = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        int maxLen = 1;
        int start = 0;
        String res = "";
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    result[i][j] = false;
                } else {
                    if (j - i < 3) {
                        result[i][j] = true;
                    } else {
                        result[i][j] = result[i + 1][j - 1];
                    }
                }
                if (result[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * 最大子序列和
     * [-2,1,-3,4,-1,2,1,-5,4]
     * <p>
     * -2 1  -2 4  3 5  6
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        /**
         * 动态方程
         * dp[i = max(dp[i-1] + nums[i],num[i])
         */
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    /**
     * 下一个排列
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        /**
         *  1.倒着找第一个非升序的数字
         *    1 2 3 6 5 4
         *    找到3
         *
         */

        int n;
        if (nums == null || (n = nums.length) == 0) return;
        //从后往前找到第一个num[i] < num[i+1]的数字
        int i = n - 2;
        for (; i >= 0 && nums[i] >= nums[i + 1]; i--) ;
        //找到i i < 0的话整体逆序
        if (i > 0) {
            //找到第一个 > i的数组
            int j = binarySearch(nums, i + 1, n - 1, nums[i]);
            //交换i和j的位置
            swap(nums, i, j);
        }
        //需要把i之后的数字调整为升序
        reverse(nums, i + 1, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > target) {
                left = mid + 1; // 尝试再缩小区间
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * 分治理思想
     *
     * @param lists
     * @param l
     * @param r
     * @return
     */
    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }


    /**
     * 先合并两个类表
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    /**
     * 多数元素
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int countNum = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (countNum == nums[i]) {
                count = count + 1;
            } else {
                count = count - 1;
                if (count == 0) {
                    countNum = nums[i];
                    count = 1;
                }
            }
        }
        return countNum;
    }

    /**
     * 最小的k个数
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        randomizedSelected(arr, 0, arr.length - 1, k);
        int[] vec = new int[k];
        for (int i = 0; i < k; ++i) {
            vec[i] = arr[i];
        }
        return vec;
    }

    private void randomizedSelected(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int pos = randomizedPartition(arr, l, r);
        int num = pos - l + 1;
        if (k == num) {
            return;
        } else if (k < num) {
            randomizedSelected(arr, l, pos - 1, k);
        } else {
            randomizedSelected(arr, pos + 1, r, k - num);
        }
    }

    // 基于随机的划分
    private int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l;
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    public boolean detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true){
            if (fast == null || fast.next == null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }
        }
    }

    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public void MinStack() {
        xStack = new LinkedList<>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        xStack.push(val);
        minStack.push(Math.min(minStack.peek(),val));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }


    class CQueue{
        Stack<Integer> s1;
        Stack<Integer> s2;
        CQueue(){
            s1 = new Stack<>();
            s2 = new Stack<>();
        }
        public void appendTail(int value) {
            s1.push(value);
        }

        public int deletehead(){
            if (s2.isEmpty()){
                if (s1.isEmpty()){
                    return -1;
                }
                while (!s1.isEmpty()){
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }

    }
}
