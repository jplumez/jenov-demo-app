package ch.jenov.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Vaccination.
 */
@Entity
@Table(name = "vaccination")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vaccination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_vaccin")
    private Instant dateVaccin;

    @ManyToOne
    @JsonIgnoreProperties(value = "vaccinations", allowSetters = true)
    private Personne personne;

    @ManyToOne
    @JsonIgnoreProperties(value = "vaccinations", allowSetters = true)
    private StockVaccin stockVaccin;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateVaccin() {
        return dateVaccin;
    }

    public Vaccination dateVaccin(Instant dateVaccin) {
        this.dateVaccin = dateVaccin;
        return this;
    }

    public void setDateVaccin(Instant dateVaccin) {
        this.dateVaccin = dateVaccin;
    }

    public Personne getPersonne() {
        return personne;
    }

    public Vaccination personne(Personne personne) {
        this.personne = personne;
        return this;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public StockVaccin getStockVaccin() {
        return stockVaccin;
    }

    public Vaccination stockVaccin(StockVaccin stockVaccin) {
        this.stockVaccin = stockVaccin;
        return this;
    }

    public void setStockVaccin(StockVaccin stockVaccin) {
        this.stockVaccin = stockVaccin;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vaccination)) {
            return false;
        }
        return id != null && id.equals(((Vaccination) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vaccination{" +
            "id=" + getId() +
            ", dateVaccin='" + getDateVaccin() + "'" +
            "}";
    }
}
