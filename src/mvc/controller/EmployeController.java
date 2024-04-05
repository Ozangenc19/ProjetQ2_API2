package mvc.controller;

import Informatique.metier.Employe;
import mvc.model.DAOEmploye;
import mvc.view.EmployeAbstractView;

import java.util.List;

public class EmployeController {
    private DAOEmploye model;
    private EmployeAbstractView view;


    public EmployeController(DAOEmploye model, EmployeAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);

    }


    public List<Employe> getAll() {
        return model.getEmployes();
    }



    public Employe addEmploye(Employe employe) {
        return model.addEmploye(employe);
    }

    public boolean removeEmploye(Employe em) {
        return model.removeEmploye(em);
    }

    public Employe updateEmploye(Employe employe) {
        return model.updateEmploye(employe);
    }

    public Employe search(int idEmploye){
        return model.readEmploye(idEmploye);
    }
}
