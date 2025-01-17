package com.plataforma3d.plataforma3D2024.configuration.security;

import com.plataforma3d.plataforma3D2024.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserInfoUserDetails implements UserDetails{

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UserInfoUserDetails(Usuario usuario) {
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();

        // Construir lista de authorities combinando el rol y los permisos
        this.authorities = new ArrayList<>();

        // Añadir el rol como "ROLE_<NOMBRE_DEL_ROL>"
        this.authorities.add((GrantedAuthority) () -> "ROLE_" + usuario.getRole().name());

        // Añadir los permisos asociados al rol
        this.authorities.addAll(
                usuario.getRole().getPermisos().stream()
                        .map(permiso -> (GrantedAuthority) () -> "PERMISO_" + permiso.name())
                        .toList()
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
