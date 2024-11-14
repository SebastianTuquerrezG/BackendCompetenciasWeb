package co.edu.unicauca.competencias.proyectoweb.user_module.user_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities.User;

import java.lang.reflect.Type;
import java.util.Optional;

public interface iUserService {
    public Iterable<User> getAllUsers();
    public Optional<User> getById(int id);
    public Optional<User> getByEmail(String email);
    public Optional<User> createUser(User user);
    public Optional<User> updateUser(int id, User user);
    public boolean deleteUser(int id);
    public Optional<Type> verifyCredentials(String username, String password);
}
