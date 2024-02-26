package Informatique.metier;

public class ListeDisciplinesEtNiveau {
    protected Disciplines disciplines;

    protected int niveau;

    public ListeDisciplinesEtNiveau(Disciplines disciplines, int niveau) {
        this.disciplines = disciplines;
        this.niveau = niveau;
    }

    public Disciplines getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Disciplines disciplines) {
        this.disciplines = disciplines;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
