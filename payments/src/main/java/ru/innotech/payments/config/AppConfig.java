package ru.innotech.payments.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.innotech.payments.config.properties.ExecutorsProperties;
import ru.innotech.payments.config.properties.RestTemplateProperties;

@Configuration
@EnableConfigurationProperties({ExecutorsProperties.class})
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(
            ExecutorsProperties executorsProperties
    ) {
        RestTemplateProperties executorClient = executorsProperties.getProductsExecutorClient();
        return new RestTemplateBuilder()
                .rootUri(executorClient.getUrl())
                .setConnectTimeout(executorClient.getConnectTimeout())
                .setReadTimeout(executorClient.getReadTimeout())
                .build();
    }
}
