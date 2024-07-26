package ru.innotech.payments.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "integrations.executors")
public class ExecutorsProperties {

    private final RestTemplateProperties productsExecutorClient;

    @ConstructorBinding
    public ExecutorsProperties(RestTemplateProperties productsExecutorClient) {
        this.productsExecutorClient = productsExecutorClient;
    }

}
