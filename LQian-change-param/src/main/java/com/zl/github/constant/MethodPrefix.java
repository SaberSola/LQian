package com.zl.github.constant;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public enum  MethodPrefix {
    GET,
    /**
     * Setter method prefix.
     */
    SET,
    /**
     * Boolean method prefix.
     */
    IS;

    /**
     * Gets the method prefix.
     * @return the method prefix.
     */
    public final String getPrefix() {
        return name().toLowerCase();
    }
}
