package com.zl.lqian.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution {


    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * @param nums
     * @param target
     * @return
     */
    @Test
    public void twoSum(){

        int[] nums ={2,7,15,11};
        int[] temp = new int[2];
        int target = 9;
        //使用hash表
        Map<Integer,Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++){
            int a = nums[i];//获取数组值
            if(map.containsKey(target - a)){
                temp[0] = map.get(target - a);
                temp[1] = i;
            }
            map.put(nums[i],a);
        }
        System.out.println(temp[0] + "  " + temp[1]);
    }

    /**
     *  给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     *  您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *  输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     *  输出：7 -> 0 -> 8
     *  原因：342 + 465 = 807
     *
     *
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }









}
