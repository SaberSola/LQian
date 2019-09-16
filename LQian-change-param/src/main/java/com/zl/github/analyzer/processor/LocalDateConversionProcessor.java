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
public class LocalDateConversionProcessor implements ConversionProcessor<LocalDate> {

    @Override
    public Function<Byte, LocalDate> convertByte() {
        return null;
    }

    @Override
    public Function<byte[], LocalDate> convertByteArray() {
        return null;
    }

    @Override
    public Function<Short, LocalDate> convertShort() {
        return null;
    }

    @Override
    public Function<Integer, LocalDate> convertInteger() {
        return null;
    }

    @Override
    public Function<Long, LocalDate> convertLong() {
        return null;
    }

    @Override
    public Function<Float, LocalDate> convertFloat() {
        return null;
    }

    @Override
    public Function<Double, LocalDate> convertDouble() {
        return null;
    }

    @Override
    public Function<Character, LocalDate> convertCharacter() {
        return null;
    }

    @Override
    public Function<Boolean, LocalDate> convertBoolean() {
        return null;
    }

    @Override
    public Function<String, LocalDate> convertString() {
        return null;
    }

    @Override
    public Function<BigInteger, LocalDate> convertBigInteger() {
        return null;
    }

    @Override
    public Function<BigDecimal, LocalDate> convertBigDecimal() {
        return null;
    }

    @Override
    public Function<LocalDateTime, LocalDate> convertLocalDateTime() {
        return null;
    }

    @Override
    public Function<LocalDate, LocalDate> convertLocalDate() {
        return null;
    }
}
