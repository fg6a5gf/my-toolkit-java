package org.fg6a5gf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fg6a5gf.jackson.JacksonJsonUtilsConfig;
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
    @ConditionalOnMissingBean(JacksonJsonUtilsConfig.class)
    public JacksonJsonUtilsConfig configJackson(@Autowired ObjectMapper objectMapper) {
        JacksonJsonUtilsConfig configJackson4Utils = new JacksonJsonUtilsConfig();
        configJackson4Utils.config(objectMapper);
        return configJackson4Utils;
    }
}