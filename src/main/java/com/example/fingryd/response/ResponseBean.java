package com.example.fingryd.response;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ResponseBean {
    @Bean
    public AuthenticationResponse getAuthResponse(){
        return new AuthenticationResponse();
    }
    @Bean
    public DefaultResponse getDefaultResponse(){
        return new DefaultResponse();
    }
}
