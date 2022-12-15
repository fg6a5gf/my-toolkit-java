package org.fg6a5gf.jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fg6a5gf.core.JsonReader;
import org.fg6a5gf.exception.JsonDecodeException;
import org.fg6a5gf.exception.JsonEncodeException;
import org.fg6a5gf.core.JsonWriter;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * JacksonJsonUtils
 *
 * @author zhengxx
 * @since 2022/12/07
 */
public class JacksonJsonUtils implements JsonWriter, JsonReader {

    private final ObjectMapper objectMapper;

    public JacksonJsonUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T read(String str, Class<T> tClass) {
        try {
            return objectMapper.readValue(str, tClass);
        } catch (JsonProcessingException e) {
            throw new JsonDecodeException(e);
        }
    }

    @Override
    public <T> Collection<T> read(String str, Class<T> tclass, Supplier<Collection<T>> collectionFactory) {
        try {
            return objectMapper.readValue(
                    str,
                    objectMapper.getTypeFactory().constructCollectionType(collectionFactory.get().getClass(), tclass)
            );
        } catch (JsonProcessingException e) {
            throw new JsonDecodeException(e);
        }
    }

    @Override
    public String writeToString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonEncodeException(e);
        }
    }
}
