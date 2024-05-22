package mvc.controller;

import Informatique.metier.*;
import mvc.model.DAOProjet;
import mvc.view.ProjetAbstractView;

import java.util.List;

public class ProjetController {
    private DAOProjet model;
    private ProjetAbstractView view;

    public ProjetController(DAOProjet model, ProjetAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setProjetController(this);
    }

    public List<Projet> getAll() {
        return model.getProjets();
    }

    public Projet addProjet(Projet projet) {
        return model.addProjet(projet);
    }

    public boolean removeProjet(Projet projet) {
        return model.removeProjet(projet);
    }

    public Projet updateProjet(Projet projet) {
        return model.updateProjet(projet);
    }

    public Projet search(int idProjet) {
        return model.readProjet(idProjet);
    }

    public boolean addDiscipline(Projet projet, Disciplines disciplines, int quantiteJH) {
        return model.addDiscipline(projet, disciplines, quantiteJH);
    }

    public boolean updateDiscipline(Projet projet, Disciplines disciplines, int quantiteJH) {
        return model.updateDiscipline(projet, disciplines, quantiteJH);
    }

    public boolean suppDiscipline(Projet projet, Disciplines disciplines) {
        return model.suppDiscipline(projet,disciplines);
    }

    public List<ListeDisciplinesEtInvestissement> listeDisciplinesEtInvestissement(){
        return model.listeDisciplinesEtInvestissement();
    }

    public List<NiveauResponsableDisciplines> niveauResponsableDiscipline(){
        return model.niveauResponsableDiscipline();
    }

    public int investissementTotal(){
        return model.investissementTotal();
    }
    public List<Investissement> getInvestissement(Projet projet){
        return model.getInvestissement(projet);
    }
}

