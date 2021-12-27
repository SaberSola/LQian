package com.zl.lqian.onehundred.twofen;

public class MinEatingSpeed {


    /**
     * 这里总共有 N 堆香蕉，第 i 堆中有piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。 阿珂可以决定她吃香蕉的速度 K （单位：根/小时），
     * 每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
     * <p>
     * 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）
     * 输入: piles = [3,6,7,11], H = 8
     * <p>
     * 输入: piles = [30,11,23,4,20], H = 5
     * 输出: 30
     * 输出: 4
     *
     * @param piles
     * @param H
     * @return
     */
    static int minEatingSpeed(int[] piles, int H) {
        int maxVal = 1;
        for (int pile : piles){
            maxVal = Math.max(maxVal,pile);
        }
        int left = 1;
        int right = maxVal;
        while (left < right){
            int mid = (left + right) >> 1;
            if (canEat(piles,mid,H)){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;

    }

    static boolean canEat(int[] piles, int speed, int H) {
        int sum = 0;
        for (int pile : piles){
            sum+= Math.ceil(pile * 1.0 / speed);
        }
        return sum > H;
    }
}