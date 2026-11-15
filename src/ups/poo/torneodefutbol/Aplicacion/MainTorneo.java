package ups.poo.torneodefutbol.Aplicacion;

import ups.poo.torneodefutbol.controlador.*;
import ups.poo.torneodefutbol.vista.*;

public class MainTorneo {

    public static void main(String[] args) {

        // CREAR CONTROLADOR
        TorneoControlador controlador =
                new TorneoControlador();

        // CREAR VISTA
        TorneoVista vista =
                new TorneoVista(controlador);

        // INICIAR PROGRAMA
        vista.iniciar();
    }
}