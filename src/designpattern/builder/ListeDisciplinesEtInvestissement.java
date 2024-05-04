package designpattern.builder;

/**
 * classe ListeDisciplinesEtInvestissement de gestion de projet
 *
 * @author Ozan Genc
 * @version 1.0
 */
public class ListeDisciplinesEtInvestissement {

    /**
     * discipline d'un investissement
     */
    protected Disciplines disciplines;

    /**
     * quantité d'un investissement
     */
    protected int quantiteJH;

    /**
     * Crée une nouvelle instance de ListeDisciplinesEtInvestissement avec les disciplines spécifiées
     * et la quantité de travail en heures associée.
     *
     * @param disciplines les disciplines à associer à cette instance
     * @param quantiteJH  la quantité de travail en heures associée à ces disciplines
     */
    public ListeDisciplinesEtInvestissement(Disciplines disciplines, int quantiteJH) {
        this.disciplines = disciplines;
        this.quantiteJH = quantiteJH;
    }

    /**
     * getter d'une discipline
     *
     * @return disciplines
     */
    public Disciplines getDisciplines() {
        return disciplines;
    }

    /**
     * setter d'une discipline
     *
     * @param disciplines disciplines
     */
    public void setDisciplines(Disciplines disciplines) {
        this.disciplines = disciplines;
    }

    /**
     * getter d'une quantite
     *
     * @return quantiteJH
     */
    public int getQuantiteJH() {
        return quantiteJH;
    }

    /**
     * setter d'une quantite
     *
     * @param quantiteJH quantiteJH
     */
    public void setQuantiteJH(int quantiteJH) {
        this.quantiteJH = quantiteJH;
    }
}
