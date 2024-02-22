package Informatique.metier;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * classe Projet de gestion de projet
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
    protected Date dateDebut;
    /**
     * date de fin du projet
     */
    protected Date dateFin;
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
     * Constructeur paramétré de la classe Projet
     *
     * @param nom nom du projet
     * @param dateDebut date de début du projet
     * @param dateFin date de fin du projet
     * @param cout cout total du projet
     */
    public Projet(String nom, Date dateDebut, Date dateFin, BigDecimal cout, Employe responsable) {
        this.id_projet = idProjet++;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.responsable = responsable;
    }

    /**
     * Constructeur paramétré de la classe Projet pour vérification
     * @param nom nom du projet
     * @param responsable responsable du projet
     */
    public Projet(String nom, Employe responsable){
        this.nom = nom;
        this.responsable = responsable;
    }


    /**
     * getter numéro du projet
     * @return numéro du projet
     */
    public int getId_projet() {
        return id_projet;
    }

    /**
     * setter numéro du projet
     * @param id_projet numéro du projet
     */

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }

    /**
     * getter nom du projet
     * @return nom du projet
     */
    public String getNom() {
        return nom;
    }

    /**
     * setter nom du projet
     * @param nom nom du projet
     */

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getter date début du projet
     * @return la date début du projet
     */

    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * setter date début du projet
     * @param dateDebut la date de début du projet
     */

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * getter date fin du projet
     * @return date fin du projet
     */

    public Date getDateFin() {
        return dateFin;
    }

    /**
     * setter date fin du projet
     * @param dateFin la date de fin du projet
     */

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * getter cout total du projet
     * @return cout total du projet
     */

    public BigDecimal getCout() {
        return cout;
    }

    /**
     * setter cout total du projet
     * @param cout le cout total du projet
     */
    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    /**
     * getter responsable du projet
     * @return reponsable du projet
     */

    public Employe getResponsable() {
        return responsable;
    }

    /**
     * setter responsable du projet
     * @param responsable le responsable du projet
     */

    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }

    /**
     * retourne la liste des disciplines et le niveau du responsable du projet
     * @return liste des disciplines et niveau du responsable de projet
     */
    public List<Investissement> listeDisciplineEtInvestissement(){
        return Listeinvest;
    }

    /**
     * getter de la liste des investissements
     * @return liste des investissements
     */
    public List<Investissement> getListeinvest() {
        return Listeinvest;
    }

    /**
     * setter de la liste des investissements
     * @param listeinvest liste des investissements
     */
    public void setListeinvest(List<Investissement> listeinvest) {
        Listeinvest = listeinvest;
    }

    /**
     * retourne le total des investissements
     * @return total des investissements
     */
    public int investissementTotal(){
        int total = 0;
        for (Investissement invest : Listeinvest){
            total += invest.quantiteJH;
        }

        return total;
    }
    /**
     * ajout d'une discpline au projet
     * @param discipline discpline à ajouter
     * @return ajout effectué ou pas
     */

    public boolean addDiscipline(Disciplines discipline, int quantite){
        for (Investissement i : Listeinvest ){
            if (i.getDiscipline().equals(discipline));
            return false;
        }
        Investissement investissement = new Investissement(discipline, quantite);
        Listeinvest.add(investissement);
        return true;
    }

    /**
     * modification d'une discipline
     * @param discipline Discipline
     */
    public void modifDiscipline(Disciplines discipline,int quantite){
        for (Investissement invest : Listeinvest){
            if (Listeinvest.get(Listeinvest.indexOf(invest)).discipline.equals(discipline)){
                Listeinvest.get(Listeinvest.indexOf(invest)).quantiteJH = quantite;
            }
            }
        }


    /**
     * suppression d'une discipline
     * @param discipline
     */
    public void suppDiscipline(Disciplines discipline){
        Listeinvest.remove(discipline);
    }


    /**
     * convertion de l'objet Projet en une chaîne de caractères représentative
     * @return les informations du Projet
     */
    @Override
    public String toString() {
        return "Projet{" +
                "nom='" + nom + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", cout=" + cout +
                ", responsable=" + responsable +
                ", Listeinvest=" + Listeinvest +
                '}';
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return Objects.equals(nom, projet.nom) && Objects.equals(dateDebut, projet.dateDebut) && Objects.equals(dateFin, projet.dateFin) && Objects.equals(cout, projet.cout) && Objects.equals(responsable, projet.responsable) && Objects.equals(Listeinvest, projet.Listeinvest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, dateDebut, dateFin, cout, responsable, Listeinvest);
    }
}