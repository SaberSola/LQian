package com.zl.lqian.jianzhi;

import com.zl.lqian.jianzhioffer.Common;

/**
 * @Author zl
 * @Date 2020-04-12
 * @Des ${todo}
 */
public class Solution52 {


    /**
     * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
     * 1.判断链表中有环 -> 2.得到环中节点的数目 -> 3.找到环中的入口节点
     *
     * @param pHead
     * @return
     */
    public Common.ListNode EntryNodeOfLoop(Common.ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        //首先判断链表中是否有环
        //快慢指针
        Common.ListNode l = pHead, r = pHead;
        boolean flag = false;
        while (r != null && r.next != null) {
            l=l.next;
            r=r.next.next;
            if(l==r){
                flag=true;
                break;
            }
        }
        if (!flag){
            return null;
        }else {
            //说明有环
            int n =1 ;
            r=r.next;
            while(l!=r){
                r=r.next;
                n++;
            }//计算节点的个数
            // 3.找到环中的入口节点
            l=r=pHead;//从头开始
            //快指针先走n个
            for(int i=0;i<n;i++){
                r=r.next;
            }
            //快慢指针相遇就是头结点
            while(l!=r){
                l=l.next;
                r=r.next;
            }
            return l;
        }

    }
}
