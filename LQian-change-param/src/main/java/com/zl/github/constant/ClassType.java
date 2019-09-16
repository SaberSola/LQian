package com.zl.github.constant;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public enum  ClassType {


    MUTABLE,
    /**
     * The class is immutable.
     */
    IMMUTABLE,
    /**
     * The class contains both final and not final fields.
     */
    MIXED;

    /**
     * Checks if a the class type  instance is equal to the given one.
     * @param classType the {@link ClassType} to which compare
     * @return true if this is equals to the given class type
     */
    public boolean is(final ClassType classType) {
        return this == classType;
    }

}
