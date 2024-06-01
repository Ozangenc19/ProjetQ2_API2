package mvc.view;


import Informatique.metier.*;
import mvc.GestInfo;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class EmployeViewConsole extends EmployeAbstractView {


    private Scanner sc = new Scanner(System.in);

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
        Employe emp = empController.search(idEmploye);
        if (emp == null) affMsg("recherche infructueuse");
        else {

            operationspecial(emp);
        }
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

            int choix = choixListe(Arrays.asList("ajouter une discipline", "modifier une discipline", "supprimer une discipline", "lister une discipline","lister un projet", "fin"));
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
                    listerProjets(employe);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);
    }


    public void ajouterDidscipline(Employe employe) {
        List<Disciplines> ld = GestInfo.dm.getDisciplines(); //on reprend la liste de toutes les disciplines
        System.out.println("Ajout d'une discipline");
        int choix = choixListe(ld);
        System.out.println(" Niveau : ");
        int niveau = sc.nextInt();
        boolean ok = empController.addDiscipline(employe,ld.get(choix - 1), niveau);
        if (ok) affMsg("Discipline ajoutée avec succes");
        else affMsg(" erreur lors de l'ajout");
    }

    private void modfifierDiscipline(Employe employe) {
        List<Competence> ld = empController.listeDisciplinesEtNiveau(employe);
        System.out.println("Modification d'une discipline");
        int choix = choixListe(ld);
        System.out.println("niveau : ");
        int niveau = sc.nextInt();
        boolean ok = empController.modifDiscipline(employe, ld.get(choix - 1).getDisciplines(), niveau);
        if (ok) affMsg("Mise à jour éffectuée");
        else affMsg(" echec de la mise à jour");
    }

    private void supprimerDiscipline(Employe employe) {
        List<Competence> ld = empController.listeDisciplinesEtNiveau(employe);
        System.out.println(" Suprresion d'une discipline ");
        int choix = choixListe(ld);
        boolean ok = empController.suppDiscipline(employe, ld.get(choix - 1).getDisciplines());
        if (ok) affMsg("Competence supprimé avec succes");
        else affMsg(" echec de la suppresion ");
    }

    private void listerDisciplines(Employe employe) {
        System.out.println("Discipline de l'employé : " + employe);
        List<Competence> lc = empController.listeDisciplinesEtNiveau(employe);
        if (lc.isEmpty())
            affMsg("aucune competence pour cette employe");
        else affList(lc);
    }

    private void listerProjets(Employe employe){
        System.out.println("Projet de l'employé : "+employe);
        List<Projet> lp = empController.listeProjets(employe);
        if (lp.isEmpty())
            affMsg("aucune competence pour cette employe");
        else affList(lp);
    }

    private void listerCompetence(Employe employe){
        System.out.println("Competence de l'employé : "+employe);
        List<Competence> lc = empController.listeCompetences(employe);
        if (lc.isEmpty())
            affMsg("aucune competence pour cette employe");
        else affList(lc);
    }

    private void listedisciplineniveau(Employe employe){
        List<Competence> lc = empController.listeDisciplinesEtNiveau(employe);

        if(lc.isEmpty()) affMsg("aucune discipline trouvée");
        else affList(lc);
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : " + msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }

}
