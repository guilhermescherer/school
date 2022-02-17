package com.personal.school.config.initializr;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperInitializr {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
