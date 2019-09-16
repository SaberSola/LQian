package com.zl.github.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@AllArgsConstructor
@Getter
@ToString
public class FieldMapping {

    private final String sourceFieldName;

    private final String destFieldName;
}
