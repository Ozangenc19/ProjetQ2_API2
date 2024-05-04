package designpattern.observer;

/**
 * classe ListeDisciplinesEtNiveau de gestion de projet
 *
 * @author Ozan Genc
 * @version 1.0
 */
public class ListeDisciplinesEtNiveau {

    /**
     * diciplines d'un niveau
     */
    protected Disciplines disciplines;

    /**
     * niveau d'une discipline
     */
    protected int niveau;

    /**
     * Crée une nouvelle instance de ListeDisciplinesEtNiveau avec les disciplines spécifiées et le niveau donné.
     *
     * @param disciplines les disciplines à associer à cette instance
     * @param niveau      le niveau associé à ces disciplines
     */
    public ListeDisciplinesEtNiveau(Disciplines disciplines, int niveau) {
        this.disciplines = disciplines;
        this.niveau = niveau;
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
     * @param disciplines discipline
     */
    public void setDisciplines(Disciplines disciplines) {
        this.disciplines = disciplines;
    }

    /**
     * getter d'un niveau
     *
     * @return niveau
     */

    public int getNiveau() {
        return niveau;
    }

    /**
     * setter d'un niveau
     *
     * @param niveau niveau
     */

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
