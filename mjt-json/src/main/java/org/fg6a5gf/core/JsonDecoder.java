package org.fg6a5gf.core;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * JsonDecoder
 *
 * @author zhengxx
 * @since 2022/12/07
 */
public interface JsonDecoder {

    <T> T decode(String str, Class<T> tClass);

    <T> Collection<T> decode(String str, Class<T> tclass, Supplier<Collection<T>> collectionFactory);
}
