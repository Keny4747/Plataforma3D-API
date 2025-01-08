package com.plataforma3d.plataforma3D2024.exceptions;

import java.io.Serial;

public class ModeloNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public ModeloNotFoundException(String message) {
        super(message);
    }
}
