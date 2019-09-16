package com.zl.github.translate;

import com.zl.github.model.FieldTransformer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@Getter
public class TransformerSettings {


    /**
     * Contains the mapping between fields's name in the source object and the destination one.
     */
    private final Map<String, String> fieldsNameMapping = new ConcurrentHashMap<>();

    /**
     * Contains the lambda functions to be applied on a given fields.
     */
    private final Map<String, FieldTransformer> fieldsTransformers = new ConcurrentHashMap<>();

    private final Set<String> fieldsToSkip = new HashSet<>();

    @Setter
    private boolean setDefaultValueForMissingField;

    @Setter
    private boolean flatFieldNameTransformation;

    @Setter
    private boolean validationEnabled;

    @Setter
    private boolean defaultValueSetEnabled = true;

    @Setter
    private boolean primitiveTypeConversionEnabled;
}
