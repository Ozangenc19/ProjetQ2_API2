package mvc.model;

import Informatique.metier.Employe;
import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeModelDB extends DAOEmploye {
    protected Connection dbConnect;

    public EmployeModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }
    }


    @Override
    public Employe addEmploye(Employe employe) {
        String query1 = "insert into API2_EMPLOYE(matricule, nom, prenom, tel, mail) values(?,?,?,?,?)";
        String query2 = "select id_employe from API2_EMPLOYE where matricule= ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, employe.getMatricule());
            pstm1.setString(2, employe.getNom());
            pstm1.setString(3, employe.getPrenom());
            pstm1.setString(4, employe.getTel());
            pstm1.setString(5, employe.getMail());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setString(1, employe.getMatricule());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idemploye = rs.getInt(1);
                    employe.setId_employe(idemploye);
                    notifyObservers();
                    return employe;
                } else {

                    System.err.println("record introuvable");
                    return null;
                }
            } else return null;

        } catch (SQLException e) {
            //System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeEmploye(Employe employe) {
        String query = "delete from API2_EMPLOYE where id_employe = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, employe.getId_employe());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return false;
        }

    }

    @Override
    public Employe updateEmploye(Employe employe) {
        String query = "update API2_EMPLOYE set matricule =?,nom=?,prenom=?,tel=?,mail=? where id_employe = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, employe.getMatricule());
            pstm.setString(2, employe.getNom());
            pstm.setString(3, employe.getPrenom());
            pstm.setString(4, employe.getTel());
            pstm.setString(5, employe.getMail());
            pstm.setInt(6, employe.getId_employe());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readEmploye(employe.getId_employe());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Employe readEmploye(int id_employe) {
        String query = "select * from API2_EMPLOYE where id_employe = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, id_employe);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe emp = new Employe(id_employe, matricule, nom, prenom, tel, mail);
                return emp;

            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public List<Employe> getEmployes() {
        List<Employe> le = new ArrayList<>();
        String query = "select * from API2_EMPLOYE";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id_employe = rs.getInt(1);
                String matricule = rs.getString(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String tel = rs.getString(5);
                String mail = rs.getString(6);
                Employe emp = new Employe(id_employe, matricule, nom, prenom, tel, mail);
                le.add(emp);
            }
            return le;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }


    @Override
    public List getNotification() {
        return getEmployes();
    }
}

