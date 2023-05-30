package com.Fut5.App;

import com.Fut5.App.dominio.Entrenador;
import com.Fut5.App.dominio.Equipo;
import com.Fut5.App.dominio.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MenuExample {

    private static List<Equipo> equipos = new ArrayList<>();
    public static void main(String[] args) {
        // Crear el frame principal
        JFrame frame = new JFrame("Menú de ejemplo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));

        // Crear los botones para las opciones
        JButton crearEquipoButton = new JButton("Crear un equipo");
        JButton buscarJugadorButton = new JButton("Buscar jugador por nombre");
        JButton buscarEquipoButton = new JButton("Buscar equipo por nombre");
        JButton mostrarJugadoresButton = new JButton("Mostrar jugadores de un equipo");
        JButton eliminarEquipoButton = new JButton("Eliminar un equipo");
        JButton importarJugadoresButton = new JButton("Importar lista de jugadores desde archivo");
        JButton exportarJugadoresButton = new JButton("Exportar lista de jugadores a archivo");
        JButton salirButton = new JButton("Salir");

        // Agregar los botones al panel principal
        panel.add(crearEquipoButton);
        panel.add(buscarJugadorButton);
        panel.add(buscarEquipoButton);
        panel.add(mostrarJugadoresButton);
        panel.add(eliminarEquipoButton);
        panel.add(importarJugadoresButton);
        panel.add(exportarJugadoresButton);
        panel.add(salirButton);

        // Agregar ActionListener a los botones
        crearEquipoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarFormularioCrearEquipo();
            }
        });

        buscarJugadorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Opción Buscar jugador por nombre seleccionada");
            }
        });

        buscarEquipoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Opción Buscar equipo por nombre seleccionada");
            }
        });

        mostrarJugadoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Opción Mostrar jugadores de un equipo seleccionada");
            }
        });

        eliminarEquipoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Opción Eliminar un equipo seleccionada");
            }
        });

        importarJugadoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Opción Importar lista de jugadores desde archivo seleccionada");
            }
        });

        exportarJugadoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Opción Exportar lista de jugadores a archivo seleccionada");
            }
        });

        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Agregar el panel principal al frame
        frame.getContentPane().add(panel);

        // Mostrar el frame
        frame.setSize(300, 400);
        frame.setVisible(true);
    }

    private static void mostrarFormularioCrearEquipo() {
        JFrame frame = new JFrame("Crear Equipo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));

        JLabel nombreLabel = new JLabel("Nombre del equipo:");
        JTextField nombreField = new JTextField();
        JLabel fechaLabel = new JLabel("Fecha de creación:");
        JTextField fechaField = new JTextField();
        JLabel entrenadorNombreLabel = new JLabel("Nombre del entrenador:");
        JTextField entrenadorNombreField = new JTextField();
        JLabel entrenadorApellidoLabel = new JLabel("Apellido del entrenador:");
        JTextField entrenadorApellidoField = new JTextField();
        JLabel entrenadorEdadLabel = new JLabel("Edad del entrenador:");
        JTextField entrenadorEdadField = new JTextField();
        JLabel jugadoresLabel = new JLabel("Jugadores (nombre, apellido, posición):");
        JTextArea jugadoresArea = new JTextArea();

        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(fechaLabel);
        panel.add(fechaField);
        panel.add(entrenadorNombreLabel);
        panel.add(entrenadorNombreField);
        panel.add(entrenadorApellidoLabel);
        panel.add(entrenadorApellidoField);
        panel.add(entrenadorEdadLabel);
        panel.add(entrenadorEdadField);
        panel.add(jugadoresLabel);
        panel.add(jugadoresArea);

        JButton crearButton = new JButton("Crear");

        crearButton.addActionListener(e -> {
            String nombreEquipo = nombreField.getText();
            LocalDateTime fechaCreacion = LocalDateTime.now();
            String nombreEntrenador = entrenadorNombreField.getText();
            String apellidoEntrenador = entrenadorApellidoField.getText();
            int edadEntrenador = Integer.parseInt(entrenadorEdadField.getText());

            Entrenador entrenador = new Entrenador(nombreEntrenador, apellidoEntrenador, edadEntrenador);
            Equipo equipo = new Equipo(nombreEquipo, fechaCreacion, entrenador);

            String jugadoresTexto = jugadoresArea.getText();
            String[] jugadoresArray = jugadoresTexto.split("\n");

            for (String jugador : jugadoresArray) {
                String[] jugadorInfo = jugador.split(",");
                String nombreJugador = jugadorInfo[0].trim();
                String apellidoJugador = jugadorInfo[1].trim();
                String posicionJugador = jugadorInfo[2].trim();
                double alturaJugador = Double.parseDouble(jugadorInfo[3].trim());
                int cantidadGoles = Integer.parseInt(jugadorInfo[4].trim());
                int cantidadPartidos = Integer.parseInt(jugadorInfo[5].trim());
                boolean esCapitan = Boolean.parseBoolean(jugadorInfo[6].trim());
                int numeroCamiseta = Integer.parseInt(jugadorInfo[7].trim());


                Jugador nuevoJugador = new Jugador(nombreJugador, apellidoJugador, alturaJugador, posicionJugador,
                        cantidadGoles, cantidadPartidos, esCapitan, numeroCamiseta);
                equipo.agregarJugador(nuevoJugador);
            }

            equipos.add(equipo);

            JOptionPane.showMessageDialog(frame, "Equipo creado exitosamente");

            frame.dispose();
        });

        panel.add(new JLabel());
        panel.add(crearButton);

        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}
