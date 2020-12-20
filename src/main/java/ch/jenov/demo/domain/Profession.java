package ch.jenov.demo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Profession.
 */
@Entity
@Table(name = "profession")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Profession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "profession")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<ProfessionnelSante> professionnelSantes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Profession nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<ProfessionnelSante> getProfessionnelSantes() {
        return professionnelSantes;
    }

    public Profession professionnelSantes(Set<ProfessionnelSante> professionnelSantes) {
        this.professionnelSantes = professionnelSantes;
        return this;
    }

    public Profession addProfessionnelSante(ProfessionnelSante professionnelSante) {
        this.professionnelSantes.add(professionnelSante);
        professionnelSante.setProfession(this);
        return this;
    }

    public Profession removeProfessionnelSante(ProfessionnelSante professionnelSante) {
        this.professionnelSantes.remove(professionnelSante);
        professionnelSante.setProfession(null);
        return this;
    }

    public void setProfessionnelSantes(Set<ProfessionnelSante> professionnelSantes) {
        this.professionnelSantes = professionnelSantes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profession)) {
            return false;
        }
        return id != null && id.equals(((Profession) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Profession{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
