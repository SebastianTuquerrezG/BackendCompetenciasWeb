package co.edu.unicauca.competencias.proyectoweb.user_module.user_service.implementations;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities.User;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.repositories.iUserRepository;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_service.interfaces.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Optional;

@Service
public class UserServiceImpl implements iUserService {

    @Autowired
    private final iUserRepository iUserRepository;

    public UserServiceImpl(iUserRepository iUserRepository){
        this.iUserRepository = iUserRepository;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> getById(int id) {
        return iUserRepository.findById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }

    @Override
    public Optional<User> createUser(User user) {
        return Optional.of(iUserRepository.save(user));
    }

    @Override
    public Optional<User> updateUser(int id, User user) {
        if (!iUserRepository.existsById(user.getId())){
            return Optional.empty();
        }
        iUserRepository.save(user);
        return iUserRepository.findById(user.getId());
    }

    @Override
    public boolean deleteUser(int id) {
        if(!iUserRepository.existsById(id)){
            return false;
        }
        iUserRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Type> verifyCredentials(String username, String password) {
        return Optional.empty();
    }
}
