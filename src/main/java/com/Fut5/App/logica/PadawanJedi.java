package com.Fut5.App.logica;

import com.Fut5.App.dominio.Equipo;
import com.Fut5.App.dominio.Jugador;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.Fut5.App.Fut5App.equipos;

public class PadawanJedi {

    public static void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorNombre(Scanner scanner, List<Equipo> equipos) {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        boolean encontrado = false;
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Nombre del entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Lista de jugadores ordenados por nombre: ");

                List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                jugadoresOrdenados.sort(Comparator.comparing(Jugador::getNombre));

                for (Jugador jugador : jugadoresOrdenados) {
                    System.out.println("- " + jugador.getNombreCompleto());
                }

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Equipo no encontrado.");
        }
    }

    public static void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorCamiseta(Scanner scanner, List<Equipo> equipos) {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        boolean encontrado = false;
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Nombre del entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Lista de jugadores ordenados por número de camiseta: ");

                List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                jugadoresOrdenados.sort(Comparator.comparingInt(Jugador::getNumeroCamiseta));

                for (Jugador jugador : jugadoresOrdenados) {
                    System.out.println("- " + jugador.getNombreCompleto());
                }

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Equipo no encontrado.");
        }
    }

    public static void buscarEquipoPorNombreMostrarJugadoresOrdenadosPorPosicionYCamiseta(Scanner scanner, List<Equipo> equipos) {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        boolean encontrado = false;
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Nombre del entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Lista de jugadores ordenados por posición y número de camiseta: ");

                List<Jugador> jugadoresOrdenados = new ArrayList<>(equipo.getJugadores());
                jugadoresOrdenados.sort(Comparator.comparing(Jugador::getPosicion)
                        .thenComparingInt(Jugador::getNumeroCamiseta));

                for (Jugador jugador : jugadoresOrdenados) {
                    System.out.println("- " + jugador.getNombreCompleto());
                }

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Equipo no encontrado.");
        }
    }

    public static void nivelMaestroExportarDatosEquipos(Scanner scanner, List<Equipo> equipos){
        System.out.print("Ingrese el nombre del archivo de salida (sin extensión): ");
        String nombreArchivo = scanner.nextLine();

        String nombreArchivoTxt = nombreArchivo + ".txt";
        String nombreArchivoCsv = nombreArchivo + ".csv";

        try {
            FileWriter writerTxt = new FileWriter(nombreArchivoTxt);
            writerTxt.write("Datos de los equipos (ordenados por nombre):\n");
            writerTxt.write("--------------------------------------------------\n");
            for (Equipo equipo : equipos) {
                writerTxt.write("Nombre: " + equipo.getNombre() + "\n");
                writerTxt.write("Entrenador: " + equipo.getEntrenador() + "\n");
                writerTxt.write("Jugadores (ordenados por nombre):\n");
                List<Jugador> jugadoresOrdenadosPorNombre = equipo.getJugadores()
                        .stream()
                        .sorted(Comparator.comparing(Jugador::getNombreCompleto))
                        .collect(Collectors.toList());
                for (Jugador jugador : jugadoresOrdenadosPorNombre) {
                    writerTxt.write("- " + jugador.getNombreCompleto() + "\n");
                }
                writerTxt.write("--------------------------------------------------\n");
            }
            writerTxt.close();

            FileWriter writerCsv = new FileWriter(nombreArchivoCsv);
            writerCsv.write("Nombre,Entrenador,Jugadores\n");
            for (Equipo equipo : equipos) {
                writerCsv.write(equipo.getNombre() + ",");
                writerCsv.write(equipo.getEntrenador() + ",");
                List<Jugador> jugadoresOrdenadosPorCamiseta = equipo.getJugadores()
                        .stream()
                        .sorted(Comparator.comparingInt(Jugador::getNumeroCamiseta))
                        .collect(Collectors.toList());
                for (Jugador jugador : jugadoresOrdenadosPorCamiseta) {
                    writerCsv.write(jugador.getNombreCompleto() + " (" + jugador.getNumeroCamiseta() + "),");
                }
                writerCsv.write("\n");
            }
            writerCsv.close();

            System.out.println("Los datos de los equipos se han exportado exitosamente en los archivos: " + nombreArchivoTxt + ", " + nombreArchivoCsv);
        } catch (IOException e) {
            System.out.println("Se produjo un error al exportar los datos de los equipos: " + e.getMessage());
        }

    }


}