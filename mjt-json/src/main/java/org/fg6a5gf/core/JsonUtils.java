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

    private static JsonEncoder encoder;

    private static JsonDecoder decoder;

    public static String encode(Object obj) {
        return encoder.encode(obj);
    }

    public static <T> T decode(String str, Class<T> tClass) {
        return decoder.decode(str, tClass);
    }

    public static <T> Collection<T> decode(String str, Class<T> tclass, Supplier<Collection<T>> collectionFactory) {
        if (str == null || str.length() == 0) {
            return List.of();
        }
        return decoder.decode(str, tclass, collectionFactory);
    }

    public static <T> List<T> decodeList(String str, Class<T> tClass) {
        return (List<T>) decode(str, tClass, (Supplier<Collection<T>>) ArrayList::new);
    }

    public static class Config {

        private Config() {
        }

        /**
         * 清空已经配置好的内容
         */
        public static void reset() {
            JsonUtils.encoder = null;
            JsonUtils.decoder = null;
        }

        public static boolean configEncoder(JsonEncoder jsonEncoder) {
            if (JsonUtils.encoder == null) {
                JsonUtils.encoder = jsonEncoder;
                return true;
            }
            return false;
        }

        public static boolean configDecoder(JsonDecoder jsonDecoder) {
            if (JsonUtils.decoder == null) {
                JsonUtils.decoder = jsonDecoder;
                return true;
            }
            return false;
        }
    }
}
