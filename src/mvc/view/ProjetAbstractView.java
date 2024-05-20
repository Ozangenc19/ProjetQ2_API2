package mvc.view;

import Informatique.metier.Projet;
import mvc.controller.ProjetController;
import mvc.observer.Observer;

import java.util.List;

public abstract class ProjetAbstractView implements Observer {
    protected ProjetController projetController;
    protected List<Projet> lp;

    public void setController(ProjetController projetController){
        this.projetController=projetController;
    }

    public abstract void affMsg(String msg);

    public abstract Projet selectionner();

    public abstract void menu();

    public abstract void affList(List l);


    @Override
    public void update(List lp){
        this.lp = lp;
        affList(lp);
    }

}
