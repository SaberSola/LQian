package com.zl.github.translate;

import com.zl.github.constant.ClassType;
import com.zl.github.constant.Punctuation;
import com.zl.github.exception.InvalidBeanException;
import com.zl.github.exception.MissingFieldException;
import com.zl.github.model.FieldTransformer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import static com.zl.github.constant.Defaults.defaultValue;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static com.zl.github.constant.ClassType.MIXED;
import static com.zl.github.constant.ClassType.MUTABLE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

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
                k = (K) classUtils.getInstance(classUtils.getNoArgsConstructor(targetClass));
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

    /**
     * all fields name equals
     *
     * @param sourceObj
     * @param targetObject
     * @param breadcrumb
     * @param <T>
     * @param <K>
     */
    private <T, K> void injectAllFields(final T sourceObj, final K targetObject, final String breadcrumb) {
        Class<?> targetObjectClass = targetObject.getClass();
        classUtils.getDeclaredFields(targetObjectClass, true)
                .forEach(field -> reflectionUtils.setFieldValue(targetObject, field, ));
    }

    private <T, K> void injectNotFinalFields(final T sourceObj, final K targetObject, final String breadcrumb) {

    }


    private <T, K> Object getFieldValue(final T sourceObj, final Class<K> targetClass, final Field field, final String breadcrumb) {
        String sourceFieldName = getSourceFieldName(field);
        return null;
    }

    private <T, K> Object getFieldValue(final T sourceObj, final String sourceFieldName, final Class<K> targetClass, final Field field, final String breadcrumb) {
        String fieldBreadcrumb = evalBreadcrumb(field.getName(), breadcrumb);
        Class<?> fieldType = field.getType();
        /**
         * jump the static
         */
        if (doSkipTransformation(breadcrumb)) {
            return defaultValue(fieldType);
        }
        boolean primitiveType = classUtils.isPrimitiveType(fieldType);
        /**
         * get transformer function
         */
        FieldTransformer transformerFunction = getTransformerFunction(field, fieldBreadcrumb);
        boolean isTransformerFunctionDefined = nonNull(transformerFunction);
        Object fieldValue = getSourceFieldValue(sourceObj, sourceFieldName, field, isTransformerFunctionDefined);
        if (nonNull(fieldValue)){
            
        }

    }

    private String evalBreadcrumb(final String fieldName, final String breadcrumb) {
        return (isNotEmpty(breadcrumb) ? breadcrumb + Punctuation.DOT.getSymbol() : EMPTY) + fieldName;
    }

    private boolean doSkipTransformation(final String breadcrumb) {
        return settings.getFieldsToSkip().contains(breadcrumb);
    }

    private FieldTransformer getTransformerFunction(final Field field, final String breadcrumb) {
        return settings.getFieldsTransformers().get(settings.isFlatFieldNameTransformation() ? field.getName() : breadcrumb);
    }

    private String getSourceFieldName(Field field) {
        return getSourceFieldName(field.getName());
    }


    private String getSourceFieldName(final String fieldName) {
        return ofNullable(settings.getFieldsNameMapping().get(fieldName)).orElse(fieldName);
    }

    private <T> Object getSourceFieldValue(final T sourceObj, final String sourceFieldName, final Field field, final boolean isFieldTransformerDefined) {
        Object fieldValue = null;
        try{
            fieldValue = reflectionUtils.getFieldValue(sourceObj, sourceFieldName, field.getType());
        }catch (MissingFieldException miss){
            if (classUtils.isPrimitiveType(sourceObj.getClass())) {
                fieldValue = sourceObj;
            } else if (!isFieldTransformerDefined && !settings.isSetDefaultValueForMissingField()) {
                throw miss;
            }
        }
        catch (Exception e){
            if (!isFieldTransformerDefined) {
                throw e;
            }
        }
        return fieldValue;
    }

}
