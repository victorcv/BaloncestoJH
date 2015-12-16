package com.example.ligabaloncesto.repository;

import com.example.ligabaloncesto.domain.Estadisticas_jugador_partido;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Estadisticas_jugador_partido entity.
 */
public interface Estadisticas_jugador_partidoRepository extends JpaRepository<Estadisticas_jugador_partido,Long> {

}
