package mvc.view;

import Informatique.metier.Employe;
import mvc.controller.EmployeController;
import mvc.observer.Observer;

import javax.swing.*;
import javax.swing.text.View;
import java.util.List;

public abstract class EmployeAbstractView  implements Observer {
    protected EmployeController empController;
    //TODO faire discipline dans ce code
    protected List<Employe> le;

    public void setEmployeController(EmployeController empController) {
        this.empController = empController;
    }
    public abstract void affMsg(String msg);
    public abstract Employe selectionner();
    public abstract void menu();
    public abstract void affList(List l);

    @Override
    public void update(List le){
        this.le = le;
        affList(this.le);
    }

}
