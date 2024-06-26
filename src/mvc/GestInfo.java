package mvc;

import mvc.controller.DisciplineController;
import mvc.controller.EmployeController;
import mvc.controller.ProjetController;
import mvc.model.*;
import mvc.view.EmployeViewConsole;
import mvc.view.EmployeAbstractView;
import mvc.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestInfo{
    public static DAOEmploye em;

    public static DAOProjet pm;
    public static DAODiscipline dm;


    public static EmployeController ec;
    public static ProjetController pc;
    public static DisciplineController dc;

    public static EmployeAbstractView ev;
    public static ProjetAbstractView pv;
    public static DisciplineAbstractView dv;

    public void gestion(){

        em = new EmployeModelDB();
        pm = new ProjetModelDB();
        dm = new DisciplineModelDB();


        ev = new EmployeViewConsole();
        pv = new ProjetViewConsole();
        dv = new DisciplineViewConsole();


        ec = new EmployeController(em, ev);
        pc = new ProjetController(pm,pv);
        dc = new DisciplineController(dm,dv);


        List<String> loptions = Arrays.asList("Employe","Projet","Discipline","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: ev.menu();
                    break;
                case 2: pv.menu();
                    break;
                case 3: dv.menu();
                    break;
                case 4: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
        GestInfo gi = new GestInfo();
        gi.gestion();
    }
}
