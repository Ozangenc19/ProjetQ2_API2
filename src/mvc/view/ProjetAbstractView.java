package mvc.view;

import Informatique.metier.Projet;
import mvc.controller.ProjetController;
import mvc.observer.Observer;

import java.util.List;

public abstract class ProjetAbstractView implements Observer {
    protected ProjetController projetController;

    protected DisciplineAbstractView disabview;

    protected EmployeAbstractView empabview;

    protected List<Projet> lp;

    public void setProjetController(ProjetController projetController) {
        this.projetController = projetController;
    }

    public void setDisciplineView(DisciplineAbstractView disabview) {
        this.disabview = disabview;
    }

    public void setEmployeView(EmployeAbstractView empabview) {
        this.empabview = empabview;
    }

    public abstract void affMsg(String msg);

    public abstract Projet selectionner();

    public abstract void menu();

    public abstract void affList(List l);


    @Override
    public void update(List lp) {
        this.lp = lp;
        affList(lp);
    }

}
