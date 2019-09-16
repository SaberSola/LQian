package com.zl.github.analyzer.processor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * @Author zl
 * @Date 2019-09-14
 * @Des ${todo}
 */
public class LocalDateTimeConversionProcessor implements ConversionProcessor<LocalDateTime> {


    @Override
    public Function<Byte, LocalDateTime> convertByte() {
        return null;
    }

    @Override
    public Function<byte[], LocalDateTime> convertByteArray() {
        return null;
    }

    @Override
    public Function<Short, LocalDateTime> convertShort() {
        return null;
    }

    @Override
    public Function<Integer, LocalDateTime> convertInteger() {
        return null;
    }

    @Override
    public Function<Long, LocalDateTime> convertLong() {
        return null;
    }

    @Override
    public Function<Float, LocalDateTime> convertFloat() {
        return null;
    }

    @Override
    public Function<Double, LocalDateTime> convertDouble() {
        return null;
    }

    @Override
    public Function<Character, LocalDateTime> convertCharacter() {
        return null;
    }

    @Override
    public Function<Boolean, LocalDateTime> convertBoolean() {
        return null;
    }

    @Override
    public Function<String, LocalDateTime> convertString() {
        return null;
    }

    @Override
    public Function<BigInteger, LocalDateTime> convertBigInteger() {
        return null;
    }

    @Override
    public Function<BigDecimal, LocalDateTime> convertBigDecimal() {
        return null;
    }

    @Override
    public Function<LocalDateTime, LocalDateTime> convertLocalDateTime() {
        return null;
    }

    @Override
    public Function<LocalDate, LocalDateTime> convertLocalDate() {
        return null;
    }
}
