package mvc.view;

import Informatique.metier.Employe;
import mvc.controller.EmployeController;

import java.math.BigDecimal;
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

    public void menu(){
        update(empController.getAll());
        do{
            int ch = choixListe(Arrays.asList("Ajout", "Retrait", "Rechercher", "Modifier", "Fin"));

            switch(ch){
                case 1: ajouter();
                    break;
                case 2 : retirer();
                    break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }



    private void modifier() {
        int nl = choixElt(le);

        Employe emp = le.get(nl-1);
        String matricule= modifyIfNotBlank("Matricule de l'employé : ",emp.getMatricule());
        String nom= modifyIfNotBlank("Nom : ",emp.getNom());
        String prenom = modifyIfNotBlank("Prenom : ",emp.getPrenom());
        String tel = modifyIfNotBlank("Tel : ",emp.getTel());
        String mail = modifyIfNotBlank("Mail : ",emp.getMail());
        Employe employemaj =  empController.updateEmploye(new Employe(emp.getId_employe(),matricule, nom, prenom, tel, mail));
        if(employemaj==null) affMsg("mise à jour infrucueuse");
        else affMsg("mise à jour effectuée : "+employemaj);
    }

    private void rechercher() {
        System.out.println("idEmploye : ");
        int idEmploye = sc.nextInt();
        empController.search(idEmploye);
    }

    private void retirer() {

        int nl = choixElt(le);
        Employe ep = le.get(nl-1);
        boolean ok = empController.removeEmploye(ep);
        if(ok) affMsg("Employé effacé");
        else affMsg("Employé non effacé");
    }

    private void ajouter() {
        System.out.print("Matricule de l'employé : ");
        String matricule = sc.nextLine();
        System.out.print("Nom : ");
        String nom = sc.nextLine();
        System.out.print("Prénom : ");
        String prenom = sc.nextLine();
        System.out.print("Téléphone : ");
        String tel = sc.nextLine();
        System.out.print("Mail : ");
        String mail = sc.nextLine();
        Employe ep = empController.addEmploye(new Employe(0, matricule, nom, prenom, tel, mail)) ;
        if(ep!=null) affMsg("création de :"+ep);
        else affMsg("erreur de création");
    }

    @Override
    public Employe selectionner(){
        update(empController.getAll());
        int nl =  choixListe(le);
        Employe ep = le.get(nl-1);
        return ep;
    }


}
