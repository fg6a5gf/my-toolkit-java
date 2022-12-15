package org.fg6a5gf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fg6a5gf.jackson.ConfigJackson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({JacksonAutoConfiguration.class})
public class MjtJacksonStarterAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(ConfigJackson.class)
    public ConfigJackson configJackson(@Autowired ObjectMapper objectMapper) {
        ConfigJackson configJackson4Utils = new ConfigJackson();
        configJackson4Utils.config(objectMapper);
        return configJackson4Utils;
    }
}