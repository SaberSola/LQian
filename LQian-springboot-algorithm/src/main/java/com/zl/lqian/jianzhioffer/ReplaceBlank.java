package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-09-27
 * @Des 替换字符串的空格
 */
public class ReplaceBlank {


    public static String replaceBlank1(String input) {
        if (input == null) {
            return null;
        }
        StringBuffer outputBuffer = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                outputBuffer.append("%20");
            } else {
                outputBuffer.append(String.valueOf(input.charAt(i)));
            }
        }
        return outputBuffer.toString();
    }

    /**
     * StringBuilder
     * @param input
     * @return
     */
    public static String replaceBlank2(String input) {

        if (input == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (String.valueOf(input.charAt(i)).equals(" ")) {
                sb.append("%20");
            } else {
                sb.append(input.charAt(i));
            }
        }
        return String.valueOf(sb);
    }
}
