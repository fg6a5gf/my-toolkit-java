package org.fg6a5gf.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author zhengxx
 * @since 2022/12/07
 */
public class JsonUtils {

    private JsonUtils() {
    }

    private static JsonWriter writer;

    private static JsonReader reader;

    public static String writeToString(Object obj) {
        return writer.writeToString(obj);
    }

    public static <T> T read(String str, Class<T> tClass) {
        return reader.read(str, tClass);
    }

    public static <T> Collection<T> read(String str, Class<T> tclass, Supplier<Collection<T>> collectionFactory) {
        if (str == null || str.length() == 0) {
            return List.of();
        }
        return reader.read(str, tclass, collectionFactory);
    }

    public static <T> List<T> readAsList(String str, Class<T> tClass) {
        return (List<T>) read(str, tClass, (Supplier<Collection<T>>) ArrayList::new);
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
