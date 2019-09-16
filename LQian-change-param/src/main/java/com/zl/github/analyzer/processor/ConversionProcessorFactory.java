package com.zl.github.analyzer.processor;

import lombok.NoArgsConstructor;

import java.util.Optional;

import static com.zl.github.utils.ClassUtils.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@NoArgsConstructor(access = PRIVATE)
public final class ConversionProcessorFactory {


    public static Optional<ConversionProcessor> getConversionProcessor(final Class<?> clazz) {
        Optional<ConversionProcessor> conversionProcessor = empty();
        if (isByte(clazz)) {
            conversionProcessor = of(new ByteConversionProcessor());
        } else if (isShort(clazz)) {
            conversionProcessor = of(new ShortConversionProcessor());
        } else if (isInt(clazz)) {
            conversionProcessor = of(new IntegerConversionProcessor());
        } else if (isLong(clazz)) {
            conversionProcessor = of(new LongConversionProcessor());
        } else if (isFloat(clazz)) {
            conversionProcessor = of(new FloatConversionProcessor());
        } else if (isDouble(clazz)) {
            conversionProcessor = of(new DoubleConversionProcessor());
        } else if (isChar(clazz)) {
            conversionProcessor = of(new CharacterConversionProcessor());
        } else if (isString(clazz)) {
            conversionProcessor = of(new StringConversionProcessor());
        } else if (isBoolean(clazz)) {
            conversionProcessor = of(new BooleanConversionProcessor());
        } else if (isBigInteger(clazz)) {
            conversionProcessor = of(new BigIntegerConversionProcessor());
        } else if (isBigDecimal(clazz)) {
            conversionProcessor = of(new BigDecimalConversionProcessor());
        } else if (isByteArray(clazz)) {
            conversionProcessor = of(new ByteArrayConversionProcessor());
        }else if (isLocalDateTime(clazz)){
            conversionProcessor = of(new LocalDateTimeConversionProcessor());
        }else if (isLocalDate(clazz)){
            conversionProcessor = of(new LocalDateConversionProcessor());
        }
        return conversionProcessor;
    }

}
