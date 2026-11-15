package ups.poo.torneodefutbol.vista;

import ups.poo.torneodefutbol.controlador.*;
import ups.poo.torneodefutbol.excepciones.*;
import ups.poo.torneodefutbol.modelo.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TorneoVista {

    // ATRIBUTOS
    private final TorneoControlador controlador;

    private final Scanner scanner =
            new Scanner(System.in);

    // CONSTRUCTOR
    public TorneoVista(TorneoControlador controlador) {

        this.controlador = controlador;
    }

    // MENU PRINCIPAL
    public void iniciar() {

        boolean salir = false;

        while (!salir) {

            System.out.println("\n===== TORNEO DE FÚTBOL =====");
            System.out.println("1. Crear y jugar nuevo torneo");
            System.out.println("2. Mostrar último torneo");
            System.out.println("3. Guardar torneo");
            System.out.println("4. Cargar torneo");
            System.out.println("5. Salir");

            System.out.print("Elige una opción: ");

            String opcion = scanner.nextLine().trim();

            try {

                switch (opcion) {

                    case "1":

                        controlador.iniciarNuevoTorneo();

                        mostrarResultados();

                        break;

                    case "2":

                        mostrarResultados();

                        break;

                    case "3":

                        controlador.guardarTorneo();

                        System.out.println(
                                "Torneo guardado en torneo.ser"
                        );

                        break;

                    case "4":

                        controlador.cargarTorneo();

                        System.out.println(
                                "Torneo cargado correctamente."
                        );

                        mostrarResultados();

                        break;

                    case "5":

                        salir = true;

                        System.out.println("Hasta pronto.");

                        break;

                    default:

                        System.out.println(
                                "Opción inválida. Ingresa del 1 al 5."
                        );
                }

            } catch (NumeroEquiposInvalidoException |
                     SorteoInvalidoException e) {

                System.out.println(
                        "Error del sorteo: " + e.getMessage()
                );

            } catch (IOException |
                     ClassNotFoundException e) {

                System.out.println(
                        "Error de archivo: " + e.getMessage()
                );

            } catch (IllegalArgumentException |
                     IllegalStateException e) {

                System.out.println(
                        "Error: " + e.getMessage()
                );
            }
        }
    }

    // MOSTRAR RESULTADOS DEL TORNEO
    private void mostrarResultados() {

        if (!controlador.hayTorneo()) {

            System.out.println(
                    "Primero crea un torneo o carga uno guardado."
            );

            return;
        }

        // MOSTRAR PARTICIPANTES
        System.out.println(
                "\n=== 16 EQUIPOS PARTICIPANTES ==="
        );

        for (Equipo equipo : controlador.obtenerEquipos()) {

            System.out.println(equipo.getNombre());
        }

        // OBTENER PARTIDOS
        List<Partido> partidos =
                controlador.obtenerPartidos();

        String[] etapas = {
                "OCTAVOS DE FINAL",
                "CUARTOS DE FINAL",
                "SEMIFINALES",
                "GRAN FINAL"
        };

        String[] etiquetas = {
                "CLASIFICADOS A CUARTOS",
                "CLASIFICADOS A SEMIFINALES",
                "CLASIFICADOS A LA FINAL",
                null
        };

        // MOSTRAR TODAS LAS ETAPAS
        for (int etapa = 0; etapa < etapas.length; etapa++) {

            System.out.println(
                    "\n=== " + etapas[etapa] + " ==="
            );

            ArrayList<Equipo> ganadores =
                    new ArrayList<>();

            int numero = 1;

            for (Partido partido : partidos) {

                if (etapas[etapa].equals(partido.getEtapa())) {

                    System.out.println(
                            "Partido " + numero++ + ": " +
                                    partido.getEquipo1().getNombre() +
                                    " VS " +
                                    partido.getEquipo2().getNombre()
                    );

                    System.out.println(
                            "GANADOR: " +
                                    partido.getGanador().getNombre()
                    );

                    ganadores.add(partido.getGanador());
                }
            }

            // MOSTRAR CLASIFICADOS
            if (etiquetas[etapa] != null) {

                System.out.println(
                        "\n=== " + etiquetas[etapa] + " ==="
                );

                for (Equipo ganador : ganadores) {

                    System.out.println(
                            ganador.getNombre()
                    );
                }
            }
        }

        // MOSTRAR CAMPEON
        System.out.println(
                "\n=============================="
        );

        System.out.println(
                "       CAMPEÓN DEL TORNEO"
        );

        System.out.println(
                "       " + controlador.obtenerCampeon()
        );

        System.out.println(
                "=============================="
        );
    }
}