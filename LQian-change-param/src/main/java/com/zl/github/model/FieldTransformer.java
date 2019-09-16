package com.zl.github.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@AllArgsConstructor(access = PRIVATE)
@Getter
@ToString
public class FieldTransformer<T,K> {

    private final String destFieldName;

    private final Function<T, K> transformerFunction;

    private final Supplier<K> transformerSupplier;

    public FieldTransformer(final String destinationFieldName, final Function<T, K> fieldTransformerFunction) {
        this(destinationFieldName, fieldTransformerFunction, null);
    }

    public FieldTransformer(final String destinationFieldName, final Supplier<K> fieldTransformerSupplier) {
        this(destinationFieldName, null, fieldTransformerSupplier);
    }

    public final K getTransformedObject(final T objectToTransform) {
        return nonNull(transformerFunction) ? transformerFunction.apply(objectToTransform) : transformerSupplier.get();
    }
}
