package com.Fut5.App.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Entrenador {
    private String nombre;
    private String apellido;
    private int edad;

    public String getNombreCompleto() {
        return nombre + " " +apellido;
    }
}
