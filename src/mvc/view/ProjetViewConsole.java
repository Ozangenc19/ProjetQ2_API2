package mvc.view;


import Informatique.metier.*;
import mvc.GestInfo;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        Projet projet;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            try {
                System.out.print("Nom du projet : ");
                String nom = sc.next();

                LocalDate datedebut = null;
                while (datedebut == null) {
                    System.out.print("Date de début (dd/MM/yyyy) : ");
                    String input = sc.next();
                    try {
                        datedebut = LocalDate.parse(input, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date invalide. Veuillez réessayer.");
                    }
                }

                LocalDate datefin = null;
                while (datefin == null) {
                    System.out.print("Date de fin (dd/MM/yyyy) : ");
                    String input = sc.next();
                    try {
                        datefin = LocalDate.parse(input, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Format de date invalide. Veuillez réessayer.");
                    }
                }
                BigDecimal cout = null;
                while (cout == null) {
                    System.out.print("Coût : ");
                    String input = sc.next();
                    try {
                        cout = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
                    } catch (NumberFormatException e) {
                        System.out.println("Valeur de coût invalide. Veuillez entrer un nombre valide.");
                    }
                }
                ;
                List<Employe> le = GestInfo.em.getEmployes();// on reprend la liste de tous les employes
                if (le.isEmpty()) {
                    System.out.println("Aucun employé disponible pour être responsable.");
                    return;
                }
                System.out.println("Responsable :");
                int ch = choixListe(le);

                if (ch <= 0 || ch > le.size()) {
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    continue;
                }
                System.out.println(le.get(ch - 1));
                projet = new Projet(nom, datedebut, datefin, cout, le.get(ch - 1));
                System.out.println("Projet créé : " + projet);
                projetController.addProjet(projet);
                break;
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        } while (true);
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
            int ch = choixListe(Arrays.asList("ajouter discipline", "modifier discipline", "supprimer discipline", "lister les disciplines", "lister un investissement", "investissement total", "competence du responsable", "menu principal"));

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
                    listerInvestissement(projet);
                    break;
                case 6:
                    investissementTotal(projet);
                    break;
                case 7:
                    niveauxResponsableDisciplines(projet);
                    break;
                case 8:
                    return;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void ajouterDiscipline(Projet projet) {
        List<Disciplines> ld = GestInfo.dm.getDisciplines();// on reprend la liste de toutes les disciplines
        System.out.println("Ajout d'une discipline");
        int choix = choixListe(ld);
        System.out.println("quantiteJH : ");
        int quantiteJH = sc.nextInt();
        boolean ok = projetController.addDiscipline(projet, ld.get(choix - 1), quantiteJH);
        if (ok) affMsg("discipline ajouté avec success");
        else affMsg(" erreur lord e l'ajout de la discipline");
    }

    private void modfifierDiscipline(Projet projet) {
        List<Investissement> li = projetController.listeDisciplinesEtInvestissement(projet);
        System.out.println("Modification d'une discipline");
        int choix = choixElt(li);
        System.out.println("quantiteJH: ");
        int quantitejH = sc.nextInt();
        boolean ok = projetController.modifDiscipline(projet, li.get(choix - 1).getDiscipline(), quantitejH);
        if (ok) affMsg("Mise à jour éffectuée");
        else affMsg(" echec de la mise à jour");
    }

    private void supprimerDiscipline(Projet projet) {
        List<Investissement> li = projetController.listeDisciplinesEtInvestissement(projet);
        System.out.println(" Suprresion d'un investissement ");
        int choix = choixElt(li);
        boolean ok = projetController.suppDiscipline(projet, li.get(choix - 1).getDiscipline());
        if (ok) affMsg("Discipline supprimé avec succes");
        else affMsg(" echec de la suppresion ");
    }

    private void listerDiscipline(Projet projet) {
        System.out.println("Discipline d'un projet : " + projet);
        List<Investissement> li = projetController.listeDisciplinesEtInvestissement(projet);
        if (li.isEmpty())
            affMsg("aucune discipline pour cette employe");
        else affList(li);
    }


    private void listerInvestissement(Projet projet) {
        System.out.println("Investissement d'un projet : " + projet);
        List<Investissement> li = projetController.getInvestissement(projet);
        if (li.isEmpty())
            affMsg("aucune discipline pour cette employe");
        else affList(li);
    }

    private void investissementTotal(Projet projet) {
        int total = projetController.investissementTotal(projet);

        if (total == 0) {
            affMsg("Aucun investissement pour ce projet");
        } else
            affMsg("Voici le total des investissement pour le projet '" + projet.getNom() + "' : " + total);
    }

    private void niveauxResponsableDisciplines(Projet projet) {
        List<Investissement> li = projetController.listeDisciplinesEtInvestissement(projet);
        List<Competence> lc = projetController.niveauResponsableDiscipline(projet);

        if (lc.isEmpty()) {
            affMsg("Aucune compétence");
        }

        System.out.println("Voici les compétences du responsable pour les disciplines du projet : ");
        for (Investissement i : li) {
            for (Competence c : lc) {
                if (i.getDiscipline().getId_discipline() == c.getDisciplines().getId_discipline()) ;
                System.out.println(c);
            }
        }
    }
}







