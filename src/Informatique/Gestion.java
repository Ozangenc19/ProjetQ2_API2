package Informatique;



import Informatique.metier.Projet;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Ozan Genc
 */
public class Gestion {
    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null){
            System.exit(1);
        }
        System.out.println("connexion établie");

        do {
            System.out.println("1.Ajout\n2.Recherche\n3.Modification\n4.Suppression\n5.Tous\n6.Fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch){
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    tous();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Votre choix est invalide Recommencez");
            }
        }while (true);
    }

    public void ajout() {

        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("Date de début : ");
        String dateDebut = sc.nextLine();
        System.out.println("Date de fin : ");
        String dateFin = sc.nextLine();
        System.out.println("Cout : ");
        String cout = sc.nextLine();

        String query1 = "insert into API2_PROJET(nom,dateDebut,dateFin,cout) values(?,?,?,?,?)";
        String query2 = "select id_projet FROM API2_EMPLOYE where nom=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(2,nom);
            pstm1.setString(3,dateDebut);
            pstm1.setString(4,dateFin);
            pstm1.setString(5,cout);
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,nom);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int id= rs.getInt(1);
                    System.out.println("idprojet = "+id);
                }
                else System.out.println("record introuvable");
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    public void recherche() {
        System.out.println("id du projet recherché ");
        int idrech = sc.nextInt();
        String query = "select * from API2_PROJET where id_projet = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                Date dateDebut = rs.getDate(3);
                Date dateFin = rs.getDate(5);
                BigDecimal cout = rs.getBigDecimal(6);

                Projet pj = new Projet(nom,dateDebut,dateFin,cout);
                System.out.println(pj);
            }
            else System.out.println("record introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    public void modification() {
        System.out.println("id du projet recherché ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau nom ");
        String nvnom = sc.nextLine();
        String query = "update API2_PROJET set nom = ? where id_projet = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,nvnom);
            pstm.setInt(2,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }

    public void suppression() {
        System.out.println("id du projet recherché ");
        int idrech = sc.nextInt();
        String query = "delete from API2_PROJET where id_projet = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    public void tous() {
        String query = "select * from API2_PROJET";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int id_projet = rs.getInt(1);
                String nom = rs.getString(2);
                Date dateDebut = rs.getDate(3);
                Date dateFin = rs.getDate(5);
                BigDecimal cout = rs.getBigDecimal(6);
                Projet pj = new Projet(id_projet,nom,dateDebut,dateFin,cout);
                System.out.println(pj);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }



    public static void main(String []args){
        Gestion g = new Gestion();
        g.gestion();
    }
}
