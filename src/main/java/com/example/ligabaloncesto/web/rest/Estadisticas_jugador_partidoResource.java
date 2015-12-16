package com.example.ligabaloncesto.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.example.ligabaloncesto.domain.Estadisticas_jugador_partido;
import com.example.ligabaloncesto.repository.Estadisticas_jugador_partidoRepository;
import com.example.ligabaloncesto.web.rest.util.HeaderUtil;
import com.example.ligabaloncesto.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Estadisticas_jugador_partido.
 */
@RestController
@RequestMapping("/api")
public class Estadisticas_jugador_partidoResource {

    private final Logger log = LoggerFactory.getLogger(Estadisticas_jugador_partidoResource.class);

    @Inject
    private Estadisticas_jugador_partidoRepository estadisticas_jugador_partidoRepository;

    /**
     * POST  /estadisticas_jugador_partidos -> Create a new estadisticas_jugador_partido.
     */
    @RequestMapping(value = "/estadisticas_jugador_partidos",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Estadisticas_jugador_partido> create(@Valid @RequestBody Estadisticas_jugador_partido estadisticas_jugador_partido) throws URISyntaxException {
        log.debug("REST request to save Estadisticas_jugador_partido : {}", estadisticas_jugador_partido);
        if (estadisticas_jugador_partido.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new estadisticas_jugador_partido cannot already have an ID").body(null);
        }
        Estadisticas_jugador_partido result = estadisticas_jugador_partidoRepository.save(estadisticas_jugador_partido);
        return ResponseEntity.created(new URI("/api/estadisticas_jugador_partidos/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("estadisticas_jugador_partido", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /estadisticas_jugador_partidos -> Updates an existing estadisticas_jugador_partido.
     */
    @RequestMapping(value = "/estadisticas_jugador_partidos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Estadisticas_jugador_partido> update(@Valid @RequestBody Estadisticas_jugador_partido estadisticas_jugador_partido) throws URISyntaxException {
        log.debug("REST request to update Estadisticas_jugador_partido : {}", estadisticas_jugador_partido);
        if (estadisticas_jugador_partido.getId() == null) {
            return create(estadisticas_jugador_partido);
        }
        Estadisticas_jugador_partido result = estadisticas_jugador_partidoRepository.save(estadisticas_jugador_partido);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("estadisticas_jugador_partido", estadisticas_jugador_partido.getId().toString()))
                .body(result);
    }

    /**
     * GET  /estadisticas_jugador_partidos -> get all the estadisticas_jugador_partidos.
     */
    @RequestMapping(value = "/estadisticas_jugador_partidos",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Estadisticas_jugador_partido>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Estadisticas_jugador_partido> page = estadisticas_jugador_partidoRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/estadisticas_jugador_partidos", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /estadisticas_jugador_partidos/:id -> get the "id" estadisticas_jugador_partido.
     */
    @RequestMapping(value = "/estadisticas_jugador_partidos/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Estadisticas_jugador_partido> get(@PathVariable Long id) {
        log.debug("REST request to get Estadisticas_jugador_partido : {}", id);
        return Optional.ofNullable(estadisticas_jugador_partidoRepository.findOne(id))
            .map(estadisticas_jugador_partido -> new ResponseEntity<>(
                estadisticas_jugador_partido,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /estadisticas_jugador_partidos/:id -> delete the "id" estadisticas_jugador_partido.
     */
    @RequestMapping(value = "/estadisticas_jugador_partidos/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete Estadisticas_jugador_partido : {}", id);
        estadisticas_jugador_partidoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("estadisticas_jugador_partido", id.toString())).build();
    }
}
