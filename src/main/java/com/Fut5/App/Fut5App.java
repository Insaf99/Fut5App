package com.Fut5.App;

import com.Fut5.App.dominio.Entrenador;
import com.Fut5.App.dominio.Equipo;
import com.Fut5.App.dominio.Jugador;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static com.Fut5.App.logica.PadawanJedi.*;

public class Fut5App {

    private static List<Equipo> equipos;

    public Fut5App() {
        this.equipos = new ArrayList<>();
    }

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("----- MENÚ -----");
            System.out.println("1. Crear un equipo");
            System.out.println("2. Buscar jugador por nombre");
            System.out.println("3. Buscar equipo por nombre");
            System.out.println("4. Mostrar jugadores de un equipo");
            System.out.println("5. Eliminar un equipo");
            System.out.println("6. Importar lista de jugadores desde archivo");
            System.out.println("7. Exportar lista de jugadores a archivo");
            System.out.println("8. Mas opciones");
            System.out.println("9. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearEquipo(scanner);
                    break;
                case 2:
                    buscarJugadorPorNombre(scanner);
                    break;
                case 3:
                    buscarEquipoPorNombre(scanner);
                    break;
                case 4:
                    mostrarJugadoresEquipo(scanner);
                    break;
                case 5:
                    eliminarEquipo(scanner);
                    break;
                case 6:
                    importarListaJugadores(scanner);
                    break;
                case 7:
                    exportarListaJugadores(scanner);
                    break;
                case 8:
                    opcionesJediPadawan(scanner);
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    private static void crearEquipo(Scanner scanner) {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();
        System.out.print("Ingrese la fecha de creación del equipo: ");
        LocalDateTime fechaCreacion = LocalDateTime.now();

        System.out.print("Ingrese el nombre del entrenador: ");
        String nombreEntrenador = scanner.nextLine();
        System.out.print("Ingrese el apellido del entrenador: ");
        String apellidoEntrenador = scanner.nextLine();
        System.out.print("Ingrese la edad del entrenador: ");
        int edadEntrenador = scanner.nextInt();
        scanner.nextLine();

        Entrenador entrenador = new Entrenador(nombreEntrenador, apellidoEntrenador, edadEntrenador);
        Equipo equipo = new Equipo(nombreEquipo, fechaCreacion, entrenador);

        agregarJugadorAEquipo(scanner, equipo);
        asignarCapitanEquipo(scanner, equipo);

        equipos.add(equipo);
        System.out.println("Equipo creado exitosamente.");
    }

    private static void agregarJugadorAEquipo(Scanner scanner, Equipo equipo){
        boolean cargarJugador = true;
        while (cargarJugador) {
            System.out.print("Ingrese el nombre del jugador: ");
            String nombreJugador = scanner.nextLine();
            System.out.print("Ingrese el apellido del jugador: ");
            String apellidoJugador = scanner.nextLine();
            System.out.print("Ingrese la altura del jugador: ");
            double alturaJugador = scanner.nextDouble();
            scanner.nextLine(); // Consumir el salto de línea

            System.out.print("Ingrese la posición del jugador (arquero, defensor, mediocampista, delantero): ");
            String posicionJugador = scanner.nextLine();
            System.out.print("Ingrese la cantidad de goles del jugador: ");
            int golesJugador = scanner.nextInt();
            System.out.print("Ingrese la cantidad de partidos del jugador: ");
            int partidosJugador = scanner.nextInt();
            System.out.print("¿Es capitán el jugador? (S/N): ");
            String opcionCapitan = scanner.next();
            boolean esCapitan = opcionCapitan.equalsIgnoreCase("S");
            boolean numeroCamisetaValido = false;
            int numeroCamiseta = 0;
            while (!numeroCamisetaValido) {
                System.out.print("Ingrese el número de camiseta: ");
                try {
                    numeroCamiseta = Integer.parseInt(scanner.nextLine());
                    // Verificar si el número de camiseta ya está asignado en el equipo
                    int finalNumeroCamiseta = numeroCamiseta;
                    boolean numeroCamisetaRepetido = equipo.getJugadores().stream()
                            .anyMatch(jugador -> jugador.getNumeroCamiseta() == finalNumeroCamiseta);
                    if (numeroCamisetaRepetido) {
                        System.out.println("El número de camiseta ya está asignado a otro jugador en el equipo.");
                    } else {
                        numeroCamisetaValido = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("El número de camiseta ingresado no es válido. Intente nuevamente.");
                }
            }

            Jugador jugador = new Jugador(nombreJugador, apellidoJugador, alturaJugador, posicionJugador,
                    golesJugador, partidosJugador, esCapitan, numeroCamiseta);
            equipo.agregarJugador(jugador);

            System.out.print("¿Desea cargar otro jugador? (S/N): ");
            String opcion = scanner.nextLine();
            cargarJugador = opcion.equalsIgnoreCase("S");
        }
    }

    private static void asignarCapitanEquipo(Scanner scanner, Equipo equipo){
        System.out.print("Ingrese el número de camiseta del jugador capitán: ");
        int numeroCamisetaCapitan = Integer.parseInt(scanner.nextLine());

        boolean capitánAsignado = equipo.getJugadores().stream()
                .anyMatch(jugador -> jugador.isEsCapitan());
        if (capitánAsignado) {
            System.out.println("Ya hay un capitán asignado en el equipo. No se puede asignar más de un capitán.");
            return;
        }

        Jugador jugadorCapitan = equipo.getJugadores().stream()
                .filter(jugador -> jugador.getNumeroCamiseta() == numeroCamisetaCapitan)
                .findFirst().orElse(null);

        if (jugadorCapitan != null) {
            jugadorCapitan.setEsCapitan(true);
            System.out.println("Se ha asignado a " + jugadorCapitan.getNombreCompleto() + " como capitán del equipo.");
        } else {
            System.out.println("No se encontró ningún jugador con el número de camiseta ingresado.");
        }
    }

    private static void buscarJugadorPorNombre(Scanner scanner) {
        System.out.print("Ingrese el nombre del jugador: ");
        String nombreJugador = scanner.nextLine();

        boolean encontrado = false;
        for (Equipo equipo : equipos) {
            for (Jugador jugador : equipo.getJugadores()) {
                if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                    System.out.println("Nombre: " + jugador.getNombre());
                    System.out.println("Apellido: " + jugador.getApellido());
                    System.out.println("Posición: " + jugador.getPosicion());
                    System.out.println("Capitán: " + (jugador.isEsCapitan() ? "Sí" : "No"));
                    System.out.println("Equipo: " + equipo.getNombre());
                    encontrado = true;
                    break;
                }
            }
        }

        if (!encontrado) {
            System.out.println("Jugador no encontrado.");
        }
    }

    private static void buscarEquipoPorNombre(Scanner scanner) {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        boolean encontrado = false;
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre: " + equipo.getNombre());
                System.out.println("Nombre del entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Nombre del capitán: " + equipo.getCapitan().getNombreCompleto());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Equipo no encontrado.");
        }
    }

