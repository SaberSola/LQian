package com.zl.github.translate;

import com.zl.github.Transformer;
import com.zl.github.model.FieldMapping;
import com.zl.github.model.FieldTransformer;

/**
 * @Author zl
 * @Date 2019-09-12
 * @Des ${todo}
 */
public interface ObjectTransformer extends Transformer {

    ObjectTransformer withFieldMapping(FieldMapping... fieldMapping);

    void removeFieldMapping(String destFieldName);

    void resetFieldsMapping();

    ObjectTransformer withFieldTransformer(FieldTransformer... fieldTransformer);

    void removeFieldTransformer(String destFieldName);

    void resetFieldsTransformer();

    ObjectTransformer setDefaultValueForMissingField(boolean useDefaultValue);

    ObjectTransformer setFlatFieldNameTransformation(boolean useFlatTransformation);

    ObjectTransformer setValidationEnabled(boolean validationEnabled);

    ObjectTransformer skipTransformationForField(String... fieldName);

    void resetFieldsTransformationSkip();

    ObjectTransformer setDefaultValueSetEnabled(boolean defaultValueSetEnabled);

    ObjectTransformer setPrimitiveTypeConversionEnabled(boolean primitiveTypeConversionEnabled);
}
