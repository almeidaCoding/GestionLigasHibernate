import controller.DAOEquipos;
import controller.DAOLigas;
import controller.DAOPartido;
import model.Equipos;
import model.Ligas;
import model.Partido;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static controller.DAOEquipos.obtenerEquipos;
import static controller.DAOLigas.obtenerTodasLasLigas;

public class MainLigas {
    public static void main(String[] args) {

        DAOLigas daoLigas = new DAOLigas();
        DAOEquipos daoEquipos = new DAOEquipos();
        DAOPartido daoPartido = new DAOPartido();

        // Verificar si ya existen ligas
        List<Ligas> ligas = obtenerTodasLasLigas();
        if (ligas.isEmpty()) {
            // Si no existen, crear y insertar la liga
            Ligas ligaOne = new Ligas("Serie A", "20/08/2024", "10/06/2025");
            daoLigas.insertarLiga(ligaOne);
            ligas.add(ligaOne); // Agregar la liga creada a la lista
        }

        // Verificar si ya existen equipos
        List<Equipos> equipos = obtenerEquipos();
        if (equipos.isEmpty()) {
            // Si no existen, crear y insertar los equipos
            crearEquipos(ligas.get(0)); // Utilizar la primera liga disponible
            equipos = obtenerEquipos(); // Actualizar la lista de equipos
        }

        // Crear y insertar partidos
        crearPartidos(equipos, ligas, daoPartido, 6);

        // Mostrar datos de equipos y partidos
        mostrarDatosEquipos();
        mostrarDatosPartidos();
    }

    public static void crearEquipos(Ligas ligas){
        String [] nombresEquipos = {
                  "Juventus",
                  "Inter de Milán",
                  "AC Milan",
                  "AS Roma",
                  "SS Lazio",
                  "Napoli",
                  "Fiorentina",
                  "Sampdoria"
                };
        String [] ciudades = {
                "Turín",
                "Milán",
                "Milán",
                "Roma",
                "Roma",
                "Nápoles",
                "Florencia",
                "Génova"
        };

        DAOEquipos daoEquipos = new DAOEquipos();
        for(int i = 1; i < nombresEquipos.length; i++){
            Equipos equipos = new Equipos(nombresEquipos[i], ciudades[i]);
            equipos.setLiga(ligas);
            daoEquipos.insertarEquipo(equipos);
        }
        System.out.println("Equipos creados");
    }

    private static void crearPartidos(
            List<Equipos> equipos, List<Ligas> liga, DAOPartido daoPartido, int cantidad) {
        Random random = new Random();
        for (int i = 0; i < cantidad; i++) {
            // Supongamos que elegimos dos equipos al azar para cada partido
            Equipos equipoLocal = equipos.get(random.nextInt(equipos.size()));
            Equipos equipoVisitante = equipos.get(random.nextInt(equipos.size()));

            // Generar una fecha aleatoria entre hoy y dentro de los próximos 30 días
            LocalDate fechaPartido = LocalDate.now().plusDays(random.nextInt(30));

            // Generar un número aleatorio de goles para cada equipo
            int golesEquipoLocal = random.nextInt(6); // Máximo 5 goles
            int golesEquipoVisitante = random.nextInt(6); // Máximo 5 goles

            Ligas ultimaLiga = liga.get(liga.size() - 1);

            // Crear instancia de Partido
            Partido partido = new Partido(fechaPartido.toString(), golesEquipoLocal, golesEquipoVisitante);
            partido.setEquipoLocal(equipoLocal);
            partido.setEquipoVisitante(equipoVisitante);
            partido.setLiga(ultimaLiga);

            // Insertar el partido usando el DAO
            daoPartido.insertarPartido(partido);
        }
    }

    public static void mostrarDatosEquipos(){
        DAOEquipos daoEquipos = new DAOEquipos();
        List<Equipos> equipos = obtenerEquipos();

        System.out.println("Datos de todos los equipos\n");
        for(Equipos equipo : equipos){
            System.out.println("ID: " + equipo.getId_equipo());
            System.out.println("Nombre: " + equipo.getNombre_equipo());
            System.out.println("Ciudad: " + equipo.getCiudad());
            System.out.println("ID de la Liga: " + equipo.getLiga().getId_liga());
        }
     }

    public static void mostrarDatosPartidos() {
        DAOPartido daoPartidos = new DAOPartido();
        List<Partido> partidos = daoPartidos.consultarCalendarioPartidos();

        System.out.println("\nDatos de todos los partidos\n");
        for (Partido partido : partidos) {
            System.out.println("ID: " + partido.getId_partido());
            System.out.println("Fecha: " + partido.getFecha_partido());
            System.out.println("Goles equipo local: " + partido.getGoles_equipo_local());
            System.out.println("Goles equipo visitante: " + partido.getGoles_equipo_visitante());
            System.out.println("ID de equipo local: " + partido.getEquipoLocal().getId_equipo());
            System.out.println("ID de equipo visitante: " + partido.getEquipoVisitante().getId_equipo());
            System.out.println("ID de la Liga: " + partido.getLiga().getId_liga());
        }
    }
}
