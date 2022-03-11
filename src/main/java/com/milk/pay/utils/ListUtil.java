package com.milk.pay.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ListUtil {

    public synchronized static <T> T first(Collection<T> list) {
        if (isNotNullOrEmpty(list)) {
            return list.iterator().next();
        }

        return null;
    }

    public synchronized static boolean isNotNullOrEmpty(Collection<?> list) {
        return !isNullOrEmpty(list);
    }

    public synchronized static boolean isNotNullOrEmpty(Object[] array) {
        return !isNullOrEmpty(array);
    }

    public synchronized static boolean isNullOrEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public synchronized static boolean isNullOrEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public synchronized static <T> List<T> toList(T... values) {
        return Arrays.asList(values);
    }

    public synchronized static <T> boolean addIfNotNull(Collection<T> collection, T value) {
        if (value != null) {

            if (value instanceof String
                    && ((String) value).isEmpty()) {
                return false;
            }

            return collection.add(value);
        }
        return false;
    }

    public synchronized static <T> void addIfNotExists(Collection<T> collection, T... values) {
        List<T> toList = toList(values);
        toList.forEach(value -> {
            if (value != null && !collection.contains(value)) {
                collection.add(value);
            }
        });
    }

    public synchronized static <T> void addAllIfNotNull(Collection<T> collection, Collection<T> values) {
        if (isNotNullOrEmpty(values)) {
            for (T value : values) {
                addIfNotNull(collection, value);
            }
        }
    }

    public synchronized static <T> void addAllIfNotExists(Collection<T> collection, Collection<T> values) {
        if (isNotNullOrEmpty(values)) {
            for (T value : values) {
                addIfNotExists(collection, value);
            }
        }
    }

    public static <T> Stream<T> stream(Collection<T> lista) {
        return isNotNullOrEmpty(lista) ? Collections.unmodifiableList(new ArrayList<>(lista)).stream() : new ArrayList<T>().stream();
    }

}
