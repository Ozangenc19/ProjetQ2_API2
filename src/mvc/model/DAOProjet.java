package mvc.model;

import Informatique.metier.Projet;
import mvc.observer.Subject;

public abstract class DAOProjet extends Subject {
    public abstract Projet addProjet(Projet projet);

    public abstract boolean removeProjet(Projet projet);

    public abstract Projet updateProjet(Projet projet);

    public abstract Projet getProjets(int idProjet);
}
