package org.example.qikservetest.qikserve.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WiremockConfig {

    @Bean
    public WiremockClient wiremockClient() {
        return new WiremockClient();
    }
}
