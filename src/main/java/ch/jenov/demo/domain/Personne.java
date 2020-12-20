package ch.jenov.demo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Personne.
 */
@Entity
@Table(name = "personne")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "no_avs", nullable = false)
    private String noAvs;

    @OneToMany(mappedBy = "personne")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Vaccination> vaccinations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Personne name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoAvs() {
        return noAvs;
    }

    public Personne noAvs(String noAvs) {
        this.noAvs = noAvs;
        return this;
    }

    public void setNoAvs(String noAvs) {
        this.noAvs = noAvs;
    }

    public Set<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public Personne vaccinations(Set<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
        return this;
    }

    public Personne addVaccination(Vaccination vaccination) {
        this.vaccinations.add(vaccination);
        vaccination.setPersonne(this);
        return this;
    }

    public Personne removeVaccination(Vaccination vaccination) {
        this.vaccinations.remove(vaccination);
        vaccination.setPersonne(null);
        return this;
    }

    public void setVaccinations(Set<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Personne)) {
            return false;
        }
        return id != null && id.equals(((Personne) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Personne{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", noAvs='" + getNoAvs() + "'" +
            "}";
    }
}
