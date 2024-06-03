package ru.evgenii.SecurityWithJWT.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.evgenii.SecurityWithJWT.dto.AuthenticationRequest;
import ru.evgenii.SecurityWithJWT.dto.AuthenticationResponse;
import ru.evgenii.SecurityWithJWT.dto.RegisterRequest;
import ru.evgenii.SecurityWithJWT.exception.EntityNotFoundException;
import ru.evgenii.SecurityWithJWT.exception.UsernameAlreadyExist;
import ru.evgenii.SecurityWithJWT.model.User;
import ru.evgenii.SecurityWithJWT.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExist("Пользователь с таким именем уже существует");
        }

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = repository.findByUsername(request.getUsername()).
                orElseThrow(() -> new EntityNotFoundException("Пользователь не найден."));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
