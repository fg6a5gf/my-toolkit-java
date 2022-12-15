package org.fg6a5gf.core;

import java.util.HashMap;

/**
 * FluentMap
 *
 * @author zhengxx
 * @since 2022/12/07
 */
public class FluentMap<K, V> extends HashMap<K, V> {

    public FluentMap<K, V> fluentPut(K key, V value) {
        this.put(key, value);
        return this;
    }
}
