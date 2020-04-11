package com.zl.lqian.jianzhi;

import java.util.HashMap;

/**
 * @Author zl
 * @Date 2020-04-11
 * @Des ${todo}
 */
public class Solution41 {


    /**
     * 暴力法 hashmaop
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        //哈希算法
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i=0; i < array.length; i++){
            if(map.containsKey(array[i]))
                map.put(array[i],2);
            else
                map.put(array[i],1);
        }
        int count = 0;
        for(int i=0; i < array.length; i++){
            if(map.get(array[i]) == 1){
                if(count == 0){
                    num1[0] =  array[i];
                    count++;
                }else
                    num2[0] =  array[i];
            }
        }

    }

    /**
     * 采用异或
     * 链接：https://www.nowcoder.com/questionTerminal/e02fdb54d7524710a7d664d082bb7811?answerType=1&f=discussion
     * 来源：牛客网
     *
     * 依照这个思路，我们来看两个数（我们假设是AB）出现一次的数组。
     * 我们首先还是先异或，剩下的数字肯定是A、B异或的结果，这个结果的二进制中的
     * ，表现的是A和B的不同的位。我们就取第一个1所在的位数，假设是第3位，接着把原数组分成两组，
     * 分组标准是第3位是否为1。如此，相同的数肯定在一个组，因为相同数字所有位都相同，而不同的数，
     * 肯定不在一组。然后把这两个组按照最开始的思路，依次异或，剩余的两个结果就是这两个只出现一次的数字。
     * @param array
     * @param num1         101
     *                     110
     *                     011
     * @param num2
     */
    public void find(int [] array,int num1[] , int num2[]) {
        int xor1 = 0;//假设的
        for(int i=0; i < array.length; i++)
            xor1 = xor1^array[i]; //xor1是俩个不同的数字异或的结果
        //在xor1中找到第一个不同的位对数据进行分类，分类为两个队列对数据进行异或求和找到我们想要的结果
        int index = 1;
        while((index & xor1)==0)
            index = index <<1;//因为可能有多个位为1所以需要求一下位置
        int result1 = 0;
        int result2 = 0;
        for(int i=0; i < array.length; i++){
            if((index & array[i]) == 0)
                result1 = result1^array[i];
            else
                result2 = result2^array[i];
        }
        num1[0] = result1;
        num2[0] = result2;

    }

    public static void main(String[] args) {
        System.out.println(2<<1);
    }

}
