package com.example.fingryd.service.security;


import com.example.fingryd.exception.CustomerException;
import com.example.fingryd.model.Employee;
import com.example.fingryd.repository.EmployeeRepository;
import com.example.fingryd.requests.AuthenticationRequest;
import com.example.fingryd.requests.RegistrationRequest;
import com.example.fingryd.response.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final EmployeeRepository userRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegistrationRequest request) throws Exception{

        var user = Employee.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .address(request.getAddress())
                .mobile(request.getMobile())
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try{ authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( request.getUserName(),
                request.getPassword() ));
        } catch (Exception e){}

        var user = userRepository.findByUserName(request.getUserName()).orElseThrow(()-> new CustomerException(
                "Employee with the Username/Password does not exist on fingryd..."));
        var token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
