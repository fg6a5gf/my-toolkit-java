package org.fg6a5gf.jackson;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.fg6a5gf.core.JsonUtils;

/**
 * JacksonJsonUtilsConfig
 *
 * @author zhengxx
 * @since 2022/12/07
 */
public class JacksonJsonUtilsConfig {

    public void config(ObjectMapper objectMapper) {
        JacksonJsonUtils mapperUtils = new JacksonJsonUtils(objectMapper);
        if (!JsonUtils.Config.configEncoder(mapperUtils)) {
            throw new ObjectMapperConfigException("encoder已经配置过，不能重新配置");
        }
        if (!JsonUtils.Config.configDecoder(mapperUtils)) {
            throw new ObjectMapperConfigException("decoder已经配置过，不能重新配置");
        }
    }
}
