package com.example.fingryd.service;


import com.example.fingryd.config.security.JwtService;
import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.Customer;
import com.example.fingryd.repository.CustomerRepository;
import com.example.fingryd.requests.AuthenticationRequest;
import com.example.fingryd.response.AuthenticationResponse;
import com.example.fingryd.service.managers.AuthManager;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService implements AuthManager {

    private final CustomerRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try{ authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( request.getUserName(),
                request.getPassword() ));
        } catch (Exception e){}

        System.out.println(userRepository.findAll().size());

        Customer user = userRepository.findByUserName(request.getUserName()).orElseThrow(()-> new CustomerException(
                "Something went wrong. Please try again"));
        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
