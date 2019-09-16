package com.zl.github.utils;

import com.zl.github.cache.CacheManager;
import com.zl.github.constant.ClassType;
import com.zl.github.exception.InstanceCreationException;
import com.zl.github.exception.InvalidBeanException;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.function.Predicate;

import static com.zl.github.cache.CacheManagerFactory.getCacheManager;
import static com.zl.github.constant.ClassType.*;
import static com.zl.github.constant.Filters.*;
import static java.lang.reflect.Modifier.*;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Collections.max;
import static java.util.Collections.min;
import static java.util.Comparator.comparing;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.Validate.notNull;
import static com.zl.github.constant.Defaults.defaultValue;
/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public class ClassUtils<T> {


    private static final String CLAZZ_CANNOT_BE_NULL = "clazz cannot be null!";

    private static final CacheManager CACHE_MANAGER = getCacheManager("classUtils");
    private static final Set<Class<?>> PRIMITIVE_TYPES = new HashSet<>(asList(String.class, Boolean.class, Integer.class, Long.class,
            Double.class, BigDecimal.class, BigInteger.class, Short.class, Float.class, Character.class, Byte.class, Void.class, Date.class, LocalDateTime.class,
            LocalDate.class));

    private final ReflectionUtils reflectionUtils;

    public ClassUtils() {
        this.reflectionUtils = new ReflectionUtils();
    }

    public boolean isPrimitiveOrSpecialType(final Class<?> clazz) {
        final String cacheKey = "isPrimitiveOrSpecial-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            final Boolean res = isPrimitiveType(clazz) || isSpecialType(clazz);
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }


    public boolean isPrimitiveType(final Class<?> clazz) {
        final String cacheKey = "isPrimitive-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            final Boolean res = clazz.isPrimitive() || PRIMITIVE_TYPES.contains(clazz) || clazz.isEnum();
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }


    public boolean isPrimitiveTypeArray(final Class<?> clazz) {
        final String cacheKey = "isPrimitiveTypeArray-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            final Boolean res = clazz.isArray() && isPrimitiveType(clazz.getComponentType());
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }


    public boolean isSpecialType(final Class<?> clazz) {
        final String cacheKey = "isSpecial-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            final Boolean res = clazz.equals(Currency.class) || clazz.equals(Locale.class) || Temporal.class.isAssignableFrom(clazz)
                    || clazz.isSynthetic();
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }


    public static boolean isDouble(final Class<?> type) {
        return Double.class.isAssignableFrom(type) || type == double.class;
    }

    public static boolean isFloat(final Class<?> type) {
        return Float.class.isAssignableFrom(type) || type == float.class;
    }

    public static boolean isLong(final Class<?> type) {
        return Long.class.isAssignableFrom(type) || type == long.class;
    }

    public static boolean isShort(final Class<?> type) {
        return Short.class.isAssignableFrom(type) || type == short.class;
    }

    public static boolean isInt(final Class<?> type) {
        return Integer.class.isAssignableFrom(type) || type == int.class;
    }

    public static boolean isByte(final Class<?> type) {
        return Byte.class.isAssignableFrom(type) || type == byte.class;
    }

    public static boolean isChar(final Class<?> type) {
        return Character.class.isAssignableFrom(type) || type == char.class;
    }

    public static boolean isBoolean(final Class<?> type) {
        return Boolean.class.isAssignableFrom(type) || type == boolean.class;
    }

    public static boolean isString(final Class<?> type) {
        return type == String.class;
    }

    public static boolean isBigInteger(final Class<?> type) {
        return type == BigInteger.class;
    }

    public static boolean isBigDecimal(final Class<?> type) {
        return type == BigDecimal.class;
    }

    public static boolean isByteArray(final Class<?> type) {
        return type == byte[].class;
    }

    public static boolean isLocalDateTime(final Class<?> type){return LocalDateTime.class.isAssignableFrom(type);}


    public static boolean isLocalDate(final Class<?> type){return LocalDate.class.isAssignableFrom(type);}
    public int getTotalFields(final Class<?> clazz, final Predicate<? super Field> predicate) {
        notNull(clazz, CLAZZ_CANNOT_BE_NULL);
        final String cacheKey = "TotalFields-" + clazz.getName() + '-' + predicate;
        return CACHE_MANAGER.getFromCache(cacheKey, Integer.class).orElseGet(() -> {
            List<Field> declaredFields = getDeclaredFields(clazz, true);
            int res = ofNullable(predicate)
                    .map(filter -> (int) declaredFields.stream().filter(filter).count())
                    .orElseGet(declaredFields::size);
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }

    public List<Field> getPrivateFields(final Class<?> clazz) {
        return getPrivateFields(clazz, false);
    }

    public List<Field> getPrivateFields(final Class<?> clazz, final boolean skipFinal) {
        notNull(clazz, CLAZZ_CANNOT_BE_NULL);
        final String cacheKey = "PrivateFields-" + clazz.getName() + "-skipFinal-" + skipFinal;
        return CACHE_MANAGER.getFromCache(cacheKey, List.class).orElseGet(() -> {
            final List<Field> res = new ArrayList<>();
            if (nonNull(clazz.getSuperclass()) && !clazz.getSuperclass().equals(Object.class)) {
                res.addAll(getPrivateFields(clazz.getSuperclass(), skipFinal));
            }
            res.addAll(stream(getDeclaredFields(clazz))
                    //.parallel()
                    .filter(field -> isPrivate(field.getModifiers())
                            && (!skipFinal || !isFinal(field.getModifiers()))
                            && !isStatic(field.getModifiers()))
                    .collect(toList()));
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }

    public List<Field> getDeclaredFields(final Class<?> clazz, final boolean skipStatic) {
        final String cacheKey = "DeclaredFields-" + clazz.getName() + "-skipStatic-" + skipStatic;
        return CACHE_MANAGER.getFromCache(cacheKey, List.class).orElseGet(() -> {
            final List<Field> res = new ArrayList<>();
            if (nonNull(clazz.getSuperclass()) && !clazz.getSuperclass().equals(Object.class)) {
                res.addAll(getDeclaredFields(clazz.getSuperclass(), skipStatic));
            }
            stream(getDeclaredFields(clazz))
                    .filter(field -> !skipStatic || !isStatic(field.getModifiers()))
                    .forEach(field -> {
                        field.setAccessible(true);
                        res.add(field);
                    });
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }

    private Field[] getDeclaredFields(final Class<?> clazz) {
        final String cacheKey = "ClassDeclaredFields-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Field[].class).orElseGet(() -> {
            Field[] res = clazz.getDeclaredFields();
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }


    public <K> boolean hasAccessibleConstructors(final Class<K> targetClass) {
        final String cacheKey = "HasAccessibleConstructors-" + targetClass.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            final boolean res = stream(targetClass.getDeclaredConstructors()).anyMatch(constructor -> isPublic(constructor.getModifiers()));
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }


    public Class[] getDeclareClasses(final Class<?> clazz){
        notNull(clazz,CLAZZ_CANNOT_BE_NULL);
        String cacheKey = "DeclaredClasses-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey,Class[].class).orElseGet(()->{
            Class[] declaredClasses = clazz.getDeclaredClasses();
            CACHE_MANAGER.cacheObject(cacheKey,declaredClasses);
            return declaredClasses;
        });
    }


    public <T> T getInstance(final Constructor constructor,final Object... constructorArgs){
        try {
            return (T) constructor.newInstance(constructorArgs);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new InstanceCreationException(e.getMessage(), e);
        }

    }

    public <K> Constructor getNoArgsConstructor(final Class<K> clazz) {
        final String cacheKey = "NoArgsConstructor-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Constructor.class).orElseGet(() -> {
            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
            final Constructor constructor = min(asList(declaredConstructors), comparing(Constructor::getParameterCount));
            if (constructor.getParameterCount() != 0) {
                throw new InvalidBeanException("No default constructors available");
            }
            constructor.setAccessible(true);
            CACHE_MANAGER.cacheObject(cacheKey, constructor);
            return constructor;
        });
    }

    public <K> Constructor getAllArgsConstructor(final Class<K> clazz) {
        final String cacheKey = "AllArgsConstructor-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Constructor.class).orElseGet(() -> {
            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
            final Constructor constructor = max(asList(declaredConstructors), comparing(Constructor::getParameterCount));
            constructor.setAccessible(true);
            CACHE_MANAGER.cacheObject(cacheKey, constructor);
            return constructor;
        });
    }

    public Parameter[] getConstructorParameters(final Constructor constructor) {
        final String cacheKey = "ConstructorParams-" + constructor.getDeclaringClass().getName() + '-' + constructor.getParameterCount();
        return CACHE_MANAGER.getFromCache(cacheKey, Parameter[].class).orElseGet(() -> {
            final Parameter[] parameters = constructor.getParameters();
            CACHE_MANAGER.cacheObject(cacheKey, parameters);
            return parameters;
        });
    }

    public boolean hasField(final Object target, final String fieldName) {
        final String cacheKey = "ClassHasField-" + target.getClass().getName() + '-' + fieldName;
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            boolean hasField;
            try {
                hasField = nonNull(target.getClass().getDeclaredField(fieldName));
            } catch (final NoSuchFieldException e) {
                hasField = false;
                final Class<?> superclass = target.getClass().getSuperclass();
                if (nonNull(superclass) && !superclass.equals(Object.class)) {
                    hasField = hasField(superclass, fieldName);
                }
            }
            CACHE_MANAGER.cacheObject(cacheKey, hasField);
            return hasField;
        });
    }



    public boolean hasSetterMethods(final Class<?> clazz) {
        notNull(clazz, CLAZZ_CANNOT_BE_NULL);
        final String cacheKey = "HasSetterMethods-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class)
                .orElseGet(() -> {
                    final Boolean res = stream(getDeclaredMethods(clazz)).anyMatch(reflectionUtils::isSetter);
                    CACHE_MANAGER.cacheObject(cacheKey, res);
                    return res;
                });
    }


    private Method[] getDeclaredMethods(final Class<?> clazz) {
        notNull(clazz, CLAZZ_CANNOT_BE_NULL);
        final String cacheKey = "DeclaredMethods-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Method[].class)
                .orElseGet(() -> {
                    final Method[] res = clazz.getDeclaredMethods();
                    CACHE_MANAGER.cacheObject(cacheKey, res);
                    return res;
                });
    }

    public boolean hasFinalFields(final Class<?> clazz) {
        notNull(clazz, CLAZZ_CANNOT_BE_NULL);
        return hasFieldsMatchingCondition(clazz, IS_FINAL_AND_NOT_STATIC_FIELD, "HasFinalNotStaticFields-");
    }

    private boolean hasNotFinalFields(final Class<?> clazz) {
        notNull(clazz, CLAZZ_CANNOT_BE_NULL);
        return hasFieldsMatchingCondition(clazz, IS_NOT_FINAL_AND_NOT_STATIC_FIELD, "HasNotFinalNotStaticFields-");
    }

    private boolean hasFieldsMatchingCondition(final Class<?> clazz, final Predicate<Field> filterPredicate, final String cacheKey) {
        return CACHE_MANAGER.getFromCache(cacheKey + clazz.getName(), Boolean.class).orElseGet(() -> {
            boolean res = stream(getDeclaredFields(clazz))
                    //.parallel()
                    .anyMatch(filterPredicate);
            if (!res && nonNull(clazz.getSuperclass()) && !clazz.getSuperclass().equals(Object.class)) {
                Class<?> superclass = clazz.getSuperclass();
                res = hasFieldsMatchingCondition(superclass, filterPredicate, cacheKey);
            }
            CACHE_MANAGER.cacheObject(cacheKey + clazz.getName(), res);
            return res;
        });
    }


    public boolean containsAnnotation(final Constructor constructor, final Class<? extends Annotation> annotationClass) {
        final String cacheKey = "ConstructorHasAnnotation-" + constructor.getDeclaringClass().getName() + '-' + annotationClass.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            final boolean containsAnnotation = stream(constructor.getParameters())
                    .noneMatch(parameter -> isNull(parameter.getAnnotation(annotationClass)));
            CACHE_MANAGER.cacheObject(cacheKey, containsAnnotation);
            return containsAnnotation;
        });
    }

    public boolean allParameterAnnotatedWith(final Constructor constructor, final Class<? extends Annotation> annotationClass) {
        final String cacheKey = "AllParameterAnnotatedWith-" + constructor.getDeclaringClass().getName() + '-' + annotationClass.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            final boolean notAllAnnotatedWith = stream(constructor.getParameters())
                    .allMatch(parameter -> nonNull(parameter.getAnnotation(annotationClass)));
            CACHE_MANAGER.cacheObject(cacheKey, notAllAnnotatedWith);
            return notAllAnnotatedWith;
        });
    }


    public boolean areParameterNamesAvailable(final Constructor constructor) {
        final String cacheKey = "AreParameterNamesAvailable-" + constructor.getDeclaringClass().getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Boolean.class).orElseGet(() -> {
            final boolean res = stream(getConstructorParameters(constructor))
                    .anyMatch(Parameter::isNamePresent);
            CACHE_MANAGER.cacheObject(cacheKey, res);
            return res;
        });
    }

    public ClassType getClassType(final Class<?> clazz) {
        final String cacheKey = "ClassType-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, ClassType.class).orElseGet(() -> {
            final ClassType classType;
            boolean hasFinalFields = hasFinalFields(clazz);
            if (!hasFinalFields) {
                classType = MUTABLE;
            } else {
                boolean hasNotFinalFields = hasNotFinalFields(clazz);
                if (hasNotFinalFields) {
                    classType = MIXED;
                } else {
                    classType = IMMUTABLE;
                }
            }
            CACHE_MANAGER.cacheObject(cacheKey, classType);
            return classType;
        });
    }
    public List<Method> getSetterMethods(final Class<?> clazz) {
        notNull(clazz, CLAZZ_CANNOT_BE_NULL);
        final String cacheKey = "SetterMethods-" + clazz.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, List.class).orElseGet(() -> {
            final List<Method> setterMethods = new LinkedList<>();
            if (nonNull(clazz.getSuperclass()) && !clazz.getSuperclass().equals(Object.class)) {
                setterMethods.addAll(getSetterMethods(clazz.getSuperclass()));
            }
            setterMethods.addAll(stream(getDeclaredMethods(clazz))
                    .filter(reflectionUtils::isSetter)
                    .collect(toList()));
            CACHE_MANAGER.cacheObject(cacheKey, setterMethods);
            return setterMethods;
        });
    }


    public Object getDefaultTypeValue(final Class<?> objectType) {
        final String cacheKey = "DefaultTypeValue-" + objectType.getName();
        return CACHE_MANAGER.getFromCache(cacheKey, Object.class).orElseGet(() -> {
            final Object defaultValue = isPrimitiveType(objectType) ? defaultValue(objectType) : null;
            CACHE_MANAGER.cacheObject(cacheKey, defaultValue);
            return defaultValue;
        });
    }

    public List<Field> getNotFinalFields(final Class<?> clazz, final Boolean skipStatic) {
        final String cacheKey = "NotFinalFields-" + clazz.getName() + "-" + skipStatic;
        return CACHE_MANAGER.getFromCache(cacheKey, List.class).orElseGet(() -> {
            List<Field> notFinalFields = getDeclaredFields(clazz, skipStatic)
                    .stream()
                    .filter(IS_NOT_FINAL_FIELD).collect(toList());
            CACHE_MANAGER.cacheObject(cacheKey, notFinalFields);
            return notFinalFields;
        });
    }


}

