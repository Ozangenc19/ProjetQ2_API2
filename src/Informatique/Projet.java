package Informatique;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Projet {
    protected int id_projet;
    protected String nom;
    protected Date dateDebut;
    protected Date dateFin;
    protected BigDecimal cout;
    protected Employe responsable;
    public List<Disciplines>disciplines = new ArrayList<>();

    public Projet(int id_projet, String nom, Date dateDebut, Date dateFin, BigDecimal cout, Employe responsable) {
        this.id_projet = id_projet;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.cout = cout;
        this.responsable = responsable;
    }

    public int getId_projet() {
        return id_projet;
    }

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public Employe getResponsable() {
        return responsable;
    }

    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }

    public boolean addDiscpiline(Disciplines disciplines){
        for(Disciplines d : disciplines){
            if (d.getDescription().equals(disciplines.getDescription()))
                return false;
        }
        disciplines.add(disciplines);
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return id_projet == projet.id_projet && Objects.equals(nom, projet.nom) && Objects.equals(dateDebut, projet.dateDebut) && Objects.equals(dateFin, projet.dateFin) && Objects.equals(cout, projet.cout) && Objects.equals(responsable, projet.responsable) && Objects.equals(disciplines, projet.disciplines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_projet, nom, dateDebut, dateFin, cout, responsable, disciplines);
    }
}
