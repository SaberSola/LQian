package com.zl.github.analyzer.processor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public interface ConversionProcessor<T> {


    /**
     * Converts a {@link Byte} type.
     * @return the converted value
     */
    Function<Byte, T> convertByte();

    /**
     * Converts a byte[] type.
     * @return the converted value
     * than the minimum required to create the destination type
     */
    Function<byte[], T> convertByteArray();

    /**
     * Converts a {@link Short} type.
     * @return the converted value
     */
    Function<Short, T> convertShort();

    /**
     * Converts an {@link Integer} type.
     * @return the converted value
     */
    Function<Integer, T> convertInteger();

    /**
     * Converts a {@link Long} type.
     * @return the converted value
     */
    Function<Long, T> convertLong();

    /**
     * Converts an {@link Float} type.
     * @return the converted value
     */
    Function<Float, T> convertFloat();

    /**
     * Converts a {@link Double} type.
     * @return the converted value
     */
    Function<Double, T> convertDouble();

    /**
     * Converts a {@link Character} type.
     * @return the converted value
     */
    Function<Character, T> convertCharacter();

    /**
     * Converts a {@link Boolean} type.
     * @return the converted value
     */
    Function<Boolean, T> convertBoolean();

    /**
     * Converts a {@link String} type.
     * @return the converted value
     */
    Function<String, T> convertString();

    /**
     * Converts an {@link BigInteger} type.
     * @return the converted value
     */
    Function<BigInteger, T> convertBigInteger();

    /**
     * Converts an {@link BigDecimal} type.
     * @return the converted value
     */
    Function<BigDecimal, T> convertBigDecimal();

    /**
     * Converts an {@link LocalDateTime} type.
     * @return the converted value
     */
    Function<LocalDateTime,T> convertLocalDateTime();

    /**
     * Converts an {@link LocalDate} type.
     * @return the converted value
     */
    Function<LocalDate,T> convertLocalDate();

}
