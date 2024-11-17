package co.edu.unicauca.competencias.proyectoweb.user_module.user_core.repositories;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iUserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.* FROM usuarios u WHERE u.email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
}
