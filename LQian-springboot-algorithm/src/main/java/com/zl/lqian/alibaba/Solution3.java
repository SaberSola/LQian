package com.zl.lqian.alibaba;


import java.util.*;

public class Solution3 {

    /**
     * 数组中的第K个最大元素
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * @param nums
     * @param k
     * @return
     */
    static Random random = new Random();

    public static int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public static int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public static int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public static int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    /**
     *
     * 堆排序
     * 第一个非
     */


    /**
     * 构造一个堆
     *
     * @param arr
     */
    public static void heapInsert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //当前插入的索引
            int currentIndex = i;
            //父节点的索引
            int fatherIndex = (currentIndex - 1) / 2;
            //如果当前插入的值大于其父结点的值,则交换值，并且将索引指向父结点
            //然后继续和上面的父结点值比较，直到不大于父结点，则退出循环
            while (arr[currentIndex] > arr[fatherIndex]) {
                swap(arr, currentIndex, fatherIndex);
                currentIndex = fatherIndex;
                //重新计算服索引
                fatherIndex = (currentIndex - 1) / 2;
            }
        }
    }

    public int findKthLargest2(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    /**
     * 构造大顶堆
     *
     * @param a
     * @param heapSize
     */
    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int large = i;
        if (left < heapSize && a[left] > a[large]) {
            large = left;
        }
        if (right < heapSize && a[right] > a[large]) {
            large = right;
        }
        //找到根节点
        if (large != i) {
            swap(a, i, large);
            maxHeapify(a, large, heapSize);
        }

    }


    public static int findKthLargest3(int[] nums, int k) {
        return quickSelect3(nums, 0, nums.length - 1, nums.length - k);
    }


    static int quickSelect3(int nums[], int l, int r, int index) {
        int piovt = pivot(nums, l, r);
        if (piovt == index) {
            return piovt;
        } else {
            return piovt < index ? quickSelect3(nums, piovt + 1, r, index) : quickSelect3(nums, l, piovt - 1, index);
        }
    }

    static int pivot(int[] nums, int l, int r) {
        //加一的目的是Wrandom是闭区间
        int i = random.nextInt(r - l + 1) + l;
        swap(nums, r, l);
        return partion(nums, l, r);
    }

    static int partion(int nums[], int l, int r) {
        //注意此时r的位置就是piovt值的位置
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
                //小于基准值,放在i的左边
                swap(nums, i + 1, j);
            }
        }
        //这里需要交换会基准值的位置
        swap(nums, i + 1, r);
        return i + 1;
    }


    public static void main(String[] args) {
        for (int l = 0 ; l < 10; ++l){
            System.out.println(l);
        }
    }

    /**
     * 三数之和,先排序
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);
        //注意此时是排过序的数组
        for (int i = 0; i < nums.length; i++) {
            //当前的i。> 0 直接break
            if (nums[i] > 0) {
                break;
            }
            //去重复,定义两个指针
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //去重
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    //去重
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    } // 去重
                    left++;
                    right--;
                }else if (sum > 0){
                    right --;
                }else {
                    left ++;
                }
            }
        }
        return ans;
    }


    /**
     * 无重复字符的最长子串
     * s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        Map<Character,Integer> map = new HashMap<>();
        int ans = -1;
        int index = -1;
        for (int i =0 ; i < s.length(); i ++){
            if (map.containsKey(s.charAt(i))){
                //第一次出现的位置
                index = Math.max(map.get(s.charAt(i)),index);
            }
            map.put(s.charAt(i),i);
            ans = Math.max(ans,i - index);
        }

        return ans;
    }

    /**
     * 最长的回串
     * dp[i][j] == s[i] == s[j] && dp[i+1][i-1]也是的
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0 ; l < n; ++l){
            for (int i = 0; i + 1 < n; ++i){
                int j = i + 1;

            }
        }

        return "";
    }

}

