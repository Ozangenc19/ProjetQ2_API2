package Informatique.metier;

public class NiveauResponsableDisciplines {

    protected Disciplines discipline;
    protected int niveau;

    public NiveauResponsableDisciplines(Disciplines discipline, int niveau) {
        this.discipline = discipline;
        this.niveau = niveau;
    }

    public Disciplines getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
