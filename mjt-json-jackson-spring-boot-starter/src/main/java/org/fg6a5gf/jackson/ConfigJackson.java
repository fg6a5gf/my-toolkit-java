package org.fg6a5gf.jackson;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.fg6a5gf.core.JsonUtils;

/**
 * ConfigJackson
 *
 * @author zhengxx
 * @since 2022/12/07
 */
public class ConfigJackson {

    public void config(ObjectMapper objectMapper) {
        ObjectMapperUtils mapperUtils = new ObjectMapperUtils(objectMapper);
        if (!JsonUtils.Config.configEncoder(mapperUtils)) {
            throw new ObjectMapperConfigException("encoder已经配置过，不能重新配置");
        }
        if (!JsonUtils.Config.configDecoder(mapperUtils)) {
            throw new ObjectMapperConfigException("decoder已经配置过，不能重新配置");
        }
    }
}
