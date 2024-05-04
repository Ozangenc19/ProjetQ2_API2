package mvc.model;

import Informatique.metier.Employe;
import Informatique.metier.Projet;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOEmploye extends Subject {
    public abstract Employe addEmploye(Employe e);

    public abstract boolean removeEmploye(Employe e);

    public abstract Employe updateEmploye(Employe e);

    public abstract Employe readEmploye(int id);

    public abstract List<Employe> getEmployes();
}
