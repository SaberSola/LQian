package com.zl.lqian.onehundred.String;

public class RotateString {

    /**
     * 旋转字符串
     *
     * @param A
     * @param B
     * @return Boolean
     * 输入: A = 'abcde', B = 'cdeab'
     * 输出: true
     * <p>
     * 输入: A = 'abcde', B = 'abced'
     * 输出: false
     */
    public boolean rotateString(String A, String B) {
        if (A.equals("") && B.equals("")) {
            return true;
        }
        int len = A.length();
        for (int i = 0; i < len; i++) {
            String first = A.substring(0,1);
            String last = A.substring(1, len);
            A = last + first;
            if (A.equals(B)){
                return true;
            }
        }
        return false;

    }

}
