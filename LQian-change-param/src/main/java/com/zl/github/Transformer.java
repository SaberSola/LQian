package com.zl.github;

/**
 * @Author zl
 * @Date 2019-09-12
 * @Des ${todo}
 */
public interface Transformer {


    <T, K> K transform(T sourceObj, Class<? extends K> targetClass);

    <T, K> void transform(T sourceObj, K targetObject);
}
