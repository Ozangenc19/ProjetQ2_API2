package Informatique.metier;

import java.util.Objects;

/**
 * classe Disciplines de gestion de projet
 * @author Ozan Genc
 * @version 1.0
 */
public class Disciplines {

    /**
     * création d'id pour auto incrémentation
     */
    private static int id_disc = 1;

    /**
     * identifiant, numéro de discipline
     */
    protected int id_discipline;

    /**
     * nom de la discipline
     */
    protected String nom;

    /**
     * description de la discipline
     */
    protected String description;


    /**
     * Constructeur paramétré de la classe Disciplines
     *
     * @param nom nom de la discipline
     * @param description description de la discipline
     */
    public Disciplines(String nom, String description) {
        this.id_discipline = id_disc++;
        this.nom = nom;
        this.description = description;
    }


    /**
     * gette du numéro de la discipline
     * @return numéro de la discipline
     */
    public int getId_discipline() {
        return id_discipline;
    }

    /**
     * setter du numéro de la discipline
     * @param id_discipline numéro de la discipline
     */
    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }

    /**
     * getter du nom de la discipline
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter du nom de la discipline
     * @param nom nom de la discipline
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter de la description de la discipline
     * @return description de la discipline
     */
    public String getDescription() {
        return description;
    }

    /**
     * setter de la description de la discipline
     * @param description description de la discipline
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * convertion de l'objet Disciplines en une chaîne de caractères représentative
     * @return les informations de la discipline
     */
    @Override
    public String toString() {
        return "Disciplines{" +
                "id_discipline=" + id_discipline +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplines that = (Disciplines) o;
        return id_discipline == that.id_discipline && Objects.equals(nom, that.nom) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_discipline, nom, description);
    }




}
