package com.plataforma3d.plataforma3D2024.configuration.security;

import com.plataforma3d.plataforma3D2024.model.Estudiante;
import com.plataforma3d.plataforma3D2024.repository.EstudianteRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService{



    private final EstudianteRepo estudianteRepo;

    public UserDetailServiceImpl(EstudianteRepo estudianteRepo) {
        this.estudianteRepo = estudianteRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Estudiante> estudiante = estudianteRepo.findByUsername(username);

        return estudiante.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario "+ username + " no encontrado"));
    }
}

