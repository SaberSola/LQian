package com.zl.lqian.meituan;

import com.zl.lqian.leetcode.ListNode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Soultion2 {


    public int myAtoi(String str) {
        int lenth = str.length();
        char[] charArray = str.toCharArray();
        // 1、去除前导空格
        int index = 0;
        while (index < lenth && charArray[index] == ' ') {
            index++;
        }
        //判断是否遍历完成
        if (index == lenth) {
            return 0;
        }
        //判断符号位
        int sign = 1;
        char firstChar = charArray[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        int res = 0;
        while (index < lenth) {
            char currChar = charArray[index];
            // 4.1 先判断不合法的情况
            if (currChar > '9' || currChar < '0') {
                break;
            }

            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            res = res * 10 + sign * (currChar - '0');
            index++;
        }
        return res;
    }


    /**
     * 最大的子序列he
     * nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * 定义动态方程
     * dp[i] = dp[i-1] + nums[i]；
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
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

    /**
     * 最长的公共自长度
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxlen = maxLength(A, B, i, 0, len);
            ret = Math.max(ret, maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }


    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }


    /**
     * 重拍链表
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        //找到中间节点,并且分为两条链表,将右端的链表翻转并且连接两个链表ji
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;//断开两个链表
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    //快慢指针找到中间链表
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 1-->2-->3
     * 1<--2<--3
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        //翻转链表需要记录当前节点,以及当前节点的前一个节点
        ListNode pre = null;
        ListNode current = head;
        while (current != null) {
            ListNode currentNext = current.next;
            current.next = pre;
            pre = current;
            current = currentNext;
        }
        return pre;
    }

    /**
     * 交替合并两个链表
     *
     * @param l1
     * @param l2
     */
    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;
            l1.next = l2;
            l1 = l1_tmp;
            l2.next = l1;
            l2 = l2_tmp;
        }
    }

    /**
     * 合并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for nums1
        int p = 0;
        while (p1 < m && p2 < n) {
            if (nums1_copy[p1] < nums2[p2]) {
                nums1[p] = nums1_copy[p1];
                p1++;
            } else {
                nums1[p] = nums2[p2];
                p2++;
            }
            p++;
        }
        //上述是比较
        if (p1 < m) {
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i) {
            for (int j = 0; j != grid[0].length; ++j) {
                ans = Math.max(ans, dfs(grid, i, j));
            }
        }
        return ans;

    }

    public int dfs(int[][] grid, int cur_i, int cur_j) {
        if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1) {
            return 0;
        }
        grid[cur_i][cur_j] = 0;
        int[] di = {0, 0, 1, -1};
        int[] dj = {1, -1, 0, 0};
        int ans = 1;
        for (int index = 0; index != 4; ++index) {
            int next_i = cur_i + di[index], next_j = cur_j + dj[index];
            ans += dfs(grid, next_i, next_j);
        }
        return ans;
    }

    /**
     * 最长的连续子序列
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
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
        //查看左边有几个数字
        int num = pos - l + 1;
        if (k == num) {
            return;
        } else if (k < num) {
            randomizedSelected(arr, l, pos - 1, k);
        } else {
            randomizedSelected(arr, pos + 1, r, k - num);
        }
    }

    /**
     * 找到随机切分的点
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int randomizedPartition(int[] nums, int l, int r) {
        //随机获取  边界控制在 [L,R]
        int i = new Random().nextInt(r - l + 1) + l;
        swap(nums, i, r);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        //包含L
        int i = l - 1;
        /**
         *      1 2 4 8 6 7 5
         *      l = 0; r= 6; 下标
         *      pivot = 5;数组值
         *      i相当于基准所在的位置
         *      r-1是因为r要存放基准的位置
         */
        for (int j = l; j <= r - 1; j++) {
            //比基准小,要放在i的左边
            if (nums[j] < pivot) {
                //比pivot要小都要放在pivot的坐标
                i = i + 1;
                swap(nums, i, j);
            }
        }
        //找不到比pivot小的值了
        //此时i+1,因该是pivot所在的值
        swap(nums, i + 1, r);
        return i + 1;
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 最长连续上升的子序列
     * nums = [10,9,2,5,3,7,101,18]
     * 10  ，9， 2， 5， 3， 7 ，101 ， 18
     * 1     1  1   2   2  3   4      4
     * dp[i] = dp[i-1]  num[i] < num[i-1]
     * dp[i] = dp[i-1] + 1  num[i] > num[i-1]
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxans = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxans = Math.max(dp[i], maxans);
        }

        return maxans;
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int firstPosition = findFirstPosition(nums, target);
        if (firstPosition == -1) {
            return new int[]{-1, -1};
        }
        int lastPosition = findLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }


    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // ① 不可以直接返回，应该继续向左边找，即 [left, mid - 1] 区间里找
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else {
                // 此时 nums[mid] > target，应该继续向左边找，即 [left, mid - 1] 区间里找
                right = mid - 1;
            }
        }

        // 此时 left 和 right 的位置关系是 [right, left]，注意上面的 ①，此时 left 才是第 1 次元素出现的位置
        // 因此还需要特别做一次判断
        if (left != nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }

    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 只有这里不一样：不可以直接返回，应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else if (nums[mid] < target) {
                // 应该继续向右边找，即 [mid + 1, right] 区间里找
                left = mid + 1;
            } else {
                // 此时 nums[mid] > target，应该继续向左边找，即 [left, mid - 1] 区间里找
                right = mid - 1;
            }
        }
        // 由于 findFirstPosition 方法可以返回是否找到，这里无需单独再做判断
        return right;
    }

    /**
     * 整数翻转
     * 321
     * <p>
     * <p>
     * 123
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        //x = 321
        while (x != 0) {
            int pop = x % 10;  // 1, 2  3
            x /= 10;           //32  3  0
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;//1  12 123
        }
        return rev;
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * //dp[i][j] 是回文串，需要判断dp[i-1][j+1]是否是回文串即可
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度
        boolean[][] dp = new boolean[n][n];
        for (int r = 1; r < n; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;

                    }
                }

            }

        }
        return s.substring(maxStart,maxEnd+1);
    }

    /**
     * 青蛙跳台阶
     * @param n
     * @return
     */
    public int numWays(int n) {

        if(n <= 2) {return n;};
        int p1 = 2;
        int p2 = 1;
        //dp[i] = dp[i-1] + dp[i-2]
        for (int i = 3;i < n ; i ++){
            int cur = p1+p2;
            p2 = p1;
            p1 = cur;
        }
        return p1;

    }


}
