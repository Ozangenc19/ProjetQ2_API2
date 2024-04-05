package mvc;

import mvc.controller.EmployeController;
import mvc.model.*;
import mvc.view.EmployeViewConsole;
import mvc.view.EmployeAbstractView;
import mvc.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestInfo{
    private DAOEmploye em;
    private EmployeController ec;
    private EmployeAbstractView ev;



    public void gestion(){
        em = new EmployeModelDB();

        ev = new EmployeViewConsole();
        ec = new EmployeController(em, ev);

        List<String> loptions = Arrays.asList("Employés","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: ev.menu();
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
