package com.Fut5.App.dominio;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class Equipo {
    
    private String nombre;
    private LocalDateTime fechaCreacion;
    private List<Jugador> jugadores;
    private Entrenador entrenador;

    public Equipo(String nombre, LocalDateTime fechaCreacion, Entrenador entrenador) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.entrenador = entrenador;
        this.jugadores = new ArrayList<>();
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        jugadores.remove(jugador);
    }

    public Jugador getCapitan() {
        for(Jugador jugador : jugadores){
            if(jugador.isEsCapitan()){
                return jugador;
            }
        }
        return null;
    }
}
