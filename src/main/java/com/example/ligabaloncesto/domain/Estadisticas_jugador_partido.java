package com.example.ligabaloncesto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Estadisticas_jugador_partido.
 */
@Entity
@Table(name = "ESTADISTICAS_JUGADOR_PARTIDO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Estadisticas_jugador_partido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

    @Min(value = 0)        
    @Column(name = "asistencias")
    private Integer asistencias;

    @Min(value = 0)        
    @Column(name = "canastas")
    private Integer canastas;

    @Min(value = 0)        
    @Column(name = "faltas")
    private Integer faltas;

    @ManyToOne
    private Jugador jugador;

    @ManyToOne
    private Partido partido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Integer asistencias) {
        this.asistencias = asistencias;
    }

    public Integer getCanastas() {
        return canastas;
    }

    public void setCanastas(Integer canastas) {
        this.canastas = canastas;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Estadisticas_jugador_partido estadisticas_jugador_partido = (Estadisticas_jugador_partido) o;

        if ( ! Objects.equals(id, estadisticas_jugador_partido.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Estadisticas_jugador_partido{" +
                "id=" + id +
                ", asistencias='" + asistencias + "'" +
                ", canastas='" + canastas + "'" +
                ", faltas='" + faltas + "'" +
                '}';
    }
}
