package ch.jenov.demo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CaisseMaladie.
 */
@Entity
@Table(name = "caisse_maladie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CaisseMaladie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_caisse_maladie")
    private String numeroCaisseMaladie;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "npa_localite")
    private String npaLocalite;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "caisseMaladie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Patient> patients = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCaisseMaladie() {
        return numeroCaisseMaladie;
    }

    public CaisseMaladie numeroCaisseMaladie(String numeroCaisseMaladie) {
        this.numeroCaisseMaladie = numeroCaisseMaladie;
        return this;
    }

    public void setNumeroCaisseMaladie(String numeroCaisseMaladie) {
        this.numeroCaisseMaladie = numeroCaisseMaladie;
    }

    public String getNom() {
        return nom;
    }

    public CaisseMaladie nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public CaisseMaladie adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNpaLocalite() {
        return npaLocalite;
    }

    public CaisseMaladie npaLocalite(String npaLocalite) {
        this.npaLocalite = npaLocalite;
        return this;
    }

    public void setNpaLocalite(String npaLocalite) {
        this.npaLocalite = npaLocalite;
    }

    public String getTelephone() {
        return telephone;
    }

    public CaisseMaladie telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public CaisseMaladie fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public CaisseMaladie email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public CaisseMaladie patients(Set<Patient> patients) {
        this.patients = patients;
        return this;
    }

    public CaisseMaladie addPatient(Patient patient) {
        this.patients.add(patient);
        patient.setCaisseMaladie(this);
        return this;
    }

    public CaisseMaladie removePatient(Patient patient) {
        this.patients.remove(patient);
        patient.setCaisseMaladie(null);
        return this;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaisseMaladie)) {
            return false;
        }
        return id != null && id.equals(((CaisseMaladie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CaisseMaladie{" +
            "id=" + getId() +
            ", numeroCaisseMaladie='" + getNumeroCaisseMaladie() + "'" +
            ", nom='" + getNom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", npaLocalite='" + getNpaLocalite() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
