package ch.jenov.demo.web.rest;

import ch.jenov.demo.JenovDemoApp;
import ch.jenov.demo.domain.CaisseMaladie;
import ch.jenov.demo.repository.CaisseMaladieRepository;

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

/**
 * Integration tests for the {@link CaisseMaladieResource} REST controller.
 */
@SpringBootTest(classes = JenovDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CaisseMaladieResourceIT {

    private static final String DEFAULT_NUMERO_CAISSE_MALADIE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_CAISSE_MALADIE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_NPA_LOCALITE = "AAAAAAAAAA";
    private static final String UPDATED_NPA_LOCALITE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    @Autowired
    private CaisseMaladieRepository caisseMaladieRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCaisseMaladieMockMvc;

    private CaisseMaladie caisseMaladie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaisseMaladie createEntity(EntityManager em) {
        CaisseMaladie caisseMaladie = new CaisseMaladie()
            .numeroCaisseMaladie(DEFAULT_NUMERO_CAISSE_MALADIE)
            .nom(DEFAULT_NOM)
            .adresse(DEFAULT_ADRESSE)
            .npaLocalite(DEFAULT_NPA_LOCALITE)
            .telephone(DEFAULT_TELEPHONE)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL);
        return caisseMaladie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CaisseMaladie createUpdatedEntity(EntityManager em) {
        CaisseMaladie caisseMaladie = new CaisseMaladie()
            .numeroCaisseMaladie(UPDATED_NUMERO_CAISSE_MALADIE)
            .nom(UPDATED_NOM)
            .adresse(UPDATED_ADRESSE)
            .npaLocalite(UPDATED_NPA_LOCALITE)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL);
        return caisseMaladie;
    }

    @BeforeEach
    public void initTest() {
        caisseMaladie = createEntity(em);
    }

    @Test
    @Transactional
    public void createCaisseMaladie() throws Exception {
        int databaseSizeBeforeCreate = caisseMaladieRepository.findAll().size();
        // Create the CaisseMaladie
        restCaisseMaladieMockMvc.perform(post("/api/caisse-maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caisseMaladie)))
            .andExpect(status().isCreated());

        // Validate the CaisseMaladie in the database
        List<CaisseMaladie> caisseMaladieList = caisseMaladieRepository.findAll();
        assertThat(caisseMaladieList).hasSize(databaseSizeBeforeCreate + 1);
        CaisseMaladie testCaisseMaladie = caisseMaladieList.get(caisseMaladieList.size() - 1);
        assertThat(testCaisseMaladie.getNumeroCaisseMaladie()).isEqualTo(DEFAULT_NUMERO_CAISSE_MALADIE);
        assertThat(testCaisseMaladie.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testCaisseMaladie.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testCaisseMaladie.getNpaLocalite()).isEqualTo(DEFAULT_NPA_LOCALITE);
        assertThat(testCaisseMaladie.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testCaisseMaladie.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testCaisseMaladie.getEmail()).isEqualTo(DEFAULT_EMAIL);
    }

    @Test
    @Transactional
    public void createCaisseMaladieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = caisseMaladieRepository.findAll().size();

        // Create the CaisseMaladie with an existing ID
        caisseMaladie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCaisseMaladieMockMvc.perform(post("/api/caisse-maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caisseMaladie)))
            .andExpect(status().isBadRequest());

        // Validate the CaisseMaladie in the database
        List<CaisseMaladie> caisseMaladieList = caisseMaladieRepository.findAll();
        assertThat(caisseMaladieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCaisseMaladies() throws Exception {
        // Initialize the database
        caisseMaladieRepository.saveAndFlush(caisseMaladie);

        // Get all the caisseMaladieList
        restCaisseMaladieMockMvc.perform(get("/api/caisse-maladies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(caisseMaladie.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroCaisseMaladie").value(hasItem(DEFAULT_NUMERO_CAISSE_MALADIE)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].npaLocalite").value(hasItem(DEFAULT_NPA_LOCALITE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)));
    }
    
    @Test
    @Transactional
    public void getCaisseMaladie() throws Exception {
        // Initialize the database
        caisseMaladieRepository.saveAndFlush(caisseMaladie);

        // Get the caisseMaladie
        restCaisseMaladieMockMvc.perform(get("/api/caisse-maladies/{id}", caisseMaladie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(caisseMaladie.getId().intValue()))
            .andExpect(jsonPath("$.numeroCaisseMaladie").value(DEFAULT_NUMERO_CAISSE_MALADIE))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.npaLocalite").value(DEFAULT_NPA_LOCALITE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL));
    }
    @Test
    @Transactional
    public void getNonExistingCaisseMaladie() throws Exception {
        // Get the caisseMaladie
        restCaisseMaladieMockMvc.perform(get("/api/caisse-maladies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCaisseMaladie() throws Exception {
        // Initialize the database
        caisseMaladieRepository.saveAndFlush(caisseMaladie);

        int databaseSizeBeforeUpdate = caisseMaladieRepository.findAll().size();

        // Update the caisseMaladie
        CaisseMaladie updatedCaisseMaladie = caisseMaladieRepository.findById(caisseMaladie.getId()).get();
        // Disconnect from session so that the updates on updatedCaisseMaladie are not directly saved in db
        em.detach(updatedCaisseMaladie);
        updatedCaisseMaladie
            .numeroCaisseMaladie(UPDATED_NUMERO_CAISSE_MALADIE)
            .nom(UPDATED_NOM)
            .adresse(UPDATED_ADRESSE)
            .npaLocalite(UPDATED_NPA_LOCALITE)
            .telephone(UPDATED_TELEPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL);

        restCaisseMaladieMockMvc.perform(put("/api/caisse-maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCaisseMaladie)))
            .andExpect(status().isOk());

        // Validate the CaisseMaladie in the database
        List<CaisseMaladie> caisseMaladieList = caisseMaladieRepository.findAll();
        assertThat(caisseMaladieList).hasSize(databaseSizeBeforeUpdate);
        CaisseMaladie testCaisseMaladie = caisseMaladieList.get(caisseMaladieList.size() - 1);
        assertThat(testCaisseMaladie.getNumeroCaisseMaladie()).isEqualTo(UPDATED_NUMERO_CAISSE_MALADIE);
        assertThat(testCaisseMaladie.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testCaisseMaladie.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testCaisseMaladie.getNpaLocalite()).isEqualTo(UPDATED_NPA_LOCALITE);
        assertThat(testCaisseMaladie.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testCaisseMaladie.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testCaisseMaladie.getEmail()).isEqualTo(UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void updateNonExistingCaisseMaladie() throws Exception {
        int databaseSizeBeforeUpdate = caisseMaladieRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCaisseMaladieMockMvc.perform(put("/api/caisse-maladies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(caisseMaladie)))
            .andExpect(status().isBadRequest());

        // Validate the CaisseMaladie in the database
        List<CaisseMaladie> caisseMaladieList = caisseMaladieRepository.findAll();
        assertThat(caisseMaladieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCaisseMaladie() throws Exception {
        // Initialize the database
        caisseMaladieRepository.saveAndFlush(caisseMaladie);

        int databaseSizeBeforeDelete = caisseMaladieRepository.findAll().size();

        // Delete the caisseMaladie
        restCaisseMaladieMockMvc.perform(delete("/api/caisse-maladies/{id}", caisseMaladie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CaisseMaladie> caisseMaladieList = caisseMaladieRepository.findAll();
        assertThat(caisseMaladieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
