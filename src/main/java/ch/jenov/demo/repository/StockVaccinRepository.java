package ch.jenov.demo.repository;

import ch.jenov.demo.domain.StockVaccin;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StockVaccin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockVaccinRepository extends JpaRepository<StockVaccin, Long> {
}
