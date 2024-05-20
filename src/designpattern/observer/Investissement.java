package designpattern.observer;


import Informatique.metier.Projet;

/**
 * classe Investissement de gestion de projet
 * @author Ozan Genc
 * @version 1.0
 */
public class Investissement {

    /**
     * création d'id pour auto incrémentation
     */
    private static int idInvest = 1;

    /**
     * identifiant, numero de l'investissement
     */
    protected int id_invest;

    /**
     * quantite de JH
     */
    protected int quantiteJH;

    /**
     * objet de la classe Disciplines
     */
    protected Disciplines discipline;

    /**
     * objet de la classe Projet
     */
    protected Projet projet; // test

    /**
     * Constructeur paramétré de la classe Investissement
     *
     * @param disciplines objet Disciplines
     * @param quantiteJH    quantite de JH
     */
    public Investissement(Disciplines disciplines, int quantiteJH) {
        this.discipline = disciplines;
        this.quantiteJH = quantiteJH;
        this.id_invest = idInvest++;

    }

    /**
     * getter numero de l'investissement
     *
     * @return numero investissement
     */


    public int getId_invest() {
        return id_invest;
    }

    /**
     * setter numero de l'investissement
     *
     * @param id_invest numero investissement
     */
    public void setId_invest(int id_invest) {
        this.id_invest = id_invest;
    }

    /**
     * getter quantite de JH
     *
     * @return quantite de JH
     */
    public int getQuantiteJH() {
        return quantiteJH;
    }

    /**
     * setter quantite de JH
     *
     * @param quantiteJH quantite de JH
     */
    public void setQuantiteJH(int quantiteJH) {
        this.quantiteJH = quantiteJH;
    }

    /**
     * getter de la classe Discipline
     *
     * @return discipline
     */
    public Disciplines getDiscipline() {
        return discipline;
    }

    /**
     * setter de la classe Discipline
     *
     * @param discipline discipline
     */
    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }

    /**
     * getter de la classe Projet
     *
     * @return projet
     */

    public Projet getProjet() { // test
        return projet;
    }

    /**
     * setter de la classe Projet
     *
     * @param projet projet
     */
    public void setProjet(Projet projet) { // test
        this.projet = projet;
    }
}
