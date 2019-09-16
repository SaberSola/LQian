package com.zl.github.constant;

import lombok.NoArgsConstructor;

import static com.zl.github.utils.ClassUtils.*;
import static java.lang.Boolean.FALSE;
import static lombok.AccessLevel.PRIVATE;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@NoArgsConstructor(access = PRIVATE)
public final class Defaults {

    public static Object defaultValue(final Class<?> type) {
        Object res = null;
        if (isBoolean(type)) {
            res = FALSE;
        } else if (isChar(type)) {
            res = '\u0000';
        } else if (isByte(type) || isInt(type) || isShort(type)) {
            res = 0;
        } else if (isLong(type)) {
            res = 0L;
        } else if (isFloat(type)) {
            res = 0.0F;
        } else if (isDouble(type)) {
            res = 0.0D;
        }
        return res;
    }


}
