package com.example.fingryd.service.managers;

import com.example.fingryd.requests.AuthenticationRequest;
import com.example.fingryd.response.AuthenticationResponse;

public interface AuthManager {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
