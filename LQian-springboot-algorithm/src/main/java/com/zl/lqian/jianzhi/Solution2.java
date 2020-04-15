package com.zl.lqian.jianzhi;

/**
 * @Author zl
 * @Date 2020-04-04
 * @Des ${todo}
 *
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Solution2 {

    /**
     * 暴力
     * @param target
     * @return
     */
    private static String replace(String target){
        return target.toString().replace(" ", "%20");
    }


    public String replace(StringBuffer str) {
        int n = str.length();
        for(int i=0; i<n; i++) {
            if(str.charAt(i) == ' ') {
                n += 2;
                str.replace(i, i+1, "%20");
            }
        }
        return str.toString();
    }


}
