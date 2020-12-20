package ch.jenov.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A StockVaccin.
 */
@Entity
@Table(name = "stock_vaccin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StockVaccin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock_initial")
    private Long stockInitial;

    @Column(name = "stock_actuel")
    private Long stockActuel;

    @OneToMany(mappedBy = "stockVaccin")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Vaccination> vaccinations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "stockVaccins", allowSetters = true)
    private Centre centre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockInitial() {
        return stockInitial;
    }

    public StockVaccin stockInitial(Long stockInitial) {
        this.stockInitial = stockInitial;
        return this;
    }

    public void setStockInitial(Long stockInitial) {
        this.stockInitial = stockInitial;
    }

    public Long getStockActuel() {
        return stockActuel;
    }

    public StockVaccin stockActuel(Long stockActuel) {
        this.stockActuel = stockActuel;
        return this;
    }

    public void setStockActuel(Long stockActuel) {
        this.stockActuel = stockActuel;
    }

    public Set<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public StockVaccin vaccinations(Set<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
        return this;
    }

    public StockVaccin addVaccination(Vaccination vaccination) {
        this.vaccinations.add(vaccination);
        vaccination.setStockVaccin(this);
        return this;
    }

    public StockVaccin removeVaccination(Vaccination vaccination) {
        this.vaccinations.remove(vaccination);
        vaccination.setStockVaccin(null);
        return this;
    }

    public void setVaccinations(Set<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public Centre getCentre() {
        return centre;
    }

    public StockVaccin centre(Centre centre) {
        this.centre = centre;
        return this;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StockVaccin)) {
            return false;
        }
        return id != null && id.equals(((StockVaccin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockVaccin{" +
            "id=" + getId() +
            ", stockInitial=" + getStockInitial() +
            ", stockActuel=" + getStockActuel() +
            "}";
    }
}
