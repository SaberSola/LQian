package com.zl.github.model;

import lombok.Builder;
import lombok.Getter;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@Getter
@Builder
public class ItemType implements MapElemType {

    /**
     * The object class.
     * i.e. In case of: {@code Map<List<String>, Integer>} the value wil be {@code List}
     */
    private final Class<?> objectClass;

    /**
     * The generic object class in the key object (if any).
     * i.e. In case of: {@code Map<List<String>, Integer>} the value wil be {@code String}
     */
    private final Class<?> genericClass;

}
