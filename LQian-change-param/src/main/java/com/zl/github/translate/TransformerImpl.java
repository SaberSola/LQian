package com.zl.github.translate;

import com.zl.github.constant.ClassType;
import com.zl.github.exception.InvalidBeanException;

import java.lang.reflect.Constructor;

import static com.zl.github.constant.ClassType.MIXED;
import static com.zl.github.constant.ClassType.MUTABLE;

/**
 * @Author zl
 * @Date 2019-09-14
 * @Des ${todo}
 */
public class TransformerImpl extends AbsTransformer {


    @Override
    protected final <T, K> K transform(final T sourceObj, final Class<? extends K> targetClass, final String breadcrumb) {
        final K k;
        final ClassType classType = classUtils.getClassType(targetClass);
        if (classType.is(MUTABLE)) {
            try {
                k = (K)classUtils.getInstance(classUtils.getNoArgsConstructor(targetClass));
                injectAllFields(sourceObj, k, breadcrumb);
            } catch (Exception e) {
                throw new InvalidBeanException(e.getMessage(), e);
            }
        } else {
            k = injectValues(sourceObj, targetClass, classUtils.getAllArgsConstructor(targetClass), breadcrumb);
            if (classType.is(MIXED)) {
                injectNotFinalFields(sourceObj, k, breadcrumb);
            }
        }
        return k;
    }


    @Override
    protected <T, K> void transform(T sourceObj, K targetObject, String breadcrumb) {

    }

    private <T, K> K injectValues(final T sourceObj, final Class<K> targetClass, final Constructor constructor, final String breadcrumb) {
        return null;
    }

    private <T, K> void injectAllFields(final T sourceObj, final K targetObject, final String breadcrumb) {

    }

    private <T, K> void injectNotFinalFields(final T sourceObj, final K targetObject, final String breadcrumb) {

    }

}
