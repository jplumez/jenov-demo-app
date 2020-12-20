package ch.jenov.demo.repository;

import ch.jenov.demo.domain.CaisseMaladie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CaisseMaladie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaisseMaladieRepository extends JpaRepository<CaisseMaladie, Long> {
}
