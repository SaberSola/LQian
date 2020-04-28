package com.zl.lqian.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * @Author zl
 * @Date 2020-04-28
 * @Des ${todo}
 */
public class Solution15 {


    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串
     * 输入: "()"
     * 输出: true。
     *
     * 输入: "()[]{}"
     * 输出: true
     *
     * 输入: "(]"
     * 输出: false
     *
     * "{[]}"
     * 输出: true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 采用栈实现
     * @param s
     * @return
     */
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        Stack<Character> stack = new Stack<Character>() {{ add('?'); }};
        for (Character c : s.toCharArray()){
            if(map.containsKey(c)){
                stack.add(c);
            }else if (map.get(stack.pop()) != c){
                return false;
            }
        }
        return stack.size() == 1;
    }
}
