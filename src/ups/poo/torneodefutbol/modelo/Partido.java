package ups.poo.torneodefutbol.modelo;

import java.io.Serializable;

public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;

    // ATRIBUTOS
    private Equipo equipo1;
    private Equipo equipo2;
    private Equipo ganador;
    private String etapa;

    // CONSTRUCTOR
    public Partido(String etapa, Equipo equipo1,
                   Equipo equipo2, Equipo ganador) {

        this.etapa = etapa;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.ganador = ganador;
    }

    // GETTER EQUIPO 1
    public Equipo getEquipo1() {
        return equipo1;
    }

    // GETTER EQUIPO 2
    public Equipo getEquipo2() {
        return equipo2;
    }

    // GETTER GANADOR
    public Equipo getGanador() {
        return ganador;
    }

    // GETTER ETAPA
    public String getEtapa() {
        return etapa;
    }
}