package com.zl.lqian.bytedance;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 */
public class Solution5 {

    public boolean isValid(String s) {

        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Stack stack = new Stack();
        Map<Character, Character> pairs = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            };
        };
        for (int i =0; i < n; i ++){
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)){
                //命中了反括号
                if (stack.empty() || stack.peek() != pairs.get(ch)){
                    return false;
                }
            }else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
