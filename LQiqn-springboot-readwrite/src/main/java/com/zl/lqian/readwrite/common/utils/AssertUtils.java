package com.zl.lqian.readwrite.common.utils;


/**
 * @Author zl
 * 断言教研参数
 *
 */
public final class AssertUtils {


    private AssertUtils() {

    }

    public static void notNull(final Object obj) {
        if (obj == null) {
            throw new RuntimeException("argument invalid,Please check");
        }
    }
}
