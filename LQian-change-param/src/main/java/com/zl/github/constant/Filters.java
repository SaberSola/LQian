package com.zl.github.constant;

import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import static java.lang.reflect.Modifier.isFinal;
import static java.lang.reflect.Modifier.isStatic;
import static lombok.AccessLevel.PRIVATE;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@NoArgsConstructor(access = PRIVATE)
public class Filters {

    public static final Predicate<Field> IS_FINAL_AND_NOT_STATIC_FIELD = field -> {
        int modifiers = field.getModifiers();
        return isFinal(modifiers) && !isStatic(modifiers);
    };

    public static final Predicate<Field> IS_NOT_FINAL_AND_NOT_STATIC_FIELD = field -> {
        int modifiers = field.getModifiers();
        return !isFinal(modifiers) && !isStatic(modifiers);
    };

    private static final Predicate<Field> IS_FINAL_FIELD = field -> isFinal(field.getModifiers());

    public static final Predicate<Field> IS_NOT_FINAL_FIELD = IS_FINAL_FIELD.negate();

}
