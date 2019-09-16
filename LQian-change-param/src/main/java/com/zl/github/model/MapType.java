package com.zl.github.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@AllArgsConstructor
@Getter
public class MapType implements MapElemType{

    /**
     * key object class.
     */
    private final MapElemType keyType;

    /**
     * elem object class.
     */
    private final MapElemType elemType;
}

