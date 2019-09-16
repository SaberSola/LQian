package com.zl.github.translate;

import com.zl.github.analyzer.ConversionAnalyzer;
import com.zl.github.cache.CacheManager;
import com.zl.github.model.FieldMapping;
import com.zl.github.model.FieldTransformer;
import com.zl.github.utils.ClassUtils;
import com.zl.github.utils.ReflectionUtils;

import java.util.Map;

import static com.zl.github.cache.CacheManagerFactory.getCacheManager;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.Validate.notNull;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
public abstract class AbsTransformer implements ObjectTransformer {

    /**
     * 缓存前缀
     */
    static final String TRANSFORMER_FUNCTION_CACHE_PREFIX = "TransformerFunction";

    private static final String TRANSFORMER_FUNCTION_REGEX = "^" + TRANSFORMER_FUNCTION_CACHE_PREFIX + ".*";

    final ReflectionUtils reflectionUtils;

    final ClassUtils classUtils;

    final CacheManager cacheManager;

    final TransformerSettings settings;

    ConversionAnalyzer conversionAnalyzer;

    AbsTransformer() {
        this.reflectionUtils = new ReflectionUtils();
        this.classUtils = new ClassUtils();
        this.settings = new TransformerSettings();
        this.cacheManager = getCacheManager("transformer");
    }

    @Override
    public ObjectTransformer withFieldMapping(FieldMapping... fieldMapping) {
        final Map<String, String> fieldsNameMapping = settings.getFieldsNameMapping();
        for (FieldMapping mapping : fieldMapping){
            fieldsNameMapping.put(mapping.getDestFieldName(), mapping.getSourceFieldName());
        }
        return this;
    }

    @Override
    public void removeFieldMapping(String destFieldName) {
        notNull(destFieldName, "The field name for which the mapping has to be removed cannot be null!");
        settings.getFieldsNameMapping().remove(destFieldName);
    }

    @Override
    public void resetFieldsMapping() {
        settings.getFieldsNameMapping().clear();
    }

    @Override
    public ObjectTransformer withFieldTransformer(FieldTransformer... fieldTransformer) {
        Map<String, FieldTransformer> fieldsTransformers = settings.getFieldsTransformers();
        for (FieldTransformer transformer : fieldTransformer) {
            fieldsTransformers.put(transformer.getDestFieldName(), transformer);
        }
        return this;
    }

    @Override
    public void removeFieldTransformer(String destFieldName) {
        notNull(destFieldName, "The field name for which the transformer function has to be removed cannot be null!");
        settings.getFieldsTransformers().remove(destFieldName);
        cacheManager.removeMatchingKeys(TRANSFORMER_FUNCTION_REGEX + destFieldName);
    }

    @Override
    public void resetFieldsTransformer() {
        settings.getFieldsTransformers().clear();
        cacheManager.removeMatchingKeys(TRANSFORMER_FUNCTION_REGEX);
    }

    @Override
    public ObjectTransformer setDefaultValueForMissingField(boolean useDefaultValue) {
        settings.setSetDefaultValueForMissingField(useDefaultValue);
        return this;
    }

    @Override
    public ObjectTransformer setFlatFieldNameTransformation(boolean useFlatTransformation) {
        settings.setFlatFieldNameTransformation(useFlatTransformation);
        return this;
    }

    @Override
    public ObjectTransformer setValidationEnabled(boolean validationEnabled) {
        return null;
    }

    @Override
    public ObjectTransformer skipTransformationForField(String... fieldName) {
        if (fieldName.length != 0) {
            settings.getFieldsToSkip().addAll(asList(fieldName));
        }
        return this;
    }

    @Override
    public void resetFieldsTransformationSkip() {
        settings.getFieldsToSkip().clear();
    }

    @Override
    public ObjectTransformer setDefaultValueSetEnabled(boolean defaultValueSetEnabled) {
        settings.setDefaultValueSetEnabled(defaultValueSetEnabled);
        return this;
    }

    @Override
    public ObjectTransformer setPrimitiveTypeConversionEnabled(boolean primitiveTypeConversionEnabled) {
        settings.setPrimitiveTypeConversionEnabled(primitiveTypeConversionEnabled);
        if (primitiveTypeConversionEnabled) {
            conversionAnalyzer = new ConversionAnalyzer();
        } else {
            cacheManager.removeMatchingKeys(TRANSFORMER_FUNCTION_REGEX);
        }
        return this;
    }

    @Override
    public <T, K> K transform(T sourceObj, Class<? extends K> targetClass) {
        notNull(sourceObj, "The object to copy cannot be null!");
        notNull(targetClass, "The destination class cannot be null!");
        return transform(sourceObj, targetClass, null);
    }

    @Override
    public <T, K> void transform(T sourceObj, K targetObject) {
        notNull(sourceObj, "The object to copy cannot be null!");
        notNull(targetObject, "The destination object cannot be null!");
        transform(sourceObj, targetObject, null);
    }

    /**
     *
     * @param sourceObj
     * @param targetClass
     * @param breadcrumb 父类的全路径
     * @param <T>
     * @param <K>
     * @return
     */
    protected abstract <T, K> K transform(T sourceObj, Class<? extends K> targetClass, String breadcrumb);

    protected abstract <T, K> void transform(T sourceObj, K targetObject, String breadcrumb);
}
