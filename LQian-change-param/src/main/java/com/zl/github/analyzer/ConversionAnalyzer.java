package com.zl.github.analyzer;

import com.zl.github.analyzer.processor.ConversionProcessor;
import com.zl.github.cache.CacheManager;
import com.zl.github.utils.ClassUtils;

import java.util.Optional;
import java.util.function.Function;

import static com.zl.github.analyzer.processor.ConversionProcessorFactory.getConversionProcessor;
import static com.zl.github.cache.CacheManagerFactory.getCacheManager;
import static com.zl.github.utils.ClassUtils.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public final class ConversionAnalyzer {

    private static final CacheManager CACHE_MANAGER = getCacheManager("conversionAnalyzer");

    private final ClassUtils classUtils;

    public ConversionAnalyzer() {
        this.classUtils = new ClassUtils();
    }

    public Optional<Function<Object,Object>> getConversionFunction(Class<?> sourceClass,Class<?> targetClass){
        final String cacheKey = "ConversionFunction-" + sourceClass.getName() + "-" + targetClass.getName();
        return CACHE_MANAGER.getFromCache(cacheKey,Optional.class).orElseGet(()->{
            Optional conversionFunction = empty();
            if (!targetClass.getSimpleName().equalsIgnoreCase(sourceClass.getSimpleName())
            && (classUtils.isPrimitiveType(sourceClass) || classUtils.isPrimitiveTypeArray(sourceClass))){
                conversionFunction = getConversionProcessor(targetClass)
                        .flatMap(conversionProcessor -> getTypeConversionFunction(conversionProcessor,targetClass));
            }
            CACHE_MANAGER.cacheObject(cacheKey, conversionFunction);
            return conversionFunction;
        });

    }

    private Optional<Function<?, ?>> getTypeConversionFunction(final ConversionProcessor conversionProcessor, final Class<?> sourceFieldType) {
        Optional<Function<?, ?>> conversionFunction = empty();
        if (isString(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertString());
        } else if (isByte(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertByte());
        } else if (isShort(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertShort());
        } else if (isInt(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertInteger());
        } else if (isLong(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertLong());
        } else if (isFloat(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertFloat());
        } else if (isDouble(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertDouble());
        } else if (isChar(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertCharacter());
        } else if (isBoolean(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertBoolean());
        } else if (isBigInteger(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertBigInteger());
        } else if (isBigDecimal(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertBigDecimal());
        } else if (isByteArray(sourceFieldType)) {
            conversionFunction = of(conversionProcessor.convertByteArray());
        }
        return conversionFunction;
    }

}
