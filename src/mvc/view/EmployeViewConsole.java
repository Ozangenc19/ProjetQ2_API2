package mvc.view;

import Informatique.metier.Competence;
import Informatique.metier.Disciplines;
import Informatique.metier.Employe;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class EmployeViewConsole extends EmployeAbstractView {


    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : " + msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }

    public void menu() {
        update(empController.getAll());
        do {
            int ch = choixListe(Arrays.asList("Ajout", "Supprimer", "Rechercher", "Modifier", "Fin"));

            switch (ch) {
                case 1:
                    ajouter();
                    break;
                case 2:
                    supprimer();
                    break;
                case 3:
                    rechercher();
                    break;
                case 4:
                    modifier();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }


    private void ajouter() {
        System.out.print("Matricule de l'employé : ");
        String matricule = sc.next();
        System.out.print("Nom : ");
        String nom = sc.next();
        System.out.print("Prénom : ");
        String prenom = sc.next();
        System.out.print("Téléphone : ");
        String tel = sc.next();
        System.out.print("Mail : ");
        String mail = sc.next();
        Employe ep = empController.addEmploye(new Employe(0, matricule, nom, prenom, tel, mail));
        if (ep != null) affMsg("création de :" + ep);
        else affMsg("erreur de création");
    }

    private void supprimer() {

        int nl = choixElt(le);
        Employe ep = le.get(nl - 1);
        boolean ok = empController.removeEmploye(ep);
        if (ok) affMsg("Employé effacé");
        else affMsg("Employé non effacé");
    }

    private void rechercher() {
        System.out.println("idEmploye : ");
        int idEmploye = sc.nextInt();
        empController.search(idEmploye);
    }

    private void modifier() {
        int nl = choixElt(le);

        Employe emp = le.get(nl - 1);
        String matricule = modifyIfNotBlank("Matricule de l'employé : ", emp.getMatricule());
        String nom = modifyIfNotBlank("Nom : ", emp.getNom());
        String prenom = modifyIfNotBlank("Prenom : ", emp.getPrenom());
        String tel = modifyIfNotBlank("Tel : ", emp.getTel());
        String mail = modifyIfNotBlank("Mail : ", emp.getMail());
        Employe employemaj = empController.updateEmploye(new Employe(emp.getId_employe(), matricule, nom, prenom, tel, mail));
        if (employemaj == null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : " + employemaj);
    }


    @Override
    public Employe selectionner() {
        update(empController.getAll());
        int nl = choixListe(le);
        Employe ep = le.get(nl - 1);
        return ep;
    }

    private void operationspecial(Employe employe) {
        do {
            affMsg(" Employe " + employe.toString());

            int choix = choixListe(Arrays.asList("ajouter une discipline", "modifier une discipline", "supprimer une discipline", "lister une discipline", "fin"));
            switch (choix) {
                case 1:
                    ajouterDidscipline(employe);
                    break;
                case 2:
                    modfifierDiscipline(employe);
                    break;
                case 3:
                    supprimerDiscipline(employe);
                    break;
                case 4:
                    listerDisciplines(employe);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }


    public void ajouterDidscipline(Employe employe){
        System.out.println("Ajout d'une competence");
        Disciplines dis = disabview.selectionner();
        System.out.println(" Niveau : ");
        int niveau = sc.nextInt();
        boolean ok = empController.addDiscipline(employe,dis,niveau);
        if (ok) affMsg("Discipline ajoutée avec succes");
        else affMsg(" erreur lors de l'ajout");
    }

    private void modfifierDiscipline(Employe employe) {
        System.out.println("Modification d'une competence");
        List<Competence> lc = empController.competences(employe);
        affList(lc);
        Competence c = lc.get(choixElt(lc)-1);
        Disciplines dis = c.getDisciplines();
        System.out.println("niveau : ");
        int niveau = sc.nextInt();
        boolean ok = empController.updateDiscipline(employe,dis,niveau);
        if (ok) affMsg("Mise à jour éffectuée");
        else affMsg(" echec de la mise à jour");
    }

    private void supprimerDiscipline(Employe employe) {
        System.out.println(" Suprresion d'une compétence ");
        List<Competence> lc = empController.competences(employe);
        affList(lc);
        Competence c = lc.get(choixElt(lc)-1);
        Disciplines dis = c.getDisciplines();
        boolean ok = empController.suppDiscipline(employe,dis);
        if (ok) affMsg("Competence supprimé avec succes");
        else affMsg(" echec de la suppresion ");
    }

    private void listerDisciplines(Employe employe) {
        System.out.println("Competence de l'employé : "+employe);
        List<Competence> lc = empController.competences(employe);
        if (lc.isEmpty())
            affMsg("aucune competence pour cette employe");
        else affList(lc);
    }


}
