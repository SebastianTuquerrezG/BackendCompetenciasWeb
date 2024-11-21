package co.edu.unicauca.competencias.proyectoweb.user_module.user_service.implementations;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_configuration.JwtService;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities.User;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.AuthRequestDTO;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.AuthResponseDTO;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.RegisterRequestDTO;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_service.interfaces.iAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements iAuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private final UserServiceImpl userService;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;

    @Value("${attempts}")
    private int attemptsSecurity;

    public AuthenticationServiceImpl(UserServiceImpl userService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        var user = userService.getByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.println("Valor de la cuenta: " + user.isAccountNonLocked());
        if (!user.isAccountNonLocked()){
            throw new IllegalStateException("Account is locked due to too many failed login attempts.");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            resetFailedAttempts(user);

            var jwtToken = jwtService.generateToken(user);

            return AuthResponseDTO.builder()
                    .token(jwtToken)
                    .expiresIn(jwtService.getExpirationTime())
                    .build();
        }catch (Exception e){
            increaseFailedAttempts(user);
            logger.error("Incorrect email or password: {}", e.getMessage());
            return null;
        }
    }

    // Maybe this register we wouldn't need it
    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        User user = new User();
        user.setIdentity(request.getIdentity());
        user.setTypeId(request.getTypeId());
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setDegree(request.getDegree());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setState(request.getStatusUser());
        user.setRol(request.getRole());
        user.setCreatedAt(request.getCreatedAt());
        user.setUpdatedAt(request.getUpdatedAt());
        userService.createUser(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();
    }

    private void increaseFailedAttempts(User user){
        int attempts = user.getFailedAttempts() + 1;
        user.setFailedAttempts(attempts);
        if (attempts >= attemptsSecurity){
            user.setAccountNonLocked(false);
        }
        userService.updateUser(user.getId(), user);
    }

    private void resetFailedAttempts(User user) {
        user.setFailedAttempts(0);
        userService.updateUser(user.getId(), user);
    }
}
