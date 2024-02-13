package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "ligas")
public class Ligas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_liga;
    @Column
    private String nombre_liga;
    @Column
    private String fecha_inicio;
    @Column
    private String fecha_fin;

    @OneToMany (mappedBy = "liga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Equipos> equipos = new HashSet<>();


    public Ligas(String nombre_liga, String fecha_inicio, String fecha_fin) {
        this.nombre_liga = nombre_liga;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }
}
