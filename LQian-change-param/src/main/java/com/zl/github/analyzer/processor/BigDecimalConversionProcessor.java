package com.zl.github.analyzer.processor;

import com.zl.github.exception.TypeConversionException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.BufferUnderflowException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

import static java.lang.Character.getNumericValue;
import static java.math.BigDecimal.valueOf;
import static java.nio.ByteBuffer.wrap;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public class BigDecimalConversionProcessor implements ConversionProcessor<BigDecimal>{


    @Override
    public Function<Byte, BigDecimal> convertByte() {
        return val -> valueOf(val.doubleValue());
    }

    @Override
    public Function<byte[], BigDecimal> convertByteArray() {
        return val -> {
            try {
                return valueOf(wrap(val).getDouble());
            } catch (BufferUnderflowException e) {
                throw new TypeConversionException("Not enough byte to represents a BigDecimal. At least 8 bytes are required.");
            }
        };
    }

    @Override
    public Function<Short, BigDecimal> convertShort() {
        return BigDecimal::valueOf;
    }

    @Override
    public Function<Integer, BigDecimal> convertInteger() {
        return BigDecimal::valueOf;
    }

    @Override
    public Function<Long, BigDecimal> convertLong() {
        return BigDecimal::valueOf;
    }

    @Override
    public Function<Float, BigDecimal> convertFloat() {
        return BigDecimal::valueOf;
    }

    @Override
    public Function<Double, BigDecimal> convertDouble() {
        return BigDecimal::valueOf;
    }

    @Override
    public Function<Character, BigDecimal> convertCharacter() {
        return val -> valueOf(getNumericValue(val));
    }

    @Override
    public Function<Boolean, BigDecimal> convertBoolean() {
        return val -> valueOf(val ? 1 : 0);
    }

    @Override
    public Function<String, BigDecimal> convertString() {
        return BigDecimal::new;
    }

    @Override
    public Function<BigInteger, BigDecimal> convertBigInteger() {
        return val -> valueOf(val.intValue());
    }

    @Override
    public Function<BigDecimal, BigDecimal> convertBigDecimal() {
        return val->val;
    }


    /**
     * this case not in it
     * @return
     */
    @Override
    public Function<LocalDateTime, BigDecimal> convertLocalDateTime() {
        return null;
    }

    /**
     * this case not in it
     * @return
     */
    public Function<LocalDate, BigDecimal> convertLocalDate() {
        return null;
    }
}
