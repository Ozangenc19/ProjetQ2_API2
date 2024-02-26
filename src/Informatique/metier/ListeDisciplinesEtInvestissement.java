package Informatique.metier;

public class ListeDisciplinesEtInvestissement {
    protected Disciplines disciplines;

    protected int quantiteJH;

    public ListeDisciplinesEtInvestissement(Disciplines disciplines, int quantiteJH) {
        this.disciplines = disciplines;
        this.quantiteJH = quantiteJH;
    }

    public Disciplines getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Disciplines disciplines) {
        this.disciplines = disciplines;
    }

    public int getQuantiteJH() {
        return quantiteJH;
    }

    public void setQuantiteJH(int quantiteJH) {
        this.quantiteJH = quantiteJH;
    }
}
