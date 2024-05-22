package mvc.model;

import Informatique.metier.*;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOEmploye extends Subject {
    public abstract Employe addEmploye(Employe e);

    public abstract boolean removeEmploye(Employe e);

    public abstract Employe updateEmploye(Employe e);

    public abstract Employe readEmploye(int idEmploye);

    public abstract List<Employe> getEmployes();

    public abstract boolean addDiscipline(Employe employe, Disciplines disciplines, int niveau);

    public abstract boolean updateDiscipline(Employe employe, Disciplines disciplines, int niveau);

    public abstract boolean suppDisicpline(Employe employe, Disciplines disciplines);

    public abstract List<ListeDisciplinesEtNiveau> listeDisciplinesEtNiveaus();

    public abstract List<Projet> listeProjets(Employe employe);

    public abstract List<Competence> listeCompet(Employe employe);
}
