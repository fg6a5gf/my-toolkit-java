package org.fg6a5gf.core;

import org.fg6a5gf.exception.JsonProxyUndefinedException;
import org.fg6a5gf.exception.JsonReadException;
import org.fg6a5gf.exception.JsonWriteException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * 封装了序列化和反序列化两种操作。
 * <p>
 * {@link JsonUtils#writer}提供了{@link JsonUtils#toJson(Object)}方法用于序列化。
 * <p>
 * {@link  JsonUtils#reader}提供了包括{@link JsonUtils#read(String, Class)}等方法用于反序列化
 *
 * @author zhengxx
 * @since 2022/12/07
 */
public class JsonUtils {

    private JsonUtils() {
    }

    private static JsonWriter writer;

    private static JsonReader reader;

    /**
     * @param obj 要序列化的对象
     * @return JSON string
     * @throws JsonProxyUndefinedException 没有配置writer
     * @throws JsonWriteException          序列化出错
     */
    public static String toJson(Object obj) {
        if (writer == null) {
            throw new JsonProxyUndefinedException("writer未定义");
        }
        return writer.toJson(obj);
    }

    /**
     * @param str    json字符串
     * @param tClass Java对象Type
     * @param <T>    Java对象Type
     * @return 反序列化的对象
     * @throws JsonReadException           反序列化中出错
     * @throws JsonProxyUndefinedException 没有配置reader
     */
    public static <T> T read(String str, Class<T> tClass) {
        if (reader == null) {
            throw new JsonProxyUndefinedException("reader未定义");
        }
        return reader.read(str, tClass);
    }

    /**
     * @param str               json
     * @param tclass            JavaType
     * @param collectionFactory 结果的类型
     * @param <T>               JavaType
     * @return 结果集
     * @see JsonUtils#read(String, Class)
     */
    @SuppressWarnings("unchecked")
    public static <T, C extends Collection<T>> C read(String str, Class<T> tclass, Supplier<C> collectionFactory) {
        if (str == null || str.length() == 0) {
            return (C) List.of();
        }
        return reader.read(str, tclass, collectionFactory);
    }

    /**
     * @see JsonUtils#read(String, Class, Supplier)
     */
    public static <T> List<T> readAsList(String str, Class<T> tClass) {
        return read(str, tClass, ArrayList::new);
    }

    public static class Config {

        private Config() {
        }

        /**
         * 清空已经配置好的内容
         */
        public static void reset() {
            JsonUtils.writer = null;
            JsonUtils.reader = null;
        }

        public static boolean configEncoder(JsonWriter jsonEncoder) {
            if (JsonUtils.writer == null) {
                JsonUtils.writer = jsonEncoder;
                return true;
            }
            return false;
        }

        public static boolean configDecoder(JsonReader jsonDecoder) {
            if (JsonUtils.reader == null) {
                JsonUtils.reader = jsonDecoder;
                return true;
            }
            return false;
        }
    }
}
