package designpattern.builder;

import Informatique.metier.Competence;
import Informatique.metier.Disciplines;
import Informatique.metier.ListeDisciplinesEtNiveau;
import Informatique.metier.Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe Employe de gestion de projet
 *
 * @author Ozan Genc
 * @version 1.0
 */
public class Employe {
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
     * constructeur paramétré
     * @param eb objet de la classe EmployeBuilder contenant les informations d'initialisation
     */

    public Employe(EmployeBuilder eb){
        this.id_employe = eb.idemploye;
        this.matricule = eb.matricule;
        this.nom = eb.nom;
        this.prenom = eb.prenom;
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


    @Override
    public String toString() {
        return "Employe{" +
                "id_employe=" + id_employe +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }



    public static class EmployeBuilder{

        /**
         * identifiant unique de l'employé
         */
        protected int idemploye;
        /**
         * matricule de l'employé
         */
        protected String matricule;
        /**
         * nom de l'employé
         */
        protected String nom;
        /**
         * prenom de l'employé
         */
        protected String prenom;

        public EmployeBuilder setIdemploye(int idemploye) {
            this.idemploye = idemploye;
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

        /*
        public Employe build() throws Exception{
            if (idemploye<=0 || matricule==null || nom==null || prenom==null ) throw new Exception("informations de constuctions incomplètes"){
             return new Employe(this);
            }
        }
        */

    }




}
