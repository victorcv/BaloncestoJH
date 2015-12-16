package com.example.ligabaloncesto.web.rest;

import com.example.ligabaloncesto.Application;
import com.example.ligabaloncesto.domain.Estadisticas_jugador_partido;
import com.example.ligabaloncesto.repository.Estadisticas_jugador_partidoRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the Estadisticas_jugador_partidoResource REST controller.
 *
 * @see Estadisticas_jugador_partidoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class Estadisticas_jugador_partidoResourceTest {


    private static final Integer DEFAULT_ASISTENCIAS = 0;
    private static final Integer UPDATED_ASISTENCIAS = 1;

    private static final Integer DEFAULT_CANASTAS = 0;
    private static final Integer UPDATED_CANASTAS = 1;

    private static final Integer DEFAULT_FALTAS = 0;
    private static final Integer UPDATED_FALTAS = 1;

    @Inject
    private Estadisticas_jugador_partidoRepository estadisticas_jugador_partidoRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restEstadisticas_jugador_partidoMockMvc;

    private Estadisticas_jugador_partido estadisticas_jugador_partido;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Estadisticas_jugador_partidoResource estadisticas_jugador_partidoResource = new Estadisticas_jugador_partidoResource();
        ReflectionTestUtils.setField(estadisticas_jugador_partidoResource, "estadisticas_jugador_partidoRepository", estadisticas_jugador_partidoRepository);
        this.restEstadisticas_jugador_partidoMockMvc = MockMvcBuilders.standaloneSetup(estadisticas_jugador_partidoResource).setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        estadisticas_jugador_partido = new Estadisticas_jugador_partido();
        estadisticas_jugador_partido.setAsistencias(DEFAULT_ASISTENCIAS);
        estadisticas_jugador_partido.setCanastas(DEFAULT_CANASTAS);
        estadisticas_jugador_partido.setFaltas(DEFAULT_FALTAS);
    }

    @Test
    @Transactional
    public void createEstadisticas_jugador_partido() throws Exception {
        int databaseSizeBeforeCreate = estadisticas_jugador_partidoRepository.findAll().size();

        // Create the Estadisticas_jugador_partido

        restEstadisticas_jugador_partidoMockMvc.perform(post("/api/estadisticas_jugador_partidos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(estadisticas_jugador_partido)))
                .andExpect(status().isCreated());

        // Validate the Estadisticas_jugador_partido in the database
        List<Estadisticas_jugador_partido> estadisticas_jugador_partidos = estadisticas_jugador_partidoRepository.findAll();
        assertThat(estadisticas_jugador_partidos).hasSize(databaseSizeBeforeCreate + 1);
        Estadisticas_jugador_partido testEstadisticas_jugador_partido = estadisticas_jugador_partidos.get(estadisticas_jugador_partidos.size() - 1);
        assertThat(testEstadisticas_jugador_partido.getAsistencias()).isEqualTo(DEFAULT_ASISTENCIAS);
        assertThat(testEstadisticas_jugador_partido.getCanastas()).isEqualTo(DEFAULT_CANASTAS);
        assertThat(testEstadisticas_jugador_partido.getFaltas()).isEqualTo(DEFAULT_FALTAS);
    }

    @Test
    @Transactional
    public void getAllEstadisticas_jugador_partidos() throws Exception {
        // Initialize the database
        estadisticas_jugador_partidoRepository.saveAndFlush(estadisticas_jugador_partido);

        // Get all the estadisticas_jugador_partidos
        restEstadisticas_jugador_partidoMockMvc.perform(get("/api/estadisticas_jugador_partidos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(estadisticas_jugador_partido.getId().intValue())))
                .andExpect(jsonPath("$.[*].asistencias").value(hasItem(DEFAULT_ASISTENCIAS)))
                .andExpect(jsonPath("$.[*].canastas").value(hasItem(DEFAULT_CANASTAS)))
                .andExpect(jsonPath("$.[*].faltas").value(hasItem(DEFAULT_FALTAS)));
    }

    @Test
    @Transactional
    public void getEstadisticas_jugador_partido() throws Exception {
        // Initialize the database
        estadisticas_jugador_partidoRepository.saveAndFlush(estadisticas_jugador_partido);

        // Get the estadisticas_jugador_partido
        restEstadisticas_jugador_partidoMockMvc.perform(get("/api/estadisticas_jugador_partidos/{id}", estadisticas_jugador_partido.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(estadisticas_jugador_partido.getId().intValue()))
            .andExpect(jsonPath("$.asistencias").value(DEFAULT_ASISTENCIAS))
            .andExpect(jsonPath("$.canastas").value(DEFAULT_CANASTAS))
            .andExpect(jsonPath("$.faltas").value(DEFAULT_FALTAS));
    }

    @Test
    @Transactional
    public void getNonExistingEstadisticas_jugador_partido() throws Exception {
        // Get the estadisticas_jugador_partido
        restEstadisticas_jugador_partidoMockMvc.perform(get("/api/estadisticas_jugador_partidos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadisticas_jugador_partido() throws Exception {
        // Initialize the database
        estadisticas_jugador_partidoRepository.saveAndFlush(estadisticas_jugador_partido);

		int databaseSizeBeforeUpdate = estadisticas_jugador_partidoRepository.findAll().size();

        // Update the estadisticas_jugador_partido
        estadisticas_jugador_partido.setAsistencias(UPDATED_ASISTENCIAS);
        estadisticas_jugador_partido.setCanastas(UPDATED_CANASTAS);
        estadisticas_jugador_partido.setFaltas(UPDATED_FALTAS);
        

        restEstadisticas_jugador_partidoMockMvc.perform(put("/api/estadisticas_jugador_partidos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(estadisticas_jugador_partido)))
                .andExpect(status().isOk());

        // Validate the Estadisticas_jugador_partido in the database
        List<Estadisticas_jugador_partido> estadisticas_jugador_partidos = estadisticas_jugador_partidoRepository.findAll();
        assertThat(estadisticas_jugador_partidos).hasSize(databaseSizeBeforeUpdate);
        Estadisticas_jugador_partido testEstadisticas_jugador_partido = estadisticas_jugador_partidos.get(estadisticas_jugador_partidos.size() - 1);
        assertThat(testEstadisticas_jugador_partido.getAsistencias()).isEqualTo(UPDATED_ASISTENCIAS);
        assertThat(testEstadisticas_jugador_partido.getCanastas()).isEqualTo(UPDATED_CANASTAS);
        assertThat(testEstadisticas_jugador_partido.getFaltas()).isEqualTo(UPDATED_FALTAS);
    }

    @Test
    @Transactional
    public void deleteEstadisticas_jugador_partido() throws Exception {
        // Initialize the database
        estadisticas_jugador_partidoRepository.saveAndFlush(estadisticas_jugador_partido);

		int databaseSizeBeforeDelete = estadisticas_jugador_partidoRepository.findAll().size();

        // Get the estadisticas_jugador_partido
        restEstadisticas_jugador_partidoMockMvc.perform(delete("/api/estadisticas_jugador_partidos/{id}", estadisticas_jugador_partido.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Estadisticas_jugador_partido> estadisticas_jugador_partidos = estadisticas_jugador_partidoRepository.findAll();
        assertThat(estadisticas_jugador_partidos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
