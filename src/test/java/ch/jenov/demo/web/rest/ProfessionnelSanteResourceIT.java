package ch.jenov.demo.web.rest;

import ch.jenov.demo.JenovDemoApp;
import ch.jenov.demo.domain.ProfessionnelSante;
import ch.jenov.demo.repository.ProfessionnelSanteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ch.jenov.demo.domain.enumeration.Sexe;
/**
 * Integration tests for the {@link ProfessionnelSanteResource} REST controller.
 */
@SpringBootTest(classes = JenovDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProfessionnelSanteResourceIT {

    private static final String DEFAULT_IDE = "AAAAAAAAAA";
    private static final String UPDATED_IDE = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Sexe DEFAULT_SEXE = Sexe.M;
    private static final Sexe UPDATED_SEXE = Sexe.F;

    private static final String DEFAULT_RUE = "AAAAAAAAAA";
    private static final String UPDATED_RUE = "BBBBBBBBBB";

    @Autowired
    private ProfessionnelSanteRepository professionnelSanteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProfessionnelSanteMockMvc;

    private ProfessionnelSante professionnelSante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfessionnelSante createEntity(EntityManager em) {
        ProfessionnelSante professionnelSante = new ProfessionnelSante()
            .ide(DEFAULT_IDE)
            .prenom(DEFAULT_PRENOM)
            .nom(DEFAULT_NOM)
            .sexe(DEFAULT_SEXE)
            .rue(DEFAULT_RUE);
        return professionnelSante;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProfessionnelSante createUpdatedEntity(EntityManager em) {
        ProfessionnelSante professionnelSante = new ProfessionnelSante()
            .ide(UPDATED_IDE)
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .sexe(UPDATED_SEXE)
            .rue(UPDATED_RUE);
        return professionnelSante;
    }

    @BeforeEach
    public void initTest() {
        professionnelSante = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfessionnelSante() throws Exception {
        int databaseSizeBeforeCreate = professionnelSanteRepository.findAll().size();
        // Create the ProfessionnelSante
        restProfessionnelSanteMockMvc.perform(post("/api/professionnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(professionnelSante)))
            .andExpect(status().isCreated());

        // Validate the ProfessionnelSante in the database
        List<ProfessionnelSante> professionnelSanteList = professionnelSanteRepository.findAll();
        assertThat(professionnelSanteList).hasSize(databaseSizeBeforeCreate + 1);
        ProfessionnelSante testProfessionnelSante = professionnelSanteList.get(professionnelSanteList.size() - 1);
        assertThat(testProfessionnelSante.getIde()).isEqualTo(DEFAULT_IDE);
        assertThat(testProfessionnelSante.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testProfessionnelSante.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testProfessionnelSante.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testProfessionnelSante.getRue()).isEqualTo(DEFAULT_RUE);
    }

    @Test
    @Transactional
    public void createProfessionnelSanteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = professionnelSanteRepository.findAll().size();

        // Create the ProfessionnelSante with an existing ID
        professionnelSante.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfessionnelSanteMockMvc.perform(post("/api/professionnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(professionnelSante)))
            .andExpect(status().isBadRequest());

        // Validate the ProfessionnelSante in the database
        List<ProfessionnelSante> professionnelSanteList = professionnelSanteRepository.findAll();
        assertThat(professionnelSanteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProfessionnelSantes() throws Exception {
        // Initialize the database
        professionnelSanteRepository.saveAndFlush(professionnelSante);

        // Get all the professionnelSanteList
        restProfessionnelSanteMockMvc.perform(get("/api/professionnel-santes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(professionnelSante.getId().intValue())))
            .andExpect(jsonPath("$.[*].ide").value(hasItem(DEFAULT_IDE)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].rue").value(hasItem(DEFAULT_RUE)));
    }
    
    @Test
    @Transactional
    public void getProfessionnelSante() throws Exception {
        // Initialize the database
        professionnelSanteRepository.saveAndFlush(professionnelSante);

        // Get the professionnelSante
        restProfessionnelSanteMockMvc.perform(get("/api/professionnel-santes/{id}", professionnelSante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(professionnelSante.getId().intValue()))
            .andExpect(jsonPath("$.ide").value(DEFAULT_IDE))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.rue").value(DEFAULT_RUE));
    }
    @Test
    @Transactional
    public void getNonExistingProfessionnelSante() throws Exception {
        // Get the professionnelSante
        restProfessionnelSanteMockMvc.perform(get("/api/professionnel-santes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfessionnelSante() throws Exception {
        // Initialize the database
        professionnelSanteRepository.saveAndFlush(professionnelSante);

        int databaseSizeBeforeUpdate = professionnelSanteRepository.findAll().size();

        // Update the professionnelSante
        ProfessionnelSante updatedProfessionnelSante = professionnelSanteRepository.findById(professionnelSante.getId()).get();
        // Disconnect from session so that the updates on updatedProfessionnelSante are not directly saved in db
        em.detach(updatedProfessionnelSante);
        updatedProfessionnelSante
            .ide(UPDATED_IDE)
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .sexe(UPDATED_SEXE)
            .rue(UPDATED_RUE);

        restProfessionnelSanteMockMvc.perform(put("/api/professionnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProfessionnelSante)))
            .andExpect(status().isOk());

        // Validate the ProfessionnelSante in the database
        List<ProfessionnelSante> professionnelSanteList = professionnelSanteRepository.findAll();
        assertThat(professionnelSanteList).hasSize(databaseSizeBeforeUpdate);
        ProfessionnelSante testProfessionnelSante = professionnelSanteList.get(professionnelSanteList.size() - 1);
        assertThat(testProfessionnelSante.getIde()).isEqualTo(UPDATED_IDE);
        assertThat(testProfessionnelSante.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testProfessionnelSante.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testProfessionnelSante.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testProfessionnelSante.getRue()).isEqualTo(UPDATED_RUE);
    }

    @Test
    @Transactional
    public void updateNonExistingProfessionnelSante() throws Exception {
        int databaseSizeBeforeUpdate = professionnelSanteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfessionnelSanteMockMvc.perform(put("/api/professionnel-santes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(professionnelSante)))
            .andExpect(status().isBadRequest());

        // Validate the ProfessionnelSante in the database
        List<ProfessionnelSante> professionnelSanteList = professionnelSanteRepository.findAll();
        assertThat(professionnelSanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProfessionnelSante() throws Exception {
        // Initialize the database
        professionnelSanteRepository.saveAndFlush(professionnelSante);

        int databaseSizeBeforeDelete = professionnelSanteRepository.findAll().size();

        // Delete the professionnelSante
        restProfessionnelSanteMockMvc.perform(delete("/api/professionnel-santes/{id}", professionnelSante.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProfessionnelSante> professionnelSanteList = professionnelSanteRepository.findAll();
        assertThat(professionnelSanteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
