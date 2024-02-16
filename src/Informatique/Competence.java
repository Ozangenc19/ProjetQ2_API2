package Informatique;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Competence {
    protected int id_competence;
    protected int niveau;

    public List<Employe>employes = new ArrayList<>();
    public List<Disciplines>disciplines = new ArrayList<>();

    public Competence(int id_competence, int niveau, List<Employe> employes, List<Disciplines> disciplines) {
        this.id_competence = id_competence;
        this.niveau = niveau;
        this.employes = employes;
        this.disciplines = disciplines;
    }

    public int getId_competence() {
        return id_competence;
    }

    public void setId_competence(int id_competence) {
        this.id_competence = id_competence;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public List<Disciplines> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Disciplines> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competence that = (Competence) o;
        return id_competence == that.id_competence && niveau == that.niveau && Objects.equals(employes, that.employes) && Objects.equals(disciplines, that.disciplines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_competence, niveau, employes, disciplines);
    }
}
