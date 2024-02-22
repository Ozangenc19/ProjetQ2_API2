package Informatique.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe Employe de gestion de projet
 * @author Ozan Genc
 * @version 1.0
 */
public class Employe {

    /**
     * création d'id pour auto incrémentation
     */
    private static int id = 1;

    /**
     * identifiant, numéro de l'employe
     */
    protected int id_employe;

    /**
     * matricule de l'employe
     */
    protected String matricule;

    /**
     * nom de l'employe
     */
    protected String nom;

    /**
     * prénom de l'employe
     */
    protected String prenom;

    /**
     * téléphone de l'employe
     */
    protected String tel;

    /**
     * mail de l'employe
     */
    protected String mail;

    /**
     * liste des projets
     */

    protected List<Projet> projets = new ArrayList<>();

    /**
     * liste des compétences
     */
    protected List<Competence> ListeCompt = new ArrayList<>();

    /**
     * Constructeur paramétré de la classe Employe
     *
     * @param matricule matricule de l'employe
     * @param nom nom de l'employe
     * @param prenom prénom de l'employe
     * @param tel téléphone de l'employe
     * @param mail mail de l'employe
     */
    public Employe(String matricule, String nom, String prenom, String tel, String mail) {
        this.id_employe = id++;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;

    }

    /**
     * retourne la liste de discipline et niveau pour une compétence
     * @return liste DisciplineEtNiveau
     */
    public List<Competence> listeDisciplinesEtNiveau() {
        return ListeCompt;
    }

    /**
     * setter de la liste compétence
     * @param listeCompt listeCompt
     */
    public void setListeCompt(List<Competence> listeCompt) {
        ListeCompt = listeCompt;
    }

    /**
     * getter du numéro de l'employe
     * @return numéro de l'employe
     */
    public int getId_employe() {
        return id_employe;
    }

    /**
     * setter du numéro de l'employe
     * @param id_employe numéro de l'employe
     */
    public void setId_employe(int id_employe) {
        this.id_employe = id_employe;
    }

    /**
     * getter du matricule de l'employe
     * @return matricule de l'employe
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * setter du matricule de l'employe
     * @param matricule matricule de l'employe
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * getter du nom de l'employe
     * @return nom de l'employe
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter du nom de l'employe
     * @param nom nom de l'employe
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter du prénom de l'employe
     * @return prénom de l'employe
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * setter du prénom de l'employe
     * @param prenom prénom de l'employe
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * getter du téléphone de l'employe
     * @return téléphone de l'employe
     */
    public String getTel() {
        return tel;
    }

    /**
     * setter du téléphone de l'employe
     * @param tel téléphone de l'employe
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * getter du mail de l'employe
     * @return mail de l'employe
     */
    public String getMail() {
        return mail;
    }

    /**
     * setter du mail de l'employe
     * @param mail mail de l'employe
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * getter de la liste des projets
     * @return projet liste des projets
     */
    public List<Projet> getProjets() {
        return projets;
    }

    /**
     * setter de la liste des projets
     * @param projets liste des projets
     */
    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    /**
     * Ajout d'une discipline avec un niveau
     *
     * @param discipline
     * @param niveau
     */
    public void addDiscipline(Disciplines discipline, int niveau){
       Competence competence = new Competence(discipline, niveau);
       ListeCompt.add(competence);
    }

    /**
     * Modification du niveau d'un discipline
     *
     * @param discipline
     * @param niveau

     */
    public boolean modifDiscipline(Disciplines discipline,int niveau){
        for (Competence c : ListeCompt){
            if (ListeCompt.get(ListeCompt.indexOf(c)).disciplines.equals(discipline)){
                ListeCompt.get(ListeCompt.indexOf(c)).niveau = niveau;
            }
        }
        return false;
    }


    /**
     * Suppression d'une discipline
     *
     * @param discipline
     */
    public void suppDiscipline(Disciplines discipline){
        ListeCompt.remove(discipline);
    }


    /**
     * convertion de l'objet Employe en une chaîne de caractères représentative
     * @return les informations de l'employe
     */
    @Override
    public String toString() {
        return "Employe{" +
                "id_employe=" + id_employe +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", projets=" + projets +
                ", ListeCompt=" + ListeCompt +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employe employe = (Employe) o;
        return id_employe == employe.id_employe && Objects.equals(matricule, employe.matricule) && Objects.equals(nom, employe.nom) && Objects.equals(prenom, employe.prenom) && Objects.equals(tel, employe.tel) && Objects.equals(mail, employe.mail) && Objects.equals(projets, employe.projets) && Objects.equals(ListeCompt, employe.ListeCompt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_employe, matricule, nom, prenom, tel, mail, projets, ListeCompt);
    }
}
