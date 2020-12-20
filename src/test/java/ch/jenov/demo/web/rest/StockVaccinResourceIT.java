package ch.jenov.demo.web.rest;

import ch.jenov.demo.JenovDemoApp;
import ch.jenov.demo.domain.StockVaccin;
import ch.jenov.demo.repository.StockVaccinRepository;

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
 * Integration tests for the {@link StockVaccinResource} REST controller.
 */
@SpringBootTest(classes = JenovDemoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class StockVaccinResourceIT {

    private static final Long DEFAULT_STOCK_INITIAL = 1L;
    private static final Long UPDATED_STOCK_INITIAL = 2L;

    private static final Long DEFAULT_STOCK_ACTUEL = 1L;
    private static final Long UPDATED_STOCK_ACTUEL = 2L;

    @Autowired
    private StockVaccinRepository stockVaccinRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStockVaccinMockMvc;

    private StockVaccin stockVaccin;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockVaccin createEntity(EntityManager em) {
        StockVaccin stockVaccin = new StockVaccin()
            .stockInitial(DEFAULT_STOCK_INITIAL)
            .stockActuel(DEFAULT_STOCK_ACTUEL);
        return stockVaccin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StockVaccin createUpdatedEntity(EntityManager em) {
        StockVaccin stockVaccin = new StockVaccin()
            .stockInitial(UPDATED_STOCK_INITIAL)
            .stockActuel(UPDATED_STOCK_ACTUEL);
        return stockVaccin;
    }

    @BeforeEach
    public void initTest() {
        stockVaccin = createEntity(em);
    }

    @Test
    @Transactional
    public void createStockVaccin() throws Exception {
        int databaseSizeBeforeCreate = stockVaccinRepository.findAll().size();
        // Create the StockVaccin
        restStockVaccinMockMvc.perform(post("/api/stock-vaccins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockVaccin)))
            .andExpect(status().isCreated());

        // Validate the StockVaccin in the database
        List<StockVaccin> stockVaccinList = stockVaccinRepository.findAll();
        assertThat(stockVaccinList).hasSize(databaseSizeBeforeCreate + 1);
        StockVaccin testStockVaccin = stockVaccinList.get(stockVaccinList.size() - 1);
        assertThat(testStockVaccin.getStockInitial()).isEqualTo(DEFAULT_STOCK_INITIAL);
        assertThat(testStockVaccin.getStockActuel()).isEqualTo(DEFAULT_STOCK_ACTUEL);
    }

    @Test
    @Transactional
    public void createStockVaccinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stockVaccinRepository.findAll().size();

        // Create the StockVaccin with an existing ID
        stockVaccin.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStockVaccinMockMvc.perform(post("/api/stock-vaccins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockVaccin)))
            .andExpect(status().isBadRequest());

        // Validate the StockVaccin in the database
        List<StockVaccin> stockVaccinList = stockVaccinRepository.findAll();
        assertThat(stockVaccinList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStockVaccins() throws Exception {
        // Initialize the database
        stockVaccinRepository.saveAndFlush(stockVaccin);

        // Get all the stockVaccinList
        restStockVaccinMockMvc.perform(get("/api/stock-vaccins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stockVaccin.getId().intValue())))
            .andExpect(jsonPath("$.[*].stockInitial").value(hasItem(DEFAULT_STOCK_INITIAL.intValue())))
            .andExpect(jsonPath("$.[*].stockActuel").value(hasItem(DEFAULT_STOCK_ACTUEL.intValue())));
    }
    
    @Test
    @Transactional
    public void getStockVaccin() throws Exception {
        // Initialize the database
        stockVaccinRepository.saveAndFlush(stockVaccin);

        // Get the stockVaccin
        restStockVaccinMockMvc.perform(get("/api/stock-vaccins/{id}", stockVaccin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stockVaccin.getId().intValue()))
            .andExpect(jsonPath("$.stockInitial").value(DEFAULT_STOCK_INITIAL.intValue()))
            .andExpect(jsonPath("$.stockActuel").value(DEFAULT_STOCK_ACTUEL.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingStockVaccin() throws Exception {
        // Get the stockVaccin
        restStockVaccinMockMvc.perform(get("/api/stock-vaccins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStockVaccin() throws Exception {
        // Initialize the database
        stockVaccinRepository.saveAndFlush(stockVaccin);

        int databaseSizeBeforeUpdate = stockVaccinRepository.findAll().size();

        // Update the stockVaccin
        StockVaccin updatedStockVaccin = stockVaccinRepository.findById(stockVaccin.getId()).get();
        // Disconnect from session so that the updates on updatedStockVaccin are not directly saved in db
        em.detach(updatedStockVaccin);
        updatedStockVaccin
            .stockInitial(UPDATED_STOCK_INITIAL)
            .stockActuel(UPDATED_STOCK_ACTUEL);

        restStockVaccinMockMvc.perform(put("/api/stock-vaccins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStockVaccin)))
            .andExpect(status().isOk());

        // Validate the StockVaccin in the database
        List<StockVaccin> stockVaccinList = stockVaccinRepository.findAll();
        assertThat(stockVaccinList).hasSize(databaseSizeBeforeUpdate);
        StockVaccin testStockVaccin = stockVaccinList.get(stockVaccinList.size() - 1);
        assertThat(testStockVaccin.getStockInitial()).isEqualTo(UPDATED_STOCK_INITIAL);
        assertThat(testStockVaccin.getStockActuel()).isEqualTo(UPDATED_STOCK_ACTUEL);
    }

    @Test
    @Transactional
    public void updateNonExistingStockVaccin() throws Exception {
        int databaseSizeBeforeUpdate = stockVaccinRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStockVaccinMockMvc.perform(put("/api/stock-vaccins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stockVaccin)))
            .andExpect(status().isBadRequest());

        // Validate the StockVaccin in the database
        List<StockVaccin> stockVaccinList = stockVaccinRepository.findAll();
        assertThat(stockVaccinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStockVaccin() throws Exception {
        // Initialize the database
        stockVaccinRepository.saveAndFlush(stockVaccin);

        int databaseSizeBeforeDelete = stockVaccinRepository.findAll().size();

        // Delete the stockVaccin
        restStockVaccinMockMvc.perform(delete("/api/stock-vaccins/{id}", stockVaccin.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StockVaccin> stockVaccinList = stockVaccinRepository.findAll();
        assertThat(stockVaccinList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
