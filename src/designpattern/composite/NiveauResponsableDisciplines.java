package designpattern.composite;

/**
 * classe NiveauResponsableDisciplinesde gestion de projet
 *
 * @author Ozan Genc
 * @version 1.0
 */
public class NiveauResponsableDisciplines {

    /**
     * discipline d'un responsable par rapport à son niveau
     */
    protected Disciplines discipline;
    /**
     * niveau d'un responsable par rapport à ca discipline
     */
    protected int niveau;

    /**
     * nom du responsable
     */
    protected String nom;


    /**
     * Crée une nouvelle instance de NiveauResponsableDisciplines avec la discipline spécifiée
     * et le niveau de responsabilité associé.
     *
     * @param discipline la discipline à associer à cette instance de niveau de responsabilité
     * @param niveau     le niveau de responsabilité associé à cette discipline
     */
    public NiveauResponsableDisciplines(Disciplines discipline, int niveau, String nom) {
        this.discipline = discipline;
        this.niveau = niveau;
        this.nom = nom;
    }

    public NiveauResponsableDisciplines(Informatique.metier.Disciplines disciplines, int niveau, String nom) {

    }

    /**
     * getter d'une discipline
     *
     * @return disciplines
     */
    public Disciplines getDiscipline() {
        return discipline;
    }

    /**
     * setter d'une discipline
     *
     * @param discipline discipline
     */
    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
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
