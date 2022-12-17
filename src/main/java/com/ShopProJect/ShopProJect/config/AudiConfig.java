package com.ShopProJect.ShopProJect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing//JPA에 Auditing기능을 활성화시킴
public class AudiConfig {
    @Bean
    public AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpi();
    }
}
