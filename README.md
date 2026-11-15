# ⚽ Torneo de Fútbol — Java MVC

**Simulación de un torneo de eliminación directa con 16 equipos, sorteos recursivos, manejo de excepciones y persistencia de resultados.**

Proyecto académico de **Programación Orientada a Objetos** desarrollado en **Java**, con una arquitectura **Modelo–Vista–Controlador (MVC)**. La versión documentada utiliza una **interfaz por consola**, que permite iniciar un torneo, revisar sus resultados, guardarlos y recuperarlos en una ejecución posterior.

## 📌 Descripción del proyecto

La aplicación organiza un torneo de fútbol a eliminación directa que comienza con **16 equipos**. En cada etapa se forman enfrentamientos aleatorios, se selecciona automáticamente un ganador por partido y los vencedores avanzan a la siguiente ronda hasta obtener un campeón.

La simulación recorre cuatro fases:

| Fase | Equipos participantes | Partidos | Clasificados |
| --- | ---: | ---: | ---: |
| Octavos de final | 16 | 8 | 8 |
| Cuartos de final | 8 | 4 | 4 |
| Semifinales | 4 | 2 | 2 |
| Gran final | 2 | 1 | 1 campeón |
| **Total** | | **15 partidos** | |

Cada partido guarda su etapa, los dos equipos enfrentados y el ganador. Los resultados se presentan en orden, incluyendo los **clasificados a cuartos, a semifinales y a la final**.

> **Alcance de la simulación:** los equipos se denominan inicialmente `Equipo 1` a `Equipo 16`. Tanto los cruces como los ganadores se determinan aleatoriamente; **no se calculan goles, marcadores ni estadísticas deportivas**.

## 🧰 Requisitos

- **Java Development Kit (JDK) 17 o superior**, correctamente configurado.
- **Eclipse IDE for Java Developers**, recomendado para abrir y ejecutar el proyecto.
- Windows, Linux o macOS.
- No requiere base de datos ni bibliotecas externas: usa las clases estándar de Java.

## 🚀 Instalación y ejecución en Eclipse

