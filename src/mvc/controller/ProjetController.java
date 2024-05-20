package mvc.controller;

import Informatique.metier.Disciplines;
import Informatique.metier.Investissement;
import Informatique.metier.Projet;
import mvc.model.DAOProjet;
import mvc.view.ProjetAbstractView;

import java.util.List;

public class ProjetController {
    private DAOProjet model;
    private ProjetAbstractView view;

    public ProjetController(DAOProjet model, ProjetAbstractView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
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

    public boolean removeDiscipline(Projet projet, Disciplines disciplines) {
        return model.removeDiscipline(projet, disciplines);
    }

    public List<Investissement> getProjets(Projet projet){
        return model.getProjets(projet);
    }
}

