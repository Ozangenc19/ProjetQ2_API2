package mvc.controller;

import Informatique.metier.*;
import mvc.model.DAOEmploye;
import mvc.view.EmployeAbstractView;

import java.util.List;

public class EmployeController  {
    private DAOEmploye model;
    private EmployeAbstractView view;

    public EmployeController(DAOEmploye model, EmployeAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setEmployeController(this);
    }

    public List<Employe> getAll() {
        return model.getEmployes();
    }

    public Employe addEmploye(Employe employe) {
        return model.addEmploye(employe);
    }

    public boolean removeEmploye(Employe employe) {
        return model.removeEmploye(employe);
    }

    public Employe updateEmploye(Employe employe) {
        return model.updateEmploye(employe);
    }
    public Employe search(int idEmploye){
        return model.readEmploye(idEmploye);
    }

    public boolean addDiscipline(Employe employe, Disciplines disciplines, int niveau){
        return model.addDiscipline(employe,disciplines,niveau);
    }

    public boolean modifDiscipline(Employe employe, Disciplines disciplines, int niveau){
        return model.modifDiscipline(employe,disciplines,niveau);
    }

    public boolean suppDiscipline(Employe employe, Disciplines disciplines){
        return model.suppDisicpline(employe,disciplines);
    }

    public List<Competence> listeDisciplinesEtNiveau(Employe employe){
        return model.listeDisciplinesEtNiveaus(employe);
    }


    public List<Projet> listeProjets(Employe employe){
        return model.listeProjets(employe);
    }

    public List<Competence> listeCompetences(Employe employe){
        return model.listeCompet(employe);
    }


}
