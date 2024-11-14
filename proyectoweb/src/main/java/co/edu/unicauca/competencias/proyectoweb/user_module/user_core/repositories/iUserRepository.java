package co.edu.unicauca.competencias.proyectoweb.user_module.user_core.repositories;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iUserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
