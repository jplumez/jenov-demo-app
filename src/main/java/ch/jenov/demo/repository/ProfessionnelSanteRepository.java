package ch.jenov.demo.repository;

import ch.jenov.demo.domain.ProfessionnelSante;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProfessionnelSante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfessionnelSanteRepository extends JpaRepository<ProfessionnelSante, Long> {
}
