package org.fg6a5gf.core;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * JsonReader
 *
 * @author zhengxx
 * @since 2022/12/07
 */
public interface JsonReader {

    <T> T read(String str, Class<T> tClass);

    <T, C extends Collection<T>> C read(String str, Class<T> tclass, Supplier<C> collectionFactory);
}
