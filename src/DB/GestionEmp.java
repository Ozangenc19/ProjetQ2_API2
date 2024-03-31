package DB;



import Informatique.metier.Employe;
import myconnections.DBConnection;

import java.sql.*;
import java.util.Scanner;

/**
 * @author Ozan Genc
 */
public class GestionEmp {
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

        System.out.println("Matricule : ");
        String matricule = sc.nextLine();
        System.out.println("Nom : ");
        String nom = sc.nextLine();
        System.out.println("Prenom : ");
        String prenom = sc.nextLine();
        System.out.println("Tel : ");
        String tel = sc.nextLine();
        System.out.println("Mail : ");
        String mail = sc.nextLine();


        String query1 = "insert into API2_EMPLOYE(matricule,nom,prenom,tel,mail) values(?,?,?,?,?)";
        String query2 = "select id_employe FROM API2_EMPLOYE where matricule=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,matricule);
            pstm1.setString(2,nom);
            pstm1.setString(3,prenom);
            pstm1.setString(4,tel);
            pstm1.setString(5,mail);
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,matricule);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idemploye= rs.getInt(1);
                    System.out.println("idemploye = "+idemploye);
                }
                else System.out.println("record introuvable");
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }

    public void recherche() {
        System.out.println("id de l'employé recherché ");
        int idrech = sc.nextInt();
        String query = "select * from API2_EMPLOYE where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);


                Employe emp = new Employe(idrech,matricule,nom,prenom,tel,mail);
                System.out.println(emp);
            }
            else System.out.println("record introuvable");
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    public void modification() {
        System.out.println("id de l'employé recherché ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau téléphone ");
        String nvtel = sc.nextLine();
        String query = "update API2_EMPLOYE set tel = ? where id_employe = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,nvtel);
            pstm.setInt(2,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }

    public void suppression() {
        System.out.println("id de l'employé recherché ");
        int idrech = sc.nextInt();
        String query = "delete from API2_EMPLOYE where id_employe = ?";
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
        String query = "select * from API2_EMPLOYE";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idrech = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);

                Employe emp = new Employe(idrech,matricule,nom,prenom,tel,mail);
                System.out.println(emp);
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
    }



    public static void main(String []args){
        GestionEmp g = new GestionEmp();
        g.gestion();
    }
}
