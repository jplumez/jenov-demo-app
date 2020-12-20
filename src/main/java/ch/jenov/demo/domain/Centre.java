package ch.jenov.demo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Centre.
 */
@Entity
@Table(name = "centre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Centre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "localite", nullable = false)
    private String localite;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "centre")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<StockVaccin> stockVaccins = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Centre code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocalite() {
        return localite;
    }

    public Centre localite(String localite) {
        this.localite = localite;
        return this;
    }

    public void setLocalite(String localite) {
        this.localite = localite;
    }

    public String getDescription() {
        return description;
    }

    public Centre description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StockVaccin> getStockVaccins() {
        return stockVaccins;
    }

    public Centre stockVaccins(Set<StockVaccin> stockVaccins) {
        this.stockVaccins = stockVaccins;
        return this;
    }

    public Centre addStockVaccin(StockVaccin stockVaccin) {
        this.stockVaccins.add(stockVaccin);
        stockVaccin.setCentre(this);
        return this;
    }

    public Centre removeStockVaccin(StockVaccin stockVaccin) {
        this.stockVaccins.remove(stockVaccin);
        stockVaccin.setCentre(null);
        return this;
    }

    public void setStockVaccins(Set<StockVaccin> stockVaccins) {
        this.stockVaccins = stockVaccins;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Centre)) {
            return false;
        }
        return id != null && id.equals(((Centre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Centre{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", localite='" + getLocalite() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
