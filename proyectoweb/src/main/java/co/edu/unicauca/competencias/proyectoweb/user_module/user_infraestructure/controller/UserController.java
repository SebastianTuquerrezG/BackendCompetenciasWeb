package co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.controller;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities.User;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.UserRequestDTO;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_service.implementations.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/controller")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/resetAttempts")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> unlockAccount(User user){
        return userService.setUserAccountLock(user);
    }

    @PostMapping("/getUserWithUsername")
    public ResponseEntity<Optional<User>> getUserWithUsername(@RequestBody UserRequestDTO user){
        return ResponseEntity.ok(userService.getByUsername(user.getUsername()));
    }
}
