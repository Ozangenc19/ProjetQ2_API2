package mvc.model;

import Informatique.metier.Disciplines;
import Informatique.metier.Investissement;
import Informatique.metier.Projet;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAOProjet extends Subject {
    public abstract Projet addProjet(Projet p);

    public abstract boolean removeProjet(Projet p);

    public abstract Projet updateProjet(Projet p);

    public abstract Projet readProjet(int idProjet);

    public abstract List<Projet> getProjets();

    public abstract boolean addDiscipline(Projet projet, Disciplines disciplines, int quantiteJH);

    public abstract boolean updateDiscipline(Projet projet, Disciplines disciplines, int quantiteJH);

    public abstract boolean removeDiscipline(Projet projet, Disciplines disciplines);

    public abstract List<Investissement> getProjets(Projet projet);
}
