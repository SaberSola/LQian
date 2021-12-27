package com.zl.lqian.bytedance;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 394. 字符串解码
 */
public class DecodeString {


    static int ptr;

    /**
     * 输入：s = "3[a]2[bc]"
     * 输出："aaabcbc"
     * 字符串解码
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        /**
         *  字符串解码
         *  1. 将字符串拆解为token
         *
         *  数字, 字母 正中括号 反中括号
         *
         *  进栈顺序,  数字 中括号,字母
         *  碰到中括号 开始出栈
         *
         */

        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;
        while (ptr < s.length()) {
            char c = s.charAt(ptr);
            //判断是不是数字
            //"3[a]2[bc]"
            if (Character.isDigit(c)){
                //是数字的话,进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            }else if (Character.isLetter(c) || c == '['){
                //获取一个字母并入栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            }else {
                //反中括号的情况,需要出栈
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }
        return getString(stk);
    }

    public static String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public static String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
       String s = "3[ab]2[bc]";
       System.out.println(decodeString(s));
    }
}
