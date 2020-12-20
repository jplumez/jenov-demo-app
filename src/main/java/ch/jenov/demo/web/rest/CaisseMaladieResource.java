package ch.jenov.demo.web.rest;

import ch.jenov.demo.domain.CaisseMaladie;
import ch.jenov.demo.repository.CaisseMaladieRepository;
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
 * REST controller for managing {@link ch.jenov.demo.domain.CaisseMaladie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CaisseMaladieResource {

    private final Logger log = LoggerFactory.getLogger(CaisseMaladieResource.class);

    private static final String ENTITY_NAME = "caisseMaladie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaisseMaladieRepository caisseMaladieRepository;

    public CaisseMaladieResource(CaisseMaladieRepository caisseMaladieRepository) {
        this.caisseMaladieRepository = caisseMaladieRepository;
    }

    /**
     * {@code POST  /caisse-maladies} : Create a new caisseMaladie.
     *
     * @param caisseMaladie the caisseMaladie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caisseMaladie, or with status {@code 400 (Bad Request)} if the caisseMaladie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/caisse-maladies")
    public ResponseEntity<CaisseMaladie> createCaisseMaladie(@RequestBody CaisseMaladie caisseMaladie) throws URISyntaxException {
        log.debug("REST request to save CaisseMaladie : {}", caisseMaladie);
        if (caisseMaladie.getId() != null) {
            throw new BadRequestAlertException("A new caisseMaladie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaisseMaladie result = caisseMaladieRepository.save(caisseMaladie);
        return ResponseEntity.created(new URI("/api/caisse-maladies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /caisse-maladies} : Updates an existing caisseMaladie.
     *
     * @param caisseMaladie the caisseMaladie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caisseMaladie,
     * or with status {@code 400 (Bad Request)} if the caisseMaladie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caisseMaladie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/caisse-maladies")
    public ResponseEntity<CaisseMaladie> updateCaisseMaladie(@RequestBody CaisseMaladie caisseMaladie) throws URISyntaxException {
        log.debug("REST request to update CaisseMaladie : {}", caisseMaladie);
        if (caisseMaladie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaisseMaladie result = caisseMaladieRepository.save(caisseMaladie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caisseMaladie.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /caisse-maladies} : get all the caisseMaladies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caisseMaladies in body.
     */
    @GetMapping("/caisse-maladies")
    public List<CaisseMaladie> getAllCaisseMaladies() {
        log.debug("REST request to get all CaisseMaladies");
        return caisseMaladieRepository.findAll();
    }

    /**
     * {@code GET  /caisse-maladies/:id} : get the "id" caisseMaladie.
     *
     * @param id the id of the caisseMaladie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caisseMaladie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/caisse-maladies/{id}")
    public ResponseEntity<CaisseMaladie> getCaisseMaladie(@PathVariable Long id) {
        log.debug("REST request to get CaisseMaladie : {}", id);
        Optional<CaisseMaladie> caisseMaladie = caisseMaladieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(caisseMaladie);
    }

    /**
     * {@code DELETE  /caisse-maladies/:id} : delete the "id" caisseMaladie.
     *
     * @param id the id of the caisseMaladie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/caisse-maladies/{id}")
    public ResponseEntity<Void> deleteCaisseMaladie(@PathVariable Long id) {
        log.debug("REST request to delete CaisseMaladie : {}", id);
        caisseMaladieRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
