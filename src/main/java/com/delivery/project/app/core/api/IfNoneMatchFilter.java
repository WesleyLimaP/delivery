package com.delivery.project.app.core.api;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
public class IfNoneMatchFilter {

    @Bean
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
}
