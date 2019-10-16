package com.zl.lqian.jianzhioffer;

/**
 * @Author zl
 * @Date 2019-10-15
 * @Des 打印字符串顺序
 */
public class Permutation {


    public static String permutation1(String str) {

        if (str == null || str.length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (str.length() == 1) {
            sb.append(str);
            return String.valueOf(sb);
        }

        char[] output = str.toCharArray();
        int point = 0;
        sb.append(output).append(",");
        while (!String.valueOf(output).equals(str)) {
            sb.append(output).append(",");
            if (point == output.length - 1) {
                swap(output, point, 0);
                point = 0;
            } else {
                swap(output, point, ++point);
            }
        }
        return String.valueOf(sb);
    }

    /**
     * 交换两个字符的位置
     *
     * @param output
     * @param i
     * @param j
     */
    public static void swap(char[] output, int i, int j) {
        char temp = output[i];
        output[i] = output[j];
        output[j] = temp;
    }
}
