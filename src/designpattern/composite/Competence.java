package designpattern.composite;


import Informatique.metier.Disciplines;

/**
 * classe Competence de gestion de projet
 *
 * @author Ozan Genc
 * @version 1.0
 */
public class Competence {

    /**
     * création d'un id pour auto incrémentation
     */
    private static int id_comp = 1;

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


    /**
     * Constructeur paramétré de la classe Compétence
     *
     * @param discipline discipline de la compétence
     * @param niveau     niveau de la compétence
     */
    public Competence(Disciplines discipline, int niveau) {
        this.disciplines = discipline;
        this.niveau = niveau;
        this.id_competence = id_comp++;

    }

    /**
     * getter du numéro de la compétence
     *
     * @return numéro de la compétence
     */
    public int getId_competence() {
        return id_competence;
    }

    /**
     * setter du numéro de la compétence
     *
     * @param id_competence numéro de la compétence
     */
    public void setId_competence(int id_competence) {
        this.id_competence = id_competence;
    }

    /**
     * getter du niveau de la compétence
     *
     * @return niveau de la compétence
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * setter du niveau de la compétence
     *
     * @param niveau niveau de la compétence
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    /**
     * getter de l'objet de la classe Disciplines
     *
     * @return
     */
    public Disciplines getDisciplines() {
        return disciplines;
    }

    /**
     * setter de l'objet de la classe Disciplines
     *
     * @param disciplines
     */
    public void setDisciplines(Disciplines disciplines) {
        this.disciplines = disciplines;

    }
}

