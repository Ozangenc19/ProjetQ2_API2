package mvc.view;

import Informatique.metier.Disciplines;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static utilitaires.Utilitaire.*;
import static utilitaires.Utilitaire.modifyIfNotBlank;


public class DisciplineViewConsole extends DisciplineAbstractView {

    private Scanner sc = new Scanner(System.in);

    @Override
    public void affMsg(String msg) {
        System.out.println("information:" + msg);
    }

    @Override
    public void affList(List l) {
        for (Object obj : l) {
            if (obj instanceof Disciplines) {
                Disciplines discipline = (Disciplines) obj;
                System.out.println(discipline);
            }
        }

    }

    @Override
    public void menu() {
        update(disciplineController.getAll());
        do {
            int ch = choixListe(Arrays.asList("Ajouter", "Supprimer", "Rechercher", "Modifier", "Fin"));

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

    public void ajouter() {
        System.out.print("nom de la discipline : ");
        String nom = sc.next();
        System.out.print("description de la discipline : ");
        String description = sc.next();

        Disciplines dis = disciplineController.addDiscipline(new Disciplines(0, nom, description));
        if (dis != null) affMsg("création de :" + dis);
        else affMsg("erreur de création");
    }

    public void supprimer() {
        int nl = choixElt(ld);
        Disciplines dis = ld.get(nl - 1);
        boolean ok = disciplineController.removeDiscipline(dis);
        if (ok) affMsg("produit effacé");
        else affMsg("produit non effacé");
    }

    public void rechercher() {
        System.out.printf("idDiscipline : ");
        int idDiscipline = sc.nextInt();
        disciplineController.search(idDiscipline);
    }

    public void modifier() {
        int nl = choixElt(ld);

        Disciplines dis = ld.get(nl - 1);
        String nom = modifyIfNotBlank("nom de la discipline ", dis.getNom());
        String description = modifyIfNotBlank("description", dis.getDescription());
        Disciplines dismaj = disciplineController.updateDiscipline(new Disciplines(dis.getId_discipline(), nom, description));
        if (dismaj == null) affMsg("mise à jour infructueuse");
        else affMsg("mise à jour effectuée : " + dismaj);
    }

    @Override
    public Disciplines selectionner() {
        update(disciplineController.getAll());
        int nl = choixListe(ld);
        Disciplines dis = ld.get(nl - 1);
        return dis;
    }


}
