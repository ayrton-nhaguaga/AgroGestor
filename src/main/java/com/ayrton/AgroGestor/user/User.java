package com.ayrton.AgroGestor.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id

    private String id;

    private String login;

    private String password;

    private UserRole role;

    public User(String login, String password, UserRole role){
        this.login = login;
        this.password = password;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ADMIN tem todas as permissoes (incliondo as de usuario, engenheiro, manager)
        if (this.role == UserRole.ADMIN) return  List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_MANAGER"));

        // GESTOR tem as permissoes de ENGENHEIRO

        if (this.role == UserRole.MANAGER) return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"), new SimpleGrantedAuthority("ROLE_ENGINEER"), new SimpleGrantedAuthority("ROLE_USER"));

        // ENGENHEIRO tem as permissoes de Usuario
        if (this.role == UserRole.ENGINEER) return List.of(new SimpleGrantedAuthority("ROLE_ENGINEER"), new SimpleGrantedAuthority("ROLE_USER"));

            // permissoes de usuario
        else return  List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
