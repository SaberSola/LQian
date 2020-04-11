package com.zl.lqian.jianzhi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author zl
 * @Date 2020-04-11
 * @Des ${todo}
 */
public class Solution50 {

    /**
     *
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        Set<Integer> set = new HashSet<>();
        for(int i =0 ;i<length;i++){
            if(set.contains(numbers[i])){
                duplication[0] = numbers[i];
                return true;
            }else{
                set.add(numbers[i]);
            }
        }
        return false;
    }

    /**
     * 散列的思想
     * @param nums
     * @param length
     * @param duplication
     * @return
     */
    public static boolean duplicate2(int numbers[],int length,int [] duplication) {

        if (numbers == null || numbers.length < 1){
            duplication[0] = -1;
            return false;
        }
        boolean flag = false;
        int[] room = new int[length];
        Arrays.fill(room,0);
        for (int i =0; i <numbers.length; i++){
            int number = numbers[i];
            if (room[number] == 0){
                room[number] = 1;
            }else {
                flag = true;
                duplication[0] = number;
                return flag;
            }
        }
        return flag;

    }

    public static void main(String[] args) {
        int[] n = {2,4,3,1,4};
        int[] duplication = new int[]{};
        duplicate2(n,n.length,duplication);
    }

}
