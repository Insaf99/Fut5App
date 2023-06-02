package com.Fut5.App.dominio;

import lombok.Data;

@Data
public class Jugador {

    private int identificador;
    private String nombre;
    private String apellido;
    private double altura;
    private String posicion;
    private int cantidadGoles;
    private int cantidadPartidos;
    private boolean esCapitan;
    private int numeroCamiseta;

    private Equipo equipo;

    public Jugador(String nombreJugador, String apellidoJugador, double alturaJugador, String posicionJugador,
                   int golesJugador, int partidosJugador, boolean esCapitan, int numeroCamiseta) {
    }

    public String getNombreCompleto() {
        return nombre + " " +apellido;
    }
}
