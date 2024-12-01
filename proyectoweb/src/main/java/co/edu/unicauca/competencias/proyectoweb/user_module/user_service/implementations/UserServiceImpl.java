package co.edu.unicauca.competencias.proyectoweb.user_module.user_service.implementations;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.complements.StatusUser;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities.User;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.repositories.iUserRepository;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_service.interfaces.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements iUserService {

    private final iUserRepository iUserRepository;

    @Autowired
    public UserServiceImpl(iUserRepository iUserRepository){
        this.iUserRepository = iUserRepository;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return iUserRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return iUserRepository.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }

    @Override
    public Optional<User> createUser(User user) {
        return Optional.of(iUserRepository.save(user));
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        if (!iUserRepository.existsById(user.getId())){
            return Optional.empty();
        }
        iUserRepository.save(user);
        return iUserRepository.findById(user.getId());
    }

    @Override
    public Optional<User> setState(User user) {
        try {
            Optional<User> usuario = iUserRepository.findById(user.getId());
            if (usuario.isEmpty()) return Optional.empty();
            StatusUser status = usuario.get().getState();
            if (status.equals(StatusUser.ACTIVO)){
                usuario.get().setState(StatusUser.INACTIVO);
            }else{
                usuario.get().setState(StatusUser.ACTIVO);
            }
            iUserRepository.save(usuario.get());
            return usuario;
        }catch (Exception e){
            throw new RuntimeException("The user doesn't exits: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> setUserAccountLock(User user) {
        user.setFailedAttempts(0);
        user.setAccountNonLocked(true);
        return updateUser(user.getId(), user);
    }
}
