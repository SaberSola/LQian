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


    public static void main(String[] args) {
        System.out.println(replaceSpace("We Are Happy"));
    }

    /**
     * 暴力
     * @param target
     * @return
     */
    private static String replace(String target){
        return target.toString().replace(" ", "%20");
    }

    public static String replaceSpace(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c == ' '){
                sb.append("%20");
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
