package ch.jenov.demo.web.rest;

import ch.jenov.demo.domain.StockVaccin;
import ch.jenov.demo.repository.StockVaccinRepository;
import ch.jenov.demo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ch.jenov.demo.domain.StockVaccin}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class StockVaccinResource {

    private final Logger log = LoggerFactory.getLogger(StockVaccinResource.class);

    private static final String ENTITY_NAME = "stockVaccin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StockVaccinRepository stockVaccinRepository;

    public StockVaccinResource(StockVaccinRepository stockVaccinRepository) {
        this.stockVaccinRepository = stockVaccinRepository;
    }

    /**
     * {@code POST  /stock-vaccins} : Create a new stockVaccin.
     *
     * @param stockVaccin the stockVaccin to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stockVaccin, or with status {@code 400 (Bad Request)} if the stockVaccin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stock-vaccins")
    public ResponseEntity<StockVaccin> createStockVaccin(@RequestBody StockVaccin stockVaccin) throws URISyntaxException {
        log.debug("REST request to save StockVaccin : {}", stockVaccin);
        if (stockVaccin.getId() != null) {
            throw new BadRequestAlertException("A new stockVaccin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockVaccin result = stockVaccinRepository.save(stockVaccin);
        return ResponseEntity.created(new URI("/api/stock-vaccins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stock-vaccins} : Updates an existing stockVaccin.
     *
     * @param stockVaccin the stockVaccin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stockVaccin,
     * or with status {@code 400 (Bad Request)} if the stockVaccin is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stockVaccin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stock-vaccins")
    public ResponseEntity<StockVaccin> updateStockVaccin(@RequestBody StockVaccin stockVaccin) throws URISyntaxException {
        log.debug("REST request to update StockVaccin : {}", stockVaccin);
        if (stockVaccin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StockVaccin result = stockVaccinRepository.save(stockVaccin);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, stockVaccin.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stock-vaccins} : get all the stockVaccins.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stockVaccins in body.
     */
    @GetMapping("/stock-vaccins")
    public List<StockVaccin> getAllStockVaccins() {
        log.debug("REST request to get all StockVaccins");
        return stockVaccinRepository.findAll();
    }

    /**
     * {@code GET  /stock-vaccins/:id} : get the "id" stockVaccin.
     *
     * @param id the id of the stockVaccin to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stockVaccin, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stock-vaccins/{id}")
    public ResponseEntity<StockVaccin> getStockVaccin(@PathVariable Long id) {
        log.debug("REST request to get StockVaccin : {}", id);
        Optional<StockVaccin> stockVaccin = stockVaccinRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(stockVaccin);
    }

    /**
     * {@code DELETE  /stock-vaccins/:id} : delete the "id" stockVaccin.
     *
     * @param id the id of the stockVaccin to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stock-vaccins/{id}")
    public ResponseEntity<Void> deleteStockVaccin(@PathVariable Long id) {
        log.debug("REST request to delete StockVaccin : {}", id);
        stockVaccinRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
