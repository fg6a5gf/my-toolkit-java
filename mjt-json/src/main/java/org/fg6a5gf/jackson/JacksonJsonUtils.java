package org.fg6a5gf.jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fg6a5gf.core.JsonReader;
import org.fg6a5gf.core.JsonWriter;
import org.fg6a5gf.exception.JsonReadException;
import org.fg6a5gf.exception.JsonWriteException;

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
            throw new JsonReadException(e);
        }
    }

    @Override
    public <T, C extends Collection<T>> C read(String str, Class<T> tclass, Supplier<C> collectionFactory) {
        try {
            return objectMapper.readValue(
                    str,
                    objectMapper.getTypeFactory().constructCollectionType(collectionFactory.get().getClass(), tclass)
            );
        } catch (JsonProcessingException e) {
            throw new JsonReadException(e);
        }
    }

    @Override
    public String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonWriteException(e);
        }
    }
}
