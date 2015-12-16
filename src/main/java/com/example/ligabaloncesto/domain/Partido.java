package com.example.ligabaloncesto.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.example.ligabaloncesto.domain.util.CustomLocalDateSerializer;
import com.example.ligabaloncesto.domain.util.ISO8601LocalDateDeserializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Partido.
 */
@Entity
@Table(name = "PARTIDO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Partido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "fecha")
    private LocalDate fecha;

    @OneToMany(mappedBy = "partido")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Estadisticas_jugador_partido> estadisticas_jugador_partidos = new HashSet<>();

    @ManyToOne
    private Arbitro arbitro;

    @ManyToOne
    private Temporada temporada;

    @ManyToOne
    private Equipo equipo_local;

    @ManyToOne
    private Equipo equipo_visitante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Set<Estadisticas_jugador_partido> getEstadisticas_jugador_partidos() {
        return estadisticas_jugador_partidos;
    }

    public void setEstadisticas_jugador_partidos(Set<Estadisticas_jugador_partido> estadisticas_jugador_partidos) {
        this.estadisticas_jugador_partidos = estadisticas_jugador_partidos;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Equipo getEquipo_local() {
        return equipo_local;
    }

    public void setEquipo_local(Equipo equipo) {
        this.equipo_local = equipo;
    }

    public Equipo getEquipo_visitante() {
        return equipo_visitante;
    }

    public void setEquipo_visitante(Equipo equipo) {
        this.equipo_visitante = equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Partido partido = (Partido) o;

        if ( ! Objects.equals(id, partido.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", fecha='" + fecha + "'" +
                '}';
    }
}
