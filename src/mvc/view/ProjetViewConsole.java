package mvc.view;

import Informatique.metier.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;

public class ProjetViewConsole extends ProjetAbstractView {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information : " + msg);

    }

    @Override
    public void affList(List l) {
        affListe(l);

    }

    @Override
    public void menu() {
        update(projetController.getAll());
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

        Employe responsable = empabview.selectionner();
        Projet proj = new Projet();
        proj.setResponsable(responsable);
        //TODO une liste qui sera soit employe ou discipline (sais pas encore)
        System.out.println("Nom du projet : ");
        String nom = sc.next();
        System.out.println("Date de début : ");
        String dte = sc.next();
        LocalDate datedebut = getDate(dte);
        System.out.println(" Date de fin ");
        dte = sc.next();
        LocalDate datefin = getDate(dte);
        System.out.println("Cout du projet : ");
        BigDecimal cout = sc.nextBigDecimal();
        Projet projet = projetController.addProjet(new Projet(0, nom, datedebut, datefin, cout, responsable));
        if (projet != null) affMsg("creation de : "+ projet);
        else affMsg("erreur de création");
    }

    private void supprimer() {
        affList(lp);
        int nl = choixElt(lp);
        Projet proj = lp.get(nl - 1);
        boolean ok = projetController.removeProjet(proj);
        if (ok) affMsg("Projet effacé");
        else affMsg("Projet non effacé");
    }

    private void rechercher() {
        System.out.println("idProjet: ");
        int idProjet = sc.nextInt();
        Projet proj = projetController.search(idProjet);
        if (proj == null) affMsg("recherche infructueuse");
        else {
            affMsg(proj.toString());
            operationspecial(proj);
        }
    }

    private void modifier() {
        int nl = choixElt(lp);

        Projet proj = lp.get(nl - 1);
        String nom = modifyIfNotBlank("Nom du projet : ", proj.getNom());
        String date = modifyIfNotBlank("date de debut :", proj.getDateDebut() + "");
        LocalDate datedebut = !date.equals("null") ? LocalDate.parse(date) : null;
        date = modifyIfNotBlank("date de fin :", proj.getDateFin() + "");
        LocalDate datefin = !date.equals("null") ? LocalDate.parse(date) : null;
        BigDecimal cout = new BigDecimal(modifyIfNotBlank(" cout : ", proj.getCout().toString())).setScale(2, RoundingMode.HALF_UP);
        Projet nvproj = new Projet(proj.getId_projet(), nom, datedebut, datefin, cout, proj.getResponsable());
        proj = projetController.updateProjet(nvproj);
        if (proj != null) affMsg("projet modifé avec succes");
        else affMsg(" erreur lors de la mise à jour");
    }


    @Override
    public Projet selectionner() {
        update(projetController.getAll());
        int nl = choixListe(lp);
        Projet proj = lp.get(nl - 1);
        return proj;
    }


    private void operationspecial(Projet projet) {
        do {
            int ch = choixListe(Arrays.asList("ajouter discipline", "modifier discipline", "supprimer discipline", "lister les disciplines", "menu principal"));

            switch (ch) {
                case 1:
                    ajouterDiscipline(projet);
                    break;
                case 2:
                    modfifierDiscipline(projet);
                    break;
                case 3:
                    supprimerDiscipline(projet);
                    break;
                case 4:
                    listerDiscipline(projet);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }
    public void ajouterDiscipline(Projet projet){
        System.out.println("Ajout d'un investissement");
        Disciplines disciplines = disabview.selectionner();
        System.out.println("quantiteJH : ");
        int quantiteJH = sc.nextInt();
        boolean ok = projetController.addDiscipline(projet,disciplines,quantiteJH);
        if (ok) affMsg("discipline ajouté avec success");
        else affMsg(" erreur lord e l'ajout de l'employe");
    }

    private void modfifierDiscipline(Projet projet) {
        System.out.println("Modification d'un investissement");
        List<Investissement> li = projetController.getInvestissement(projet);
        affList(li);
        Investissement i = li.get(choixElt(li)-1);
        Disciplines disciplines = i.getDiscipline();
        System.out.println("quantiteJH: ");
        int quantitejH = sc.nextInt();
        boolean ok = projetController.updateDiscipline(projet,disciplines,quantitejH);
        if (ok) affMsg("Mise à jour éffectuée");
        else affMsg(" echec de la mise à jour");
    }

    private void supprimerDiscipline(Projet projet) {
        System.out.println(" Suprresion d'un investissement ");
        List<Investissement> li = projetController.getInvestissement(projet);
        affList(li);
        Investissement i = li.get(choixElt(li)-1);
        Disciplines disciplines = i.getDiscipline();
        boolean ok = projetController.suppDiscipline(projet,disciplines);
        if (ok) affMsg("Competence supprimé avec succes");
        else affMsg(" echec de la suppresion ");
    }

    private void listerDiscipline(Projet projet) {
        System.out.println("Investissement d'un projet : "+projet);
        List<Investissement> li = projetController.getInvestissement(projet);
        if (li.isEmpty())
            affMsg("aucune competence pour cette employe");
        else affList(li);
    }



}
