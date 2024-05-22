package mvc.model;

import Informatique.metier.*;
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

    public abstract boolean suppDiscipline(Projet projet, Disciplines disciplines);

    public abstract List<ListeDisciplinesEtInvestissement> listeDisciplinesEtInvestissement();

    public abstract List<NiveauResponsableDisciplines> niveauResponsableDiscipline();

    public abstract int investissementTotal();

    public abstract List<Investissement> getInvestissement(Projet projet);
}
