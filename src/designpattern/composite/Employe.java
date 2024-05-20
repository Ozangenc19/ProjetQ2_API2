package designpattern.composite;

import Informatique.metier.Disciplines;
import Informatique.metier.Projet;

import java.util.ArrayList;
import java.util.List;


/**
 * classe Employe de gestion de projet
 *
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
     * liste du niveau pour chaque discipline
     */
    protected List<ListeDisciplinesEtNiveau> ListDiscNiveau = new ArrayList<>();


    /**
     * Constructeur paramétré de la classe Employe
     *
     * @param id        id de l'employe
     * @param matricule matricule de l'employe
     * @param nom       nom de l'employe
     * @param prenom    prénom de l'employe
     * @param tel       téléphone de l'employe
     * @param mail      mail de l'employe
     */
    public Employe(int id, String matricule, String nom, String prenom, String tel, String mail) {
        this.id_employe = id++;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;

    }

    /**
     * Constructeur paramétré de la classe Employe
     *
     * @param matricule matricule de l'employe
     * @param nom       nom de l'employe
     * @param prenom    prénom de l'employe
     * @param tel       téléphone de l'employe
     * @param mail      mail de l'employe
     */
    public Employe(String matricule, String nom, String prenom, String tel, String mail) {
        this.id_employe = id++;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.mail = mail;
    }



    public Employe(EmployeBuilder eb){
        this.id_employe= id++;
        this.matricule = eb.matricule;
        this.nom = eb.nom;
        this.prenom = eb.prenom;
        this.tel = eb.tel;
        this.mail = eb.mail;
    }
    /**
     * setter de la liste compétence
     *
     * @param listeCompt listeCompt
     */
    public void setListeCompt(List<Competence> listeCompt) {
        ListeCompt = listeCompt;
    }

    /**
     * getter du numéro de l'employe
     *
     * @return numéro de l'employe
     */
    public int getId_employe() {
        return id_employe;
    }

    /**
     * setter du numéro de l'employe
     *
     * @param id_employe numéro de l'employe
     */
    public void setId_employe(int id_employe) {
        this.id_employe = id_employe;
    }

    /**
     * getter du matricule de l'employe
     *
     * @return matricule de l'employe
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * setter du matricule de l'employe
     *
     * @param matricule matricule de l'employe
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * getter du nom de l'employe
     *
     * @return nom de l'employe
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter du nom de l'employe
     *
     * @param nom nom de l'employe
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter du prénom de l'employe
     *
     * @return prénom de l'employe
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * setter du prénom de l'employe
     *
     * @param prenom prénom de l'employe
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * getter du téléphone de l'employe
     *
     * @return téléphone de l'employe
     */
    public String getTel() {
        return tel;
    }

    /**
     * setter du téléphone de l'employe
     *
     * @param tel téléphone de l'employe
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * getter du mail de l'employe
     *
     * @return mail de l'employe
     */
    public String getMail() {
        return mail;
    }

    /**
     * setter du mail de l'employe
     *
     * @param mail mail de l'employe
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * getter de la liste des projets
     *
     * @return projet liste des projets
     */
    public List<Projet> getProjets() {
        return projets;
    }

    /**
     * setter de la liste des projets
     *
     * @param projets liste des projets
     */
    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }
    /**
     * Retourne une liste des disciplines associées à leur niveau de compétence.
     * Cette méthode parcourt la liste des compétences de l'employé et crée une liste de paires Discipline-Niveau.
     * Note: Avant de retourner la liste, elle efface d'abord la liste actuelle pour éviter les duplications.
     *
     * @return Une liste de paires Discipline-Niveau représentant les disciplines et leur niveau de compétence.
     */
    public List<ListeDisciplinesEtNiveau> listeDisciplinesEtNiveau() {
        for (Competence c : ListeCompt) {
            ListDiscNiveau.add(new ListeDisciplinesEtNiveau(c.getDisciplines(), c.getNiveau()));
        }
        return ListDiscNiveau;
    }

    /**
     * Ajout d'une discipline avec un niveau
     *
     * @param discipline
     * @param niveau
     */
    public void addDiscipline(Disciplines discipline, int niveau) {
        Competence c = new Competence(discipline, niveau);
        ListeCompt.add(c);
    }

    /**
     * Modification du niveau d'un discipline
     *
     * @param discipline
     * @param niveau
     */

    public boolean modifDiscipline(Disciplines discipline, int niveau) {
        for (Competence c : ListeCompt) {
            if (c.getDisciplines().equals(discipline)) {
                c.setNiveau(niveau);
                return true;
            }
        }
        return false; // La discipline n'a pas été trouvée et modifiée
    }

    /**
     * Suppression d'une discipline
     *
     * @param discipline
     */

    public boolean supprimerDiscipline(Disciplines discipline) {
        for (Competence c : ListeCompt) {
            if (c.getDisciplines().equals(discipline)) {
                return ListeCompt.remove(c);

            }
        }
        return false; // La discipline n'a pas été trouvée et supprimée
    }


    @Override
    public String toString() {
        return "Employe{" +
                "n° =" + id_employe +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

    public static class EmployeBuilder {
        protected int id_empl;
        protected String matricule;
        protected String nom;
        protected String prenom;
        protected String tel;
        protected String mail;

        public EmployeBuilder setId_empl(int id_empl) {
            this.id_empl = id_empl;
            return this;
        }

        public EmployeBuilder setMatricule(String matricule) {
            this.matricule = matricule;
            return this;
        }

        public EmployeBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public EmployeBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public EmployeBuilder setTel(String tel) {
            this.tel = tel;
            return this;
        }

        public EmployeBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public Employe Build() throws Exception {
            if(matricule == null || nom == null || prenom == null) throw new Exception("Informations manquantes");
            return new Employe(this);
        }
    }
}