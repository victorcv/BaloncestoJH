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
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Jugador.
 */
@Entity
@Table(name = "JUGADOR")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Jugador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column(name = "nombre")
    private String nombre;
    
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "fecha")
    private LocalDate fecha;

    @Min(value = 0)        
    @Column(name = "total_canastas")
    private Integer totalCanastas;

    @Min(value = 0)        
    @Column(name = "total_asistencias")
    private Integer totalAsistencias;

    @Min(value = 0)        
    @Column(name = "total_rebotes")
    private Integer totalRebotes;
    
    @Column(name = "posicion")
    private String posicion;

    @OneToMany(mappedBy = "jugador")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Estadisticas_jugador_partido> estadisticas_jugador_partidos = new HashSet<>();

    @ManyToOne
    private Equipo equipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getTotalCanastas() {
        return totalCanastas;
    }

    public void setTotalCanastas(Integer totalCanastas) {
        this.totalCanastas = totalCanastas;
    }

    public Integer getTotalAsistencias() {
        return totalAsistencias;
    }

    public void setTotalAsistencias(Integer totalAsistencias) {
        this.totalAsistencias = totalAsistencias;
    }

    public Integer getTotalRebotes() {
        return totalRebotes;
    }

    public void setTotalRebotes(Integer totalRebotes) {
        this.totalRebotes = totalRebotes;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Set<Estadisticas_jugador_partido> getEstadisticas_jugador_partidos() {
        return estadisticas_jugador_partidos;
    }

    public void setEstadisticas_jugador_partidos(Set<Estadisticas_jugador_partido> estadisticas_jugador_partidos) {
        this.estadisticas_jugador_partidos = estadisticas_jugador_partidos;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Jugador jugador = (Jugador) o;

        if ( ! Objects.equals(id, jugador.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + "'" +
                ", fecha='" + fecha + "'" +
                ", totalCanastas='" + totalCanastas + "'" +
                ", totalAsistencias='" + totalAsistencias + "'" +
                ", totalRebotes='" + totalRebotes + "'" +
                ", posicion='" + posicion + "'" +
                '}';
    }
}
