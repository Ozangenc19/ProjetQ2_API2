package Informatique;

import java.util.Objects;

public class Disciplines {
    protected int id_discipline;
    protected String nom;
    protected String description;

    public Disciplines(int id_discipline, String nom, String description) {
        this.id_discipline = id_discipline;
        this.nom = nom;
        this.description = description;
    }

    public int getId_discipline() {
        return id_discipline;
    }

    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
