package com.Fut5.App;

/*mport com.Fut5.App.dominio.Entrenador;
import com.Fut5.App.dominio.Equipo;
import com.Fut5.App.dominio.Jugador;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Fut5App {

    private List<Equipo> equipos;

    public Fut5App(){
        this.equipos = new ArrayList<>();
    }

    public void ejecutar() {
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
            System.out.println("8. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

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
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    private void crearEquipo(Scanner scanner) {
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
        scanner.nextLine(); // Consumir el salto de línea

        Entrenador entrenador = new Entrenador(nombreEntrenador, apellidoEntrenador, edadEntrenador);
        Equipo equipo = new Equipo(nombreEquipo, fechaCreacion, entrenador);

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
            System.out.print("Ingrese el número de camiseta del jugador: ");
            int numeroCamiseta = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            Jugador jugador = new Jugador(nombreJugador, apellidoJugador, alturaJugador, posicionJugador,
                    golesJugador, partidosJugador, esCapitan, numeroCamiseta, equipo);
            equipo.agregarJugador(jugador);
            equipo.agregarJugador(jugador);

            System.out.print("¿Desea cargar otro jugador? (S/N): ");
            String opcion = scanner.nextLine();
            cargarJugador = opcion.equalsIgnoreCase("S");
        }

        equipos.add(equipo);
        System.out.println("Equipo creado exitosamente.");
    }

    private void buscarJugadorPorNombre(Scanner scanner) {
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

    private void buscarEquipoPorNombre(Scanner scanner) {
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

    private void mostrarJugadoresEquipo(Scanner scanner) {
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

    private void eliminarEquipo(Scanner scanner) {
        System.out.print("Ingrese el nombre del equipo que desea eliminar: ");
        String nombreEquipo = scanner.nextLine();

        boolean encontrado = false;
        for (Iterator<Equipo> iterator = equipos.iterator(); iterator.hasNext(); ) {
            Equipo equipo = iterator.next();
            if (equipo.getNombre().equalsIgnoreCase(nombreEquipo)) {
                iterator.remove();
                System.out.println("Equipo eliminado exitosamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Equipo no encontrado.");
        }
    }

    private void importarListaJugadores(Scanner scanner) {
        System.out.print("Ingrese el nombre del archivo (csv o txt) que contiene la lista de jugadores: ");
        String nombreArchivo = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datosJugador = linea.split(",");
                // Extraer los datos del jugador de cada línea y crear el objeto Jugador
                // Agregar el jugador al equipo correspondiente
            }
            System.out.println("Lista de jugadores importada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al importar la lista de jugadores: " + e.getMessage());
        }
    }

    private void exportarListaJugadores(Scanner scanner) {
        System.out.print("Ingrese el nombre del archivo de destino (csv o txt) para exportar la lista de jugadores: ");
        String nombreArchivo = scanner.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Equipo equipo : equipos) {
                for (Jugador jugador : equipo.getJugadores()) {
                    String linea = jugador.getNombre() + "," + jugador.getApellido() + "," + jugador.getAltura()
                            + "," + jugador.getPosicion() + "," + jugador.getCantidadGoles() + "," + jugador.getCantidadPartidos()
                            + "," + (jugador.isEsCapitan() ? "S" : "N") + "," + jugador.getNumeroCamiseta()
                            + "," + equipo.getNombre();
                    bw.write(linea);
                    bw.newLine();
                }
            }
            System.out.println("Lista de jugadores exportada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al exportar la lista de jugadores: " + e.getMessage());
        }
    }

    // Implementar el resto de los métodos según las funcionalidades requeridas

    public static void main(String[] args) {
        Fut5App app = new Fut5App();
        app.ejecutar();
    }


}*/
