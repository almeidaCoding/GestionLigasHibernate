package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "partido")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_partido;
    @Column
    private String fecha_partido;
    @Column
    private int goles_equipo_local;
    @Column
    private int goles_equipo_visitante;

    @ManyToOne
    @JoinColumn(name = "id_equipo_local", referencedColumnName = "id_equipo")
    private Equipos equipoLocal;

    @ManyToOne
    @JoinColumn(name = "id_equipo_visitante", referencedColumnName = "id_equipo")
    private Equipos equipoVisitante;

    @ManyToOne
    @JoinColumn(name="id_liga", referencedColumnName = "id_liga")
    private Ligas liga;



    public Partido(String fecha_partido, int goles_equipo_local, int goles_equipo_visitante) {
        this.fecha_partido = fecha_partido;
        this.goles_equipo_local = goles_equipo_local;
        this.goles_equipo_visitante = goles_equipo_visitante;
    }
}
