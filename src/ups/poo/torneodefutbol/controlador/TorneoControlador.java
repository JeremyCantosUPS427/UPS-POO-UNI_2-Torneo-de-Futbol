package ups.poo.torneodefutbol.controlador;

import ups.poo.torneodefutbol.excepciones.*;
import ups.poo.torneodefutbol.modelo.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TorneoControlador {

    // ATRIBUTOS
    private static final String ARCHIVO = "torneo.ser";

    private Torneo torneo;

    // INICIAR NUEVO TORNEO
    public void iniciarNuevoTorneo()
            throws NumeroEquiposInvalidoException,
            SorteoInvalidoException {

        Torneo nuevo = new Torneo();

        nuevo.jugar();

        torneo = nuevo;
    }

    // COMPROBAR SI EXISTE UN TORNEO
    public boolean hayTorneo() {

        return torneo != null &&
                torneo.getCampeon() != null;
    }

    // OBTENER EQUIPOS
    public List<Equipo> obtenerEquipos() {

        return hayTorneo()
                ? torneo.getEquipos()
                : Collections.emptyList();
    }

    // OBTENER PARTIDOS
    public List<Partido> obtenerPartidos() {

        return hayTorneo()
                ? torneo.getPartidos()
                : Collections.emptyList();
    }

    // OBTENER CAMPEON
    public String obtenerCampeon() {

        return hayTorneo()
                ? torneo.getCampeon().getNombre()
                : "";
    }

    // GUARDAR TORNEO
    public void guardarTorneo() throws IOException {

        if (!hayTorneo()) {

            throw new IllegalStateException(
                    "No hay torneo para guardar."
            );
        }

        PersistenciaTorneo.guardar(
                torneo,
                ARCHIVO
        );
    }

    // CARGAR TORNEO
    public void cargarTorneo()
            throws IOException, ClassNotFoundException {

        Torneo cargado =
                PersistenciaTorneo.cargar(ARCHIVO);

        if (cargado.getCampeon() == null) {

            throw new IOException(
                    "El archivo no contiene un torneo finalizado."
            );
        }

        torneo = cargado;
    }
}