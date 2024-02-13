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
@Table(name = "equipos")
public class Equipos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_equipo;
    @Column
    private String nombre_equipo;
    @Column
    private String ciudad;

    @ManyToOne
    @JoinColumn(name="id_liga", referencedColumnName = "id_liga")
    private Ligas liga;

    public Equipos(String nombre_equipo, String ciudad) {
        this.nombre_equipo = nombre_equipo;
        this.ciudad = ciudad;
    }
}
