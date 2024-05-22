package mvc.controller;

import Informatique.metier.Disciplines;
import mvc.model.DAODiscipline;
import mvc.view.DisciplineAbstractView;

import java.util.List;

public class DisciplineController {
    private DAODiscipline model;
    private DisciplineAbstractView view;


    public DisciplineController(DAODiscipline model, DisciplineAbstractView view){
        this.model = model;
        this.view = view;
        this.view.setDisciplineController(this);
    }

    public List<Disciplines> getAll(){
        return model.getDisciplines();
    }
    public Disciplines addDiscipline(Disciplines disciplines){
        return model.addDiscipline(disciplines);
    }

    public boolean removeDiscipline(Disciplines disciplines){
        return model.removeDiscipline(disciplines);
    }

    public Disciplines updateDiscipline(Disciplines disciplines){
        return model.updateDiscipline(disciplines);
    }

    public Disciplines search(int idDiscipline){
        return model.readDiscipline(idDiscipline);
    }

}
