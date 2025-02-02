package org.pgrabarek.jakartawebproject.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringExports {
    @Bean
    public SpringExampleBean springExampleBean() {
        return new SpringExampleBean("Hello World");
    }
}
