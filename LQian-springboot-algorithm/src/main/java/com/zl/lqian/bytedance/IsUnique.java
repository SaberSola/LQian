package com.zl.lqian.bytedance;

public class IsUnique {

    public boolean isUnique(String s) {
        /**
         * a & (1<<k) 用于判断a的第k位数字是0是1，其实和我们使用数组差不错。相等于 nums[k];
         *
         * a | (1<<k) 用于将a的第k位数字赋值为1, 相当于nums[k]=1
         *
         * ASCII码的字符个数为128个，我们可以使用两个64位，8字节的long变量来存储是否出现某个字符，二进制位1表示出现过，0表示没有出现过。
         *
         */
        long left = 0;
        long right = 0;
        for (char c : s.toCharArray()) {
            if (c >= 64) {
                long bitIndex = 1L << (c - 64);
                if ((left & bitIndex) != 0) {
                    return false;
                }
                left |= bitIndex;
            } else {
                long bitIndex = 1L << c;
                if ((right & bitIndex) != 0) {
                    return false;
                }
                right |= bitIndex;
            }
        }
        return true;
    }
}
