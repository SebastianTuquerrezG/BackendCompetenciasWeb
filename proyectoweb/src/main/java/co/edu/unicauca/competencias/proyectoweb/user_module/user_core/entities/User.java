package co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.complements.Role;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.complements.StatusUser;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.complements.TypeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "Usuarios")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false, name = "identificacion")
    private String identity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo identificacion")
    private TypeId typeId;

    @Column(nullable = false, name = "nombre(s)")
    private String name;

    @Column(nullable = false, name = "apellido(s)")
    private String lastname;

    @Column(nullable = false, name = "titulo")
    private String degree;

    @Column(unique = true, length = 200, nullable = false, name = "email")
    private String email;

    @Column(unique = true, length = 200, nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "contrasenia")
    private String password;

    @Column(name = "telefono")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "estado")
    private StatusUser state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "rol")
    private Role rol;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "failed_attempts", columnDefinition = "integer default 0")
    private int failedAttempts=0;

    @Column(name = "account_Non_locked", columnDefinition = "boolean default true")
    private boolean accountNonLocked=true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
