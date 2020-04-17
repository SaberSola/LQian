package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-08
 * @Des ${todo}
 *  在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class Solution34 {


    public static int FirstNotRepeatingChar(String str) {

        if(str==null || str.length() == 0)return -1;
        int[] count = new int[256];
        //用一个类似hash的东西来存储字符出现的次数，很方便
        for(int i=0; i < str.length();i++)
            count[str.charAt(i)]++;// acII码值作为数组的下标
        //其实这个第二步应该也是ka我的地方，没有在第一时间想到只要在遍历一遍数组并访问hash记录就可以了
        for(int i=0; i < str.length();i++)
            if(count[str.charAt(i)]==1)
                return i;
        return -1;
    }

    /**
     *  在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar1(String str) {
        if(str==null || str.length() == 0)return -1;
        int[] count = new int[256];
        for(int i=0; i < str.length();i++)
            count[str.charAt(i)]++;
        return 0;
    }

    public static void main(String[] args) {
        String str = "wqeiutqwueytiuqwt";
        //FirstNotRepeatingChar(str);
        System.out.println(str.charAt(8));
        int[] count = new int[256];
        count['a'] = 1;
        count['a']++;
        System.out.println( count['a']);
    }



}
