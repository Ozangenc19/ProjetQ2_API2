package mvc.view;

import Informatique.metier.Employe;
import mvc.controller.EmployeController;
import mvc.observer.Observer;


import java.util.List;

public abstract class EmployeAbstractView  implements Observer {
    protected EmployeController empController;
    protected DisciplineAbstractView disabview;
    protected List<Employe> le;

    public void setEmployeController(EmployeController empController) {
        this.empController = empController;
    }

    public void setDisciplineView(DisciplineAbstractView disabview){
        this.disabview = disabview;
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
