package Informatique;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employe {
    protected int id_employe;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String tel;
    protected String mail;

    public List<Projet>projets = new ArrayList<>();

    public Employe(int id_employe, String matricule, String nom, String prenom, String tel, String mail, List<Projet> projets) {
        this.id_employe = id_employe;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;
        this.projets = projets;
    }

    public int getId_employe() {
        return id_employe;
    }

    public void setId_employe(int id_employe) {
        this.id_employe = id_employe;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return id_employe == employe.id_employe && Objects.equals(matricule, employe.matricule) && Objects.equals(nom, employe.nom) && Objects.equals(prenom, employe.prenom) && Objects.equals(tel, employe.tel) && Objects.equals(mail, employe.mail) && Objects.equals(projets, employe.projets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_employe, matricule, nom, prenom, tel, mail, projets);
    }

}
