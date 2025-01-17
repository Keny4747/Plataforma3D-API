package com.plataforma3d.plataforma3D2024.model.utilitario;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;

@Getter
public enum RoleEnum {

    ADMIN(EnumSet.of(PermisoEnum.CREATE, PermisoEnum.UPDATE, PermisoEnum.READ, PermisoEnum.DELETE)),
    USER (EnumSet.of(PermisoEnum.READ)),
    GUEST (EnumSet.of(PermisoEnum.READ)),
    ;

    private final Set<PermisoEnum> permisos;

    RoleEnum(Set<PermisoEnum> permisos) {
        this.permisos = permisos;
    }
}
