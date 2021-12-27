package com.zl.lqian.bytedance;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 */
public class IsValid {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */

    private final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('{', '}');
        put('[', ']');
        put('(', ')');
        put('?', '?');
    }};

    public boolean isValid(String s) {
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>() {
            {
                add('?');
            }
        };
        for (Character character : s.toCharArray()) {
            if (map.containsKey(character)) {
                stack.add(character);
            } else {
                if (!map.get(stack.pop()).equals(character)) {
                    return false;
                }
            }
        }
        return stack.size() == 1;
    }
}
