package com.zl.github.cache;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PROTECTED;

/**
 * @Author zl
 * @Date 2019-09-13
 * @Des ${todo}
 */
@AllArgsConstructor(access = PROTECTED)
public final class CacheManager {

    private final Map<String, Object> cacheMap;

    public <T> void cacheObject(final String cacheKey, final T object) {
        cacheObject(cacheKey, object, null);
    }

    public <T> void cacheObject(final String cacheKey, final T object, final Object defaultValue) {
        if (nonNull(object)) {
            cacheMap.put(cacheKey, object);
        } else if (nonNull(defaultValue)) {
            cacheMap.put(cacheKey, defaultValue);
        }
    }

    public <T> Optional<T> getFromCache(final String cacheKey, final Class<? extends T> objectClass) {
        return ofNullable(cacheMap.get(cacheKey)).map(objectClass::cast);
    }

    public void removeFromCache(final String cacheKey) {
        ofNullable(cacheKey).ifPresent(cacheMap::remove);
    }


    public void removeMatchingKeys(final String regex) {
        cacheMap.keySet().removeIf(key -> key.matches(regex));
    }
}
