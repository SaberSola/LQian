package com.zl.lqian.bytedance;

/**
 * 168. Excel表列名称
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如：
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ConvertToTitle {


    public String convertToTitle(int columnNumber) {
        //其实就是转换26进制
        StringBuffer sb = new StringBuffer();
        while (columnNumber > 0) {
            int a = (columnNumber - 1) % 26 + 1;
            sb.append(a - 1 + 'A');
            columnNumber = (columnNumber - a) / 26;
        }
        return sb.reverse().toString();
    }

}
