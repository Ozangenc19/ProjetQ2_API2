package mvc.view;

import Informatique.metier.Disciplines;
import mvc.controller.DisciplineController;
import mvc.observer.Observer;

import java.util.List;

public abstract class DisciplineAbstractView implements Observer {
    protected DisciplineController  disciplineController;

    protected List<Disciplines> ld;

    public void setDisciplineController(DisciplineController disciplineController){
        this.disciplineController = disciplineController;
    }

    public abstract void affMsg(String msg);

    public abstract Disciplines selectionner();

    public abstract void menu();

    public abstract void affList(List l);

    @Override
    public void update(List ld){
        this.ld = ld;
        affList(ld);
    }
}
