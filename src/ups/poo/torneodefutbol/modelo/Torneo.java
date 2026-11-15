package ups.poo.torneodefutbol.modelo;

import ups.poo.torneodefutbol.excepciones.NumeroEquiposInvalidoException;
import ups.poo.torneodefutbol.excepciones.SorteoInvalidoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Torneo implements Serializable {

    private static final long serialVersionUID = 1L;

    // ATRIBUTOS
    private final ArrayList<Equipo> equipos = new ArrayList<>();
    private final ArrayList<Partido> partidos = new ArrayList<>();

    private Equipo campeon;

    // CONSTRUCTOR
    public Torneo() throws NumeroEquiposInvalidoException {
        this(generarNombres());
    }

    // CONSTRUCTOR CON NOMBRES
    public Torneo(String[] nombres)
            throws NumeroEquiposInvalidoException {

        if (nombres == null || nombres.length != 16) {

            throw new NumeroEquiposInvalidoException(
                    "El torneo debe comenzar con exactamente 16 equipos."
            );
        }

        for (String nombre : nombres) {
            equipos.add(new Equipo(nombre));
        }
    }

    // CREAR LOS 16 EQUIPOS
    private static String[] generarNombres() {

        String[] nombres = new String[16];

        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = "Equipo " + (i + 1);
        }

        return nombres;
    }

    // EJECUTAR TORNEO
    public void jugar()
            throws NumeroEquiposInvalidoException,
            SorteoInvalidoException {

        partidos.clear();
        campeon = null;

        ArrayList<Equipo> participantes =
                new ArrayList<>(equipos);

        String[] etapas = {
                "OCTAVOS DE FINAL",
                "CUARTOS DE FINAL",
                "SEMIFINALES",
                "GRAN FINAL"
        };

        for (String etapa : etapas) {

            participantes = jugarEtapa(
                    participantes,
                    etapa
            );
        }

        campeon = participantes.get(0);
    }

    // EJECUTAR UNA ETAPA
    private ArrayList<Equipo> jugarEtapa(
            List<Equipo> participantes,
            String etapa
    ) throws NumeroEquiposInvalidoException,
            SorteoInvalidoException {

        int cantidad = participantes.size();

        // VALIDAR NUMERO DE EQUIPOS
        if (cantidad < 2 ||
                (cantidad & (cantidad - 1)) != 0) {

            throw new NumeroEquiposInvalidoException(
                    "Cantidad inválida en " + etapa +
                            ": " + cantidad +
                            ". Debe ser una potencia de 2."
            );
        }

        // MEZCLAR EQUIPOS
        ArrayList<Equipo> mezclados =
                new ArrayList<>(participantes);

        Collections.shuffle(mezclados);

        // LISTAS PARA RESULTADOS
        ArrayList<Equipo> ganadores =
                new ArrayList<>();

        ArrayList<Partido> partidosEtapa =
                new ArrayList<>();

        Set<String> nombresUsados =
                new HashSet<>();

        // LLAMAR AL METODO RECURSIVO
        emparejarRecursivo(
                mezclados,
                0,
                etapa,
                ganadores,
                partidosEtapa,
                nombresUsados
        );

        // COMPROBAR EMPAREJAMIENTOS
        if (ganadores.size() != cantidad / 2) {

            throw new SorteoInvalidoException(
                    "El sorteo no generó todos los partidos."
            );
        }

        partidos.addAll(partidosEtapa);

        return ganadores;
    }

    // METODO RECURSIVO
    private void emparejarRecursivo(
            List<Equipo> mezclados,
            int indice,
            String etapa,
            List<Equipo> ganadores,
            List<Partido> partidosEtapa,
            Set<String> nombresUsados
    ) throws SorteoInvalidoException {

        // CASO BASE
        if (indice == mezclados.size()) {
            return;
        }

        // VALIDAR INDICES
        if (indice < 0 ||
                indice + 1 >= mezclados.size()) {

            throw new SorteoInvalidoException(
                    "No se puede formar un par en la posición "
                            + indice
            );
        }

        // OBTENER EQUIPOS
        Equipo primero = mezclados.get(indice);
        Equipo segundo = mezclados.get(indice + 1);

        // VALIDAR EQUIPOS
        if (primero == null ||
                segundo == null ||
                primero == segundo ||
                primero.getNombre().equalsIgnoreCase(
                        segundo.getNombre())) {

            throw new SorteoInvalidoException(
                    "Partido inválido: equipos nulos o repetidos."
            );
        }

        String nombre1 =
                primero.getNombre().toLowerCase(Locale.ROOT);

        String nombre2 =
                segundo.getNombre().toLowerCase(Locale.ROOT);

        // EVITAR EQUIPOS REPETIDOS
        if (!nombresUsados.add(nombre1) ||
                !nombresUsados.add(nombre2)) {

            throw new SorteoInvalidoException(
                    "Un equipo se repite en la etapa " + etapa
            );
        }

        // SELECCIONAR GANADOR ALEATORIO
        Equipo ganador =
                ThreadLocalRandom.current().nextBoolean()
                        ? primero
                        : segundo;

        // GUARDAR PARTIDO
        partidosEtapa.add(
                new Partido(etapa, primero, segundo, ganador)
        );

        // GUARDAR GANADOR
        ganadores.add(ganador);

        // LLAMADA RECURSIVA
        emparejarRecursivo(
                mezclados,
                indice + 2,
                etapa,
                ganadores,
                partidosEtapa,
                nombresUsados
        );
    }

    // GETTERS

    public List<Equipo> getEquipos() {
        return Collections.unmodifiableList(equipos);
    }

    public List<Partido> getPartidos() {
        return Collections.unmodifiableList(partidos);
    }

    public Equipo getCampeon() {
        return campeon;
    }
}