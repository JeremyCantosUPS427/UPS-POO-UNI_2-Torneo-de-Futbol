package ups.poo.torneodefutbol.modelo;

import java.io.Serializable;

public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    // ATRIBUTOS
    private final String nombre;

    // CONSTRUCTOR
    public Equipo(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre del equipo no puede estar vacío."
            );
        }

        this.nombre = nombre.trim();
    }

    // GETTER
    public String getNombre() {
        return nombre;
    }
}