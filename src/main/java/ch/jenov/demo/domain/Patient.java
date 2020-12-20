package ch.jenov.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "no_avs", nullable = false)
    private String noAvs;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "npa_localite")
    private String npaLocalite;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "numero_assure")
    private String numeroAssure;

    @OneToMany(mappedBy = "personne")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Vaccination> vaccinations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "patients", allowSetters = true)
    private CaisseMaladie caisseMaladie;

    @ManyToOne
    @JsonIgnoreProperties(value = "patients", allowSetters = true)
    private ProfessionnelSante medecinTraitant;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoAvs() {
        return noAvs;
    }

    public Patient noAvs(String noAvs) {
        this.noAvs = noAvs;
        return this;
    }

    public void setNoAvs(String noAvs) {
        this.noAvs = noAvs;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public Patient dateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public Patient nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Patient prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Patient adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNpaLocalite() {
        return npaLocalite;
    }

    public Patient npaLocalite(String npaLocalite) {
        this.npaLocalite = npaLocalite;
        return this;
    }

    public void setNpaLocalite(String npaLocalite) {
        this.npaLocalite = npaLocalite;
    }

    public String getTelephone() {
        return telephone;
    }

    public Patient telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public Patient email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroAssure() {
        return numeroAssure;
    }

    public Patient numeroAssure(String numeroAssure) {
        this.numeroAssure = numeroAssure;
        return this;
    }

    public void setNumeroAssure(String numeroAssure) {
        this.numeroAssure = numeroAssure;
    }

    public Set<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public Patient vaccinations(Set<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
        return this;
    }

    public Patient addVaccination(Vaccination vaccination) {
        this.vaccinations.add(vaccination);
        vaccination.setPersonne(this);
        return this;
    }

    public Patient removeVaccination(Vaccination vaccination) {
        this.vaccinations.remove(vaccination);
        vaccination.setPersonne(null);
        return this;
    }

    public void setVaccinations(Set<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public CaisseMaladie getCaisseMaladie() {
        return caisseMaladie;
    }

    public Patient caisseMaladie(CaisseMaladie caisseMaladie) {
        this.caisseMaladie = caisseMaladie;
        return this;
    }

    public void setCaisseMaladie(CaisseMaladie caisseMaladie) {
        this.caisseMaladie = caisseMaladie;
    }

    public ProfessionnelSante getMedecinTraitant() {
        return medecinTraitant;
    }

    public Patient medecinTraitant(ProfessionnelSante professionnelSante) {
        this.medecinTraitant = professionnelSante;
        return this;
    }

    public void setMedecinTraitant(ProfessionnelSante professionnelSante) {
        this.medecinTraitant = professionnelSante;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", noAvs='" + getNoAvs() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", npaLocalite='" + getNpaLocalite() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", numeroAssure='" + getNumeroAssure() + "'" +
            "}";
    }
}
