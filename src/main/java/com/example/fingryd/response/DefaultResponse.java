package com.example.fingryd.response;

import lombok.Builder;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Builder
public class DefaultResponse {

    public static Map<String, String> successResponse(String msg){

        Map<String, String> response = new HashMap<>();
        response.put("Message", "You have success fully been onboarded as a fingryd staff");

        return response;
    }

    public static Map<String, String> failureResponse(String msg){

        Map<String, String> response = new HashMap<>();
        response.put("Message", "Your onboarding process was unsuccessful");

        return response;
    }
}
