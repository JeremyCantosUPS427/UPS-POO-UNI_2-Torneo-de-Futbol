package ups.poo.torneodefutbol.modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class PersistenciaTorneo {

    private PersistenciaTorneo() {
    }

    // SERIALIZACION
    public static void guardar(
            Torneo torneo,
            String archivo
    ) throws IOException {

        try (ObjectOutputStream salida =
                     new ObjectOutputStream(
                             new FileOutputStream(archivo)
                     )) {

            salida.writeObject(torneo);
        }
    }

    // DESERIALIZACION
    public static Torneo cargar(String archivo)
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream entrada =
                     new ObjectInputStream(
                             new FileInputStream(archivo)
                     )) {

            Object objeto = entrada.readObject();

            if (!(objeto instanceof Torneo)) {

                throw new IOException(
                        "El archivo no contiene un Torneo válido."
                );
            }

            return (Torneo) objeto;
        }
    }
}