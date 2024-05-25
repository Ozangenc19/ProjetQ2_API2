package Informatique.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe Competence de gestion de projet
 * @author Ozan Genc
 * @version 1.0
 */
public class Competence {

    /**
     * création d'un id pour auto incrémentation
     */
    private static int id_comp= 1;

    /**
     * identifiant, numéro de la compétence
     */
    protected int id_competence;

    /**
     * niveau de la compétence
     */
    protected int niveau;

    /**
     * objet de la classe Disciplines
     */
    protected Disciplines disciplines;

    protected Employe employe;

    /**
     * Constructeur paramétré de la classe Compétence
     *
     * @param discipline discipline de la compétence
     * @param niveau niveau de la compétence
     */
    public Competence(Disciplines discipline, int niveau){
        this.disciplines = discipline;
        this.niveau = niveau;
        this.id_competence = id_comp++;

    }
    public Competence(int id_comp, int niveau, Disciplines discipline, Employe employe) {
        this.id_competence = id_comp;
        this.niveau = niveau;
        this.disciplines = discipline;
        this.employe = employe;
    }

    public Competence(int id_comp, int niveau, Employe employe){
        this.id_competence = id_comp;
        this.niveau = niveau;
        this.employe = employe;
    }

    /**
     * getter du numéro de la compétence
     * @return numéro de la compétence
     */
    public int getId_competence() {
        return id_competence;
    }

    /**
     * setter du numéro de la compétence
     * @param id_competence numéro de la compétence
     */
    public void setId_competence(int id_competence) {
        this.id_competence = id_competence;
    }

    /**
     * getter du niveau de la compétence
     * @return niveau de la compétence
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * setter du niveau de la compétence
     * @param niveau niveau de la compétence
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * getter de l'objet de la classe Disciplines
     * @return
     */
    public Disciplines getDisciplines() {
        return disciplines;
    }

    /**
     * setter de l'objet de la classe Disciplines
     * @param disciplines
     */
    public void setDisciplines(Disciplines disciplines) {
        this.disciplines = disciplines;
    }


    /**
     * convertion de l'objet Competence en une chaîne de caractères représentative
     * @return les informations de la compétence
     */
    @Override
    public String toString() {
        return "Competence{" +
                "id_competence=" + id_competence +
                ", niveau=" + niveau +
                ", disciplines=" + disciplines +
                '}';
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competence that = (Competence) o;
        return id_competence == that.id_competence && niveau == that.niveau && Objects.equals(disciplines, that.disciplines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_competence, niveau, disciplines);
    }
}
