package designpattern.composite;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * classe Projet de gestion de projet
 *
 * @author Ozan Genc
 * @version 1.0
 */
public class Projet {

    /**
     * créarion d'id pour auto incrémentation
     */
    private static int idProjet = 1;
    /**
     * identifiant unique/numéro du projet
     */
    protected static int id_projet;
    /**
     * nom du projet
     */
    protected String nom;
    /**
     * date de début du projet
     */
    protected LocalDate dateDebut;
    /**
     * date de fin du projet
     */
    protected LocalDate dateFin;
    /**
     * cout total du projet
     */
    protected BigDecimal cout;
    /**
     * responsable du projet
     */
    protected Employe responsable;


    /**
     * liste des disciplines
     */
    protected List<Investissement> Listeinvest = new ArrayList<>();

    /**
     * liste du niveau des compétences pour chaque responsable selon ca discipline
     */
    protected List<NiveauResponsableDisciplines> ListeNivResDiscipline = new ArrayList<>();
    /**
     * liste de la quantite d'investissement pour chaque discipline
     */
    protected List<ListeDisciplinesEtInvestissement> ListeDiscInvest = new ArrayList<>();

    /**
     * Constructeur paramétré de la classe Projet
     *
     * @param nom         nom du projet
     * @param dateDebut   date de début du projet
     * @param dateFin     date de fin du projet
     * @param cout        cout total du projet
     * @param responsable objet de la table Employe
     */
    public Projet(String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout, Employe responsable) {
        this.id_projet = idProjet++;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.responsable = responsable;
    }


    /**
     * Constructeur paramétré de la classe Projet pour vérification
     *
     * @param nom         nom du projet
     * @param responsable responsable du projet
     */
    public Projet(String nom, Employe responsable) {
        this.nom = nom;
        this.responsable = responsable;
    }

    public Projet(int numprojet, String nom, LocalDate dateDebut, LocalDate dateFin, BigDecimal cout, Employe employe) {
        this.id_projet = numprojet;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.responsable = employe;
    }


    /**
     * getter numéro du projet
     *
     * @return numéro du projet
     */
    public int getId_projet() {
        return id_projet;
    }

    /**
     * setter numéro du projet
     *
     * @param id_projet numéro du projet
     */

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }

    /**
     * getter nom du projet
     *
     * @return nom du projet
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom du projet
     *
     * @param nom nom du projet
     */

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter date début du projet
     *
     * @return la date début du projet
     */

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * setter date début du projet
     *
     * @param dateDebut la date de début du projet
     */

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * getter date fin du projet
     *
     * @return date fin du projet
     */

    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * setter date fin du projet
     *
     * @param dateFin la date de fin du projet
     */

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * getter cout total du projet
     *
     * @return cout total du projet
     */

    public BigDecimal getCout() {
        return cout;
    }

    /**
     * setter cout total du projet
     *
     * @param cout le cout total du projet
     */
    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    /**
     * getter responsable du projet
     *
     * @return reponsable du projet
     */

    public Employe getResponsable() {
        return responsable;
    }

    /**
     * setter responsable du projet
     *
     * @param responsable le responsable du projet
     */

    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }

    /**
     * getter de la liste des investissements
     *
     * @return liste des investissements
     */
    public List<Investissement> getListeinvest() {
        return Listeinvest;
    }

    /**
     * setter de la liste des investissements
     *
     * @param listeinvest liste des investissements
     */
    public void setListeinvest(List<Investissement> listeinvest) {
        Listeinvest = listeinvest;
    }

    /**
     * retourne la liste des disciplines et le niveau du responsable du projet
     *
     * @return liste des disciplines et le niveau du responsable du projet
     */
    public List<NiveauResponsableDisciplines> niveauResponsableDisciplines() {
        List<NiveauResponsableDisciplines> liste_respDiscipline= new ArrayList<>();
        for (Competence c : getResponsable().ListeCompt) {
            for (Investissement i : Listeinvest){
                if (c.getDisciplines().equals(i.getDiscipline())){
                    liste_respDiscipline.add(new NiveauResponsableDisciplines(c.getDisciplines(), c.getNiveau(), responsable.getNom()));
                }
            }

        }
        return liste_respDiscipline;
    }

    /**
     * Retourne une liste des disciplines associées à leur investissement en quantité
     * Cette méthode parcourt la liste des investissements et crée une liste de paires Discipline-QuantitéJH.
     * Note: Avant de retourner la liste, elle efface d'abord la liste actuelle pour éviter les duplications.
     *
     * @return Une liste de paires Discipline-QuantitéJH représentant les disciplines et leurs investissements.
     */
    protected List<ListeDisciplinesEtInvestissement> listeDisciplinesEtInvestissement() {
        ListeDiscInvest.clear();
        for (Investissement investissement : Listeinvest) {
            ListeDiscInvest.add(new ListeDisciplinesEtInvestissement(investissement.getDiscipline(), investissement.getQuantiteJH()));
        }
        return ListeDiscInvest;
    }

    /**
     * retourne le total des investissements
     *
     * @return total des investissements
     */
    public int investissementTotal() {
        int total = 0;
        for (Investissement invest : Listeinvest) {
            total += invest.quantiteJH;
        }

        return total;
    }

    /**
     * ajout d'une discpline au projet
     *
     * @param discipline discpline à ajouter
     * @return ajout effectué ou pas
     */

    public boolean addDiscipline(Disciplines discipline, int quantite) {
        for (Investissement i : Listeinvest) {
            if (i.getDiscipline().equals(discipline)) ;
            return false;
        }
        Investissement investissement = new Investissement(discipline, quantite);
        Listeinvest.add(investissement);
        return true;
    }

    /**
     * modification d'une discipline
     *
     * @param discipline Discipline
     */

    public boolean modifDiscipline(Disciplines discipline, int quantite) {
        for (Investissement investissement : Listeinvest) {
            if (investissement.getDiscipline().equals(discipline)) {
                investissement.setQuantiteJH(quantite);
                return true;
            }
        }
        return false; // La discipline n'a pas été trouvée et modifiée
    }

    /**
     * suppression d'une discipline
     *
     * @param discipline Discipline
     */

    public boolean suppDiscipline(Disciplines discipline) {
        for (Investissement investissement : Listeinvest) {
            if (investissement.getDiscipline().equals(discipline)) {
                return Listeinvest.remove(investissement);

            }
        }
        return false; // La discipline n'a pas été trouvée et supprimée
    }
}

