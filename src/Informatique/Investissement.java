package Informatique;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Investissement {
    protected int id_invest;
    protected int quantiteJH;

    public List<Projet>projets = new ArrayList<>();
    public List<Disciplines>disciplines = new ArrayList<>();

    public Investissement(int id_invest, int quantiteJH, List<Projet> projets, List<Disciplines> disciplines) {
        this.id_invest = id_invest;
        this.quantiteJH = quantiteJH;
        this.projets = projets;
        this.disciplines = disciplines;
    }

    public int getId_invest() {
        return id_invest;
    }

    public void setId_invest(int id_invest) {
        this.id_invest = id_invest;
    }

    public int getQuantiteJH() {
        return quantiteJH;
    }

    public void setQuantiteJH(int quantiteJH) {
        this.quantiteJH = quantiteJH;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
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
        Investissement that = (Investissement) o;
        return id_invest == that.id_invest && quantiteJH == that.quantiteJH && Objects.equals(projets, that.projets) && Objects.equals(disciplines, that.disciplines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_invest, quantiteJH, projets, disciplines);
    }
}