    private static void mostrarJugadoresEquipo(Scanner scanner) {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        boolean encontrado = false;
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                System.out.println("Nombre del equipo: " + equipo.getNombre());
                System.out.println("Nombre del entrenador: " + equipo.getEntrenador().getNombreCompleto());
                System.out.println("Lista de jugadores: ");
                for (Jugador jugador : equipo.getJugadores()) {
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

    private static void eliminarEquipo(Scanner scanner) {
        System.out.print("Ingrese el nombre del equipo a eliminar: ");
        String nombreEquipo = scanner.nextLine();

        Iterator<Equipo> iterator = equipos.iterator();
        boolean encontrado = false;

        while (iterator.hasNext()) {
            Equipo equipo = iterator.next();
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                iterator.remove();
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            System.out.println("Equipo eliminado correctamente.");
        } else {
            System.out.println("Equipo no encontrado.");
        }
    }

    private static void importarListaJugadores(Scanner scanner) {
        System.out.print("Ingrese el nombre del archivo de importación: ");
        String nombreArchivo = scanner.nextLine();

        try (FileInputStream fis = new FileInputStream(nombreArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            List<Jugador> jugadoresImportados = (List<Jugador>) ois.readObject();

            for (Jugador jugador : jugadoresImportados) {
                for (Equipo equipo : equipos) {
                    if (jugador.getEquipo().getNombre().equals(equipo.getNombre())) {
                        jugador.setEquipo(equipo);
                        equipo.agregarJugador(jugador);
                        break;
                    }
                }
            }

            System.out.println("Lista de jugadores importada correctamente.");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al importar la lista de jugadores: " + e.getMessage());
        }
    }

    private static void exportarListaJugadores(Scanner scanner) {
        System.out.print("Ingrese el nombre del archivo de exportación: ");
        String nombreArchivo = scanner.nextLine();

        try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            List<Jugador> jugadoresExportados = new ArrayList<>();

            for (Equipo equipo : equipos) {
                jugadoresExportados.addAll(equipo.getJugadores());
            }

            oos.writeObject(jugadoresExportados);

            System.out.println("Lista de jugadores exportada correctamente.");

        } catch (IOException e) {
            System.out.println("Error al exportar la lista de jugadores: " + e.getMessage());
        }
    }

    private static void opcionesJediPadawan(Scanner scanner) {
        boolean salir = false;
        while (!salir) {
            System.out.println("----- MENÚ -----");
            System.out.println("1. Buscar equipo por nombre y mostrar jugadores ordenados por nombre");
            System.out.println("2. Buscar equipo por nombre y mostrar jugadores ordenados por camiseta");
            System.out.println("3. Buscar equipo por nombre y mostrar jugadores ordenados por posicion y camiseta");
            System.out.println("4. Exportar datos de los equipos");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    buscarEquipoPorNombreMostrarJugadoresOrdenadosPorNombre(scanner, equipos);
                    break;
                case 2:
                    buscarEquipoPorNombreMostrarJugadoresOrdenadosPorCamiseta(scanner, equipos);
                    break;
                case 3:
                    buscarEquipoPorNombreMostrarJugadoresOrdenadosPorPosicionYCamiseta(scanner, equipos);
                    break;
                case 4:
                    nivelMaestroExportarDatosEquipos(scanner, equipos);
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }
}
