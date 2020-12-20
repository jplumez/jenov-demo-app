package ch.jenov.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import ch.jenov.demo.domain.enumeration.Sexe;

/**
 * A ProfessionnelSante.
 */
@Entity
@Table(name = "professionnel_sante")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProfessionnelSante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ide")
    private String ide;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private Sexe sexe;

    @Column(name = "rue")
    private String rue;

    @OneToMany(mappedBy = "medecinTraitant")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Patient> patients = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "professionnelSantes", allowSetters = true)
    private Localite localite;

    @ManyToOne
    @JsonIgnoreProperties(value = "professionnelSantes", allowSetters = true)
    private Profession profession;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIde() {
        return ide;
    }

    public ProfessionnelSante ide(String ide) {
        this.ide = ide;
        return this;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public String getPrenom() {
        return prenom;
    }

    public ProfessionnelSante prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public ProfessionnelSante nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public ProfessionnelSante sexe(Sexe sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getRue() {
        return rue;
    }

    public ProfessionnelSante rue(String rue) {
        this.rue = rue;
        return this;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public ProfessionnelSante patients(Set<Patient> patients) {
        this.patients = patients;
        return this;
    }

    public ProfessionnelSante addPatient(Patient patient) {
        this.patients.add(patient);
        patient.setMedecinTraitant(this);
        return this;
    }

    public ProfessionnelSante removePatient(Patient patient) {
        this.patients.remove(patient);
        patient.setMedecinTraitant(null);
        return this;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Localite getLocalite() {
        return localite;
    }

    public ProfessionnelSante localite(Localite localite) {
        this.localite = localite;
        return this;
    }

    public void setLocalite(Localite localite) {
        this.localite = localite;
    }

    public Profession getProfession() {
        return profession;
    }

    public ProfessionnelSante profession(Profession profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfessionnelSante)) {
            return false;
        }
        return id != null && id.equals(((ProfessionnelSante) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfessionnelSante{" +
            "id=" + getId() +
            ", ide='" + getIde() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", rue='" + getRue() + "'" +
            "}";
    }
}