1. Descarga e instala **Eclipse IDE for Java Developers** desde la [página oficial de Eclipse](https://www.eclipse.org/downloads/packages/).
2. Descarga el proyecto y descomprime el archivo, si está comprimido.
3. Abre **Eclipse**, elige un espacio de trabajo y selecciona **File → Open Projects from File System…**.
4. Haz clic en **Directory…** y selecciona la carpeta principal del proyecto, que contiene `.project` y `src`. Marca el proyecto detectado y pulsa **Finish**.
5. Comprueba en **Project → Properties → Java Build Path → Libraries** que **JRE System Library** corresponda a un JDK 17 o superior.
6. En **Package Explorer**, abre `src/ups/poo/torneodefutbol/Aplicacion/MainTorneo.java`. Haz clic derecho sobre el archivo y selecciona **Run As → Java Application**.
7. Usa la pestaña **Console** de Eclipse para ver el menú y escribir tus opciones.

**Punto de entrada:** `ups.poo.torneodefutbol.Aplicacion.MainTorneo`

No es necesario instalar MySQL, Maven, Gradle ni un servidor para utilizar la versión por consola.

## 🎮 Menú y funciones

Al iniciar aparece un menú similar al siguiente:

```text
===== TORNEO DE FÚTBOL =====
1. Crear y jugar nuevo torneo
2. Mostrar último torneo
3. Guardar torneo
4. Cargar torneo
5. Salir
Elige una opción:
```

| Opción | Acción | Resultado |
| --- | --- | --- |
| **1. Crear y jugar nuevo torneo** | Genera y juega las cuatro fases del torneo. | Muestra partidos, ganadores, clasificados y campeón. |
| **2. Mostrar último torneo** | Consulta el torneo que permanece en memoria. | Vuelve a presentar todos los resultados sin hacer otro sorteo. |
| **3. Guardar torneo** | Serializa el torneo actual. | Crea o sobrescribe el archivo `torneo.ser`. |
| **4. Cargar torneo** | Deserializa un torneo guardado anteriormente. | Recupera equipos, partidos y campeón. |
| **5. Salir** | Finaliza el menú. | Cierra la aplicación. |

### Ejemplo de funcionamiento

Los enfrentamientos y sus ganadores cambian en cada torneo. Una parte de la salida podría ser:

```text
=== CUARTOS DE FINAL ===
Partido 1: Equipo 3 VS Equipo 11
GANADOR: Equipo 11
Partido 2: Equipo 5 VS Equipo 9
GANADOR: Equipo 5
...

=== CLASIFICADOS A SEMIFINALES ===
Equipo 11
Equipo 5
Equipo 2
Equipo 15

=== SEMIFINALES ===
...

=== CLASIFICADOS A LA FINAL ===
Equipo 11
Equipo 15

=== GRAN FINAL ===
Partido 1: Equipo 11 VS Equipo 15
GANADOR: Equipo 15

CAMPEÓN DEL TORNEO
Equipo 15
```

Este es un **ejemplo ilustrativo**, no un resultado fijo del sistema.

## 🗂️ Estructura de carpetas

```text
src/
└── ups/poo/torneodefutbol/
    ├── Aplicacion/
    │   └── MainTorneo.java
    ├── controlador/
    │   └── TorneoControlador.java
    ├── excepciones/
    │   ├── NumeroEquiposInvalidoException.java
    │   └── SorteoInvalidoException.java
    ├── modelo/
    │   ├── Equipo.java
    │   ├── Partido.java
    │   ├── Torneo.java
    │   └── PersistenciaTorneo.java
    └── vista/
        └── TorneoVista.java
```

### Responsabilidad de cada clase

| Clase | Responsabilidad |
| --- | --- |
| `Equipo` | Representa a un participante e identifica su nombre. |
| `Partido` | Registra la etapa, los dos oponentes y el ganador. |
| `Torneo` | Mantiene los equipos, ejecuta las rondas y contiene el algoritmo recursivo del sorteo. |
| `PersistenciaTorneo` | Guarda y carga el estado del torneo desde un archivo binario. |
| `NumeroEquiposInvalidoException` | Señala cantidades de equipos incompatibles con la eliminación directa. |
| `SorteoInvalidoException` | Señala situaciones inválidas al asignar oponentes. |
| `TorneoControlador` | Atiende solicitudes de la vista y coordina las operaciones del modelo. |
| `TorneoVista` | Presenta el menú, recibe entradas por consola y muestra los resultados o errores. |
| `MainTorneo` | Crea los componentes e inicia la aplicación. |

## 🧩 Arquitectura MVC

La aplicación separa **datos**, **reglas** y **presentación**:

```text
Usuario
  │
  ▼
Vista: TorneoVista
  │  Solicita acciones
  ▼
Controlador: TorneoControlador
  │  Invoca operaciones
  ▼
Modelo: Torneo + Equipo + Partido
        + PersistenciaTorneo
  │
  └──── Resultados ──► Controlador ──► Vista ──► Usuario
```

- **Modelo:** almacena el torneo y aplica las reglas. La recursividad y la selección de ganadores se encuentran en `Torneo.java`; la lectura y escritura del archivo se realizan mediante `PersistenciaTorneo.java`.
- **Vista:** utiliza `Scanner` y `System.out` para gestionar la interacción. No decide enfrentamientos ni resultados deportivos.
- **Controlador:** conecta la vista con el modelo; inicia los torneos, entrega datos para su presentación e invoca los métodos de guardado y carga.

Esta separación facilita mantener y ampliar el programa sin mezclar su lógica con la interfaz.

## 🔁 Sorteo recursivo

El sorteo está implementado en el modelo (`Torneo.java`) mediante el método **`emparejarRecursivo(...)`**:

1. Se valida que la cantidad de participantes de la ronda sea adecuada (potencia de dos y al menos dos).
2. `Collections.shuffle(...)` mezcla los equipos para producir enfrentamientos aleatorios.
3. El método recursivo toma dos equipos consecutivos y forma un partido.
4. Se verifica que no se repitan equipos dentro de la misma etapa.
5. Se elige aleatoriamente un ganador y se guarda el partido.
6. El método vuelve a llamarse con `indice + 2`.
7. El **caso base** se alcanza cuando el índice llega al tamaño de la lista.

Los ganadores conforman la lista de participantes de la ronda siguiente. La misma lógica se reutiliza para octavos, cuartos, semifinales y final.

## 🛡️ Manejo de excepciones

El proyecto incorpora excepciones personalizadas que extienden `Exception`:

- **`NumeroEquiposInvalidoException`:** se lanza cuando una ronda tiene una cantidad de participantes inválida, por ejemplo 3, 5 o 7 equipos.
- **`SorteoInvalidoException`:** se lanza cuando la recursividad detecta un emparejamiento imposible, índices incorrectos o participantes repetidos.

La vista maneja las excepciones de negocio y las relacionadas con archivos mediante bloques **`try-catch`**, y muestra mensajes descriptivos sin finalizar abruptamente la aplicación en los casos contemplados. También se contemplan situaciones como intentar guardar sin haber jugado un torneo.

## 💾 Persistencia: serialización y deserialización

La información se almacena en el archivo **`torneo.ser`** mediante serialización nativa de Java:

- **Guardar:** `ObjectOutputStream` y `FileOutputStream` escriben el objeto `Torneo`.
- **Cargar:** `ObjectInputStream` y `FileInputStream` reconstruyen el objeto y su historial.
- `Torneo`, `Equipo` y `Partido` implementan `Serializable` para conservar conjuntamente el estado necesario.

El archivo se crea en el **directorio de trabajo** de la ejecución. En Eclipse, puedes comprobarlo en **Run → Run Configurations… → Java Application → Arguments → Working directory**. La opción **Guardar** puede sobrescribir un archivo `torneo.ser` existente.

### Prueba recomendada de persistencia

1. Ejecuta el programa y selecciona **1** para jugar un torneo.
2. Anota el equipo campeón y selecciona **3** para guardar.
3. Sal del programa con **5**.
4. Inicia nuevamente la aplicación y selecciona **4** para cargar.
5. Verifica que reaparezcan **los mismos enfrentamientos, ganadores y campeón**, sin ejecutar un sorteo nuevo.

**Nota:** los archivos serializados requieren compatibilidad entre las clases usadas al guardar y al cargar. Por seguridad, carga únicamente archivos `.ser` que hayas generado tú o cuyo origen sea confiable.

## ✨ Aspectos destacados e innovaciones del proyecto

El proyecto integra varias técnicas en una sola simulación educativa:

1. **Emparejamientos recursivos reutilizables:** una sola función recursiva permite organizar todas las etapas, evitando implementar un sorteo distinto por ronda.
2. **Avance automático por eliminación:** cada victoria actualiza los participantes de la siguiente fase hasta determinar al campeón.
3. **Control de integridad del sorteo:** validaciones y conjuntos de nombres impiden asignaciones duplicadas en la misma ronda.
4. **Historial completo de partidos:** no se guarda únicamente al campeón, sino también los enfrentamientos y ganadores por etapa.
5. **Persistencia entre ejecuciones:** los resultados se pueden cerrar, guardar y recuperar gracias a la serialización.
6. **Diseño modular MVC:** la interfaz, el control de acciones y las reglas del torneo están desacoplados.
7. **Excepciones específicas del dominio:** los errores del sorteo se comunican mediante tipos propios, facilitando su identificación.

Se trata de **características relevantes de la implementación académica**, no de afirmaciones de innovación tecnológica inédita.

## 🔍 Pruebas sugeridas

| Prueba | Resultado esperado |
| --- | --- |
| Crear un torneo nuevo | Se muestran 16 equipos y 15 partidos distribuidos en cuatro etapas. |
| Revisar los clasificados | Se observan 8 clasificados a cuartos, 4 a semifinales y 2 a la final. |
| Verificar repetidos | Ningún equipo participa en dos partidos de la misma etapa. |
| Mostrar el último torneo | Se presentan los mismos resultados del torneo en memoria. |
| Guardar y cargar | Se recupera el mismo campeón y el historial de encuentros. |
| Seleccionar una opción fuera de 1–5 | Se informa que la opción no es válida. |
| Intentar guardar antes de jugar | Se muestra un mensaje de error controlado. |
| Cargar sin un archivo disponible | Se informa del problema de lectura. |

## 🧯 Solución de problemas

**Tipos o paquetes sin resolver en Eclipse**  
Comprueba que los nombres de clases y archivos coincidan, que las declaraciones `package` correspondan a las carpetas y que `src` figure en **Project → Properties → Java Build Path → Source**. Usa **Project → Clean…** para limpiar la compilación.

**Se ejecuta una versión anterior del programa**  
Recompila el proyecto y comprueba que **Run → Run Configurations… → Java Application** apunte a `ups.poo.torneodefutbol.Aplicacion.MainTorneo`.

**No aparece `torneo.ser` o no se puede cargar**  
Asegúrate de guardar primero y de ejecutar desde el mismo directorio de trabajo. Comprueba los permisos de escritura y lectura.

**`InvalidClassException` o error al cargar un archivo antiguo**  
Puede deberse a cambios incompatibles en la estructura serializable. Guarda un torneo nuevo con la versión actual del proyecto.

## 🛠️ Posibles mejoras futuras

- Permitir registrar nombres de equipos desde la interfaz.
- Añadir una **interfaz gráfica con Java Swing**, sin modificar las reglas del modelo.
- Mostrar un cuadro de eliminación visual (bracket).
- Incorporar marcadores y estadísticas deportivas, en lugar de escoger ganadores solo al azar.
- Agregar pruebas automatizadas para validar sorteos y persistencia.

> Estas mejoras se plantean como **trabajo futuro**; no forman parte de las funciones descritas para la versión actual por consola.

## 🎓 Contexto académico

Proyecto de práctica de **Programación Orientada a Objetos**, orientado a demostrar la aplicación de **POO**, **MVC**, **recursividad**, **manejo de excepciones personalizadas** y **archivos serializados** mediante una aplicación funcional en Java.
