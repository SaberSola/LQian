package com.zl.lqian.jianzhi;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author zl
 * @Date 2020-04-11
 * @Des ${todo}
 */
public class Solution46 {


    /**
     * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
     * HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
     * 然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
     * 然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,
     * 可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
     * <p>
     * 1 2 3 4
     * 12      5
     * 11      6
     * 10  9 8 7
     *
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) return -1;
        //构建列表
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int remove = 0;
        while (list.size() != 1) {
            remove = (remove + m - 1) % list.size();
            list.remove(remove);
        }
        return list.get(0);
    }
}
