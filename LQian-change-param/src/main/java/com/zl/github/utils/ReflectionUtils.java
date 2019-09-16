package com.zl.github.utils;

import com.zl.github.cache.CacheManager;
import com.zl.github.exception.MissingFieldException;
import com.zl.github.exception.MissingMethodException;
import com.zl.github.model.EmptyValue;
import com.zl.github.model.ItemType;
import com.zl.github.model.MapElemType;
import com.zl.github.model.MapType;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static com.zl.github.cache.CacheManagerFactory.getCacheManager;
import static java.lang.reflect.Modifier.isPublic;
import static com.zl.github.constant.MethodPrefix.GET;
import static com.zl.github.constant.MethodPrefix.IS;
import static com.zl.github.constant.MethodPrefix.SET;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public class ReflectionUtils {

    private static final String BOOLEAN = "boolean";

    private static final String SETTER_METHOD_NAME_REGEX = "^set[A-Z].*";

    private static final String DOT_SPLIT_REGEX = "\\.";

    private static final CacheManager CACHE_MANAGER = getCacheManager("reflectionUtils");

    protected Object invokeMethod(final Method method, final Object target, final Object... args) {
        try {
            return method.invoke(target, args);
        } catch (final Exception e) {
            handleReflectionException(e);
            throw new IllegalStateException(e);
        }
    }


    public boolean isSetter(final Method method) {
        final String cacheKey = "IsSetter-" + method.getDeclaringClass().getName() + '-' + method.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            boolean res = isPublic(method.getModifiers())
                    && method.getName().matches(SETTER_METHOD_NAME_REGEX)
                    && method.getParameterTypes().length == 1
                    && method.getReturnType().equals(void.class);
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }


    public Object getFieldValue(final Object target, final Field field) {
        return getFieldValue(target, field.getName(), field.getType());
    }

    public Object getFieldValue(final Object target, final String fieldName, final Class<?> fieldType) {
        Object fieldValue = getRealTarget(target);
        for (String currFieldName : fieldName.split(DOT_SPLIT_REGEX)) {
            if (fieldValue == null) {
                break;
            }
            try {
                fieldValue = getFieldValueDirectAccess(fieldValue, currFieldName);
            } catch (final Exception e) {
                fieldValue = invokeMethod(getGetterMethod(fieldValue.getClass(), currFieldName, fieldType), fieldValue);
            }
        }
        return fieldValue;
    }

    private Method getGetterMethod(final Class<?> fieldClass, final String fieldName, final Class<?> fieldType) {
        final String cacheKey = "GetterMethod-" + fieldClass.getName() + '-' + fieldName;
        return CACHE_MANAGER.getFromCache(cacheKey, Method.class).orElseGet(() -> {
            try {
                Method method = fieldClass.getMethod(getGetterMethodPrefix(fieldType) + capitalize(fieldName));
                method.setAccessible(true);
                CACHE_MANAGER.cacheObject(cacheKey, method);
                return method;
            } catch (NoSuchMethodException e) {
                throw new MissingFieldException(fieldClass.getName() + " hasn't a field called: " + fieldName + ".");
            }
        });
    }


    public <A extends Annotation> A getFieldAnnotation(final Field field, final Class<A> annotationClazz) {
        final String cacheKey = "FieldAnnotation-" + field.getDeclaringClass().getName() + "-" + field.getName() + "-" + annotationClazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, annotationClazz).orElseGet(() -> {
            A annotation = null;
            if (field.isAnnotationPresent(annotationClazz)) {
                annotation = field.getAnnotation(annotationClazz);
            }
            CACHE_MANAGER.cacheObject(cacheKey, annotation);
            return annotation;
        });
    }

    public <A extends Annotation> A getParameterAnnotation(final Parameter parameter, final Class<A> annotationClazz, final String declaringClassName) {
        final String cacheKey = "ParameterAnnotation-" + declaringClassName + "-" + parameter.getName() + "-" + annotationClazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, annotationClazz).orElseGet(() -> {
            A annotation = null;
            if (parameter.isAnnotationPresent(annotationClazz)) {
                annotation = parameter.getAnnotation(annotationClazz);
            }
            CACHE_MANAGER.cacheObject(cacheKey, annotation);
            return annotation;
        });
    }

    private Object getFieldValueDirectAccess(final Object target, final String fieldName) {
        try {
            Field field = getDeclaredField(fieldName, target.getClass());
            return field.get(target);
        } catch (MissingFieldException e) {
            throw e;
        } catch (final Exception e) {
            handleReflectionException(e);
            throw new IllegalStateException(e);
        }
    }

    private Field getClassDeclaredField(final String fieldName, final Class<?> targetClass) {
        final String cacheKey = "ClassDeclaredField-" + targetClass.getName() + "-" + fieldName;
        return CACHE_MANAGER.getFromCache(cacheKey, Field.class).orElseGet(() -> {
            Field field;
            try {
                field = targetClass.getDeclaredField(fieldName);
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Class<?> superclass = targetClass.getSuperclass();
                if (!superclass.equals(Object.class)) {
                    field = getClassDeclaredField(fieldName, superclass);
                } else {
                    throw new MissingFieldException(targetClass.getName() + " does not contain field: " + fieldName);
                }
            } catch (final Exception e) {
                handleReflectionException(e);
                throw new IllegalStateException(e);
            }
            CACHE_MANAGER.cacheObject(cacheKey, field);
            return field;
        });
    }

    public Field getDeclaredField(final String fieldName, final Class<?> targetClass) {
        final String cacheKey = "ClassDeclaredFieldDotNotation-" + targetClass.getName() + "-" + fieldName;
        return CACHE_MANAGER.getFromCache(cacheKey, Field.class).orElseGet(() -> {
            Field field = null;
            Class<?> currentClass = targetClass;
            for (String currFieldName : fieldName.split(DOT_SPLIT_REGEX)) {
                field = getClassDeclaredField(currFieldName, currentClass);
                currentClass = field.getType();
            }
            CACHE_MANAGER.cacheObject(cacheKey, field);
            return field;
        });
    }

    public Class<?> getDeclaredFieldType(final String fieldName, final Class<?> clazz) {
        final String cacheKey = "FieldType-" + clazz.getName() + "-" + fieldName;
        return CACHE_MANAGER.getFromCache(cacheKey, Class.class).orElseGet(() -> {
            Class<?> fieldType = getDeclaredField(fieldName, clazz).getType();
            CACHE_MANAGER.cacheObject(cacheKey, fieldType);
            return fieldType;
        });
    }

    public void setFieldValue(final Object target, final Field field, final Object fieldValue) {
        try {
            setFieldValueWithoutSetterMethod(target, field, fieldValue);
        } catch (final IllegalArgumentException e) {
            throw e;
        } catch (final Exception e) {
            invokeMethod(getSetterMethodForField(target.getClass(), field.getName(), field.getType()), target, fieldValue);
        }
    }

    private void setFieldValueWithoutSetterMethod(final Object target, final Field field, final Object fieldValue) {
        try {
            field.set(target, fieldValue);
        } catch (final Exception e) {
            handleReflectionException(e);
            throw new IllegalStateException(e);
        }
    }

    private Object getRealTarget(final Object target) {
        AtomicReference<Object> realTarget = new AtomicReference<>(target);
        if (isOptionalType(target)) {
            ((Optional<?>) target).ifPresent(realTarget::set);
        }
        return realTarget.get();
    }

    private boolean isOptionalType(final Object object) {
        return object instanceof Optional;
    }

    private String getGetterMethodPrefix(final Class<?> fieldType) {
        final String cacheKey = "GetterMethodPrefix-" + fieldType.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, String.class).orElseGet(() -> {
            String res = Boolean.class.equals(fieldType) || fieldType.getName().equals(BOOLEAN) ? IS.getPrefix() : GET.getPrefix();
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }


    public Method getSetterMethodForField(final Class<?> fieldClass, final String fieldName, final Class<?> fieldType) {
        final String cacheKey = "SetterMethod-" + fieldClass.getName() + '-' + fieldName;
        return CACHE_MANAGER.getFromCache(cacheKey, Method.class).orElseGet(() -> {
            try {
                Method method = fieldClass.getMethod(SET.getPrefix() + capitalize(fieldName), fieldType);
                method.setAccessible(true);
                CACHE_MANAGER.cacheObject(cacheKey, method);
                return method;
            } catch (NoSuchMethodException e) {
                throw new MissingMethodException(e.getMessage());
            }
        });
    }


    public Class<?> getGenericFieldType(final Field field) {
        final String cacheKey = "GenericFieldType-" + field.getDeclaringClass().getName() + '-' + field.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Class.class).orElseGet(() -> {
            Class<?> res = null;
            if (ParameterizedType.class.isAssignableFrom(field.getGenericType().getClass())) {
                final Type[] fieldArgTypes = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
                if (fieldArgTypes.length != 0) {
                    res = (Class<?>) fieldArgTypes[0];
                }
            }
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }


    public MapType getMapGenericType(final Type fieldType, final String declaringClass, final String fieldName) {
        final Class<?> fieldClass = getArgumentTypeClass(fieldType, declaringClass, fieldName, false);
        final String cacheKey = "MapGenericFieldType-" + fieldClass.getName() + '-' + fieldName;
        return CACHE_MANAGER.getFromCache(cacheKey, MapType.class).orElseGet(() -> {
            if (!Map.class.isAssignableFrom(fieldClass)) {
                throw new IllegalArgumentException("Type for object: " + fieldName + " is invalid. "
                        + "It cannot be assigned from: " + Map.class.getName() + ".");
            }
            final ParameterizedType genericType = (ParameterizedType) fieldType;
            final MapElemType keyType = getMapElemType(genericType.getActualTypeArguments()[0], declaringClass, fieldName);
            final MapElemType elemType = getMapElemType(genericType.getActualTypeArguments()[1], declaringClass, fieldName);
            final MapType mapType = new MapType(keyType, elemType);
            CACHE_MANAGER.cacheObject(cacheKey, mapType);
            return mapType;
        });
    }


    private MapElemType getMapElemType(final Type fieldType, final String declaringClass, final String fieldName) {
        final String cacheKey = "MapElemType-" + declaringClass + "-" + fieldType.getTypeName() + '-' + fieldName;
        return CACHE_MANAGER.getFromCache(cacheKey, MapElemType.class).orElseGet(() -> {
            final Class<?> argumentTypeClass = getArgumentTypeClass(fieldType, declaringClass, fieldName, false);
            final MapElemType res;
            if (Map.class.isAssignableFrom(argumentTypeClass)) {
                res = getMapGenericType(fieldType, declaringClass, fieldName);
            } else {
                res = buildItemType(fieldType, declaringClass, fieldName);
            }
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }

    private ItemType buildItemType(final Object argument, final String declaringClass, final String fieldName) {
        return ItemType.builder()
                .objectClass(getArgumentTypeClass(argument, declaringClass, fieldName, false))
                .genericClass(getArgumentTypeClass(argument, declaringClass, fieldName, true))
                .build();
    }

    public Class<?> getArgumentTypeClass(final Object argument, final String declaringClass, final String fieldName, final boolean getNestedGenericClass) {
        final boolean argumentIsTypeClass = argument instanceof Class;
        final String cacheKey = "ArgumentTypeClass-" + declaringClass + "-" + fieldName + "-" + getNestedGenericClass + "-"
                + "-" + (argumentIsTypeClass || !List.class.isAssignableFrom(argument.getClass()) ? argument : argument.getClass());
        return CACHE_MANAGER.getFromCache(cacheKey, Class.class)
                .map(atc -> atc == EmptyValue.class ? null : atc)
                .orElseGet(() -> {
                    Class<?> res = null;
                    if (argumentIsTypeClass) {
                        res = getNestedGenericClass ? null : (Class<?>) argument;
                    } else if (ParameterizedType.class.isAssignableFrom(argument.getClass())) {
                        res = getNestedGenericClass
                                ? getArgumentTypeClass(((ParameterizedType) argument).getActualTypeArguments()[0], declaringClass, fieldName, false)
                                : (Class<?>) ((ParameterizedType) argument).getRawType();
                    }
                    CACHE_MANAGER.cacheObject(cacheKey, res, EmptyValue.class);
                    return res;
                });
    }


    public Class<?> getArrayType(final Field arrayField) {
        final String cacheKey = "ArrayType-" + arrayField.getDeclaringClass().getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Class.class).orElseGet(() -> {
            final Class<?> arrayType = arrayField.getType().getComponentType();
            CACHE_MANAGER.cacheObject(cacheKey, arrayType);
            return arrayType;
        });
    }


    void handleReflectionException(final Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            throw new MissingMethodException("Method not found: " + ex.getMessage());
        } else if (ex instanceof IllegalAccessException) {
            throw new IllegalStateException("Could not access method: " + ex.getMessage(), ex);
        } else {
            if (ex instanceof RuntimeException) {
                throw (RuntimeException) ex;
            } else {
                throw new UndeclaredThrowableException(ex);
            }
        }
    }
}
