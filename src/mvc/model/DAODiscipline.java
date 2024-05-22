package mvc.model;

import Informatique.metier.Disciplines;
import Informatique.metier.ListeDisciplinesEtNiveau;
import mvc.observer.Subject;

import java.util.List;

public abstract class DAODiscipline extends Subject {

    public abstract Disciplines addDiscipline(Disciplines disciplines);

    public abstract boolean removeDiscipline(Disciplines disciplines);

    public abstract Disciplines updateDiscipline(Disciplines disciplines);

    public abstract Disciplines readDiscipline(int idDiscipline);

    public abstract List<Disciplines> getDisciplines();


}
