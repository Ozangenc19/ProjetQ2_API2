package mvc.model;

import Informatique.metier.Disciplines;
import Informatique.metier.Employe;
import Informatique.metier.Investissement;
import Informatique.metier.Projet;

import myconnections.DBConnection;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetModelDB extends DAOProjet {
    protected Connection dbConnect;

    public ProjetModelDB() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.err.println("erreur de connexion");

            System.exit(1);
        }
    }

    @Override
    public Projet addProjet(Projet projet) {
        String query1 = "insert into API2_PROJET(nom,datedebut,datefin,cout,id_employe) values(?,?,?,?,?)";
        String query2 = "select id_projet from API2_EMPLOYE where id_employe = ?";
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);
        ) {
            pstm1.setString(1, projet.getNom());
            pstm1.setDate(2, projet.getDateDebut() != null ? Date.valueOf(projet.getDateDebut()) : null);
            pstm1.setDate(2, projet.getDateFin() != null ? Date.valueOf(projet.getDateFin()) : null);
            pstm1.setBigDecimal(4, projet.getCout());
            pstm1.setInt(5, projet.getResponsable().getId_employe());
            int n = pstm1.executeUpdate();
            if (n == 1) {
                pstm2.setInt(1, projet.getId_projet());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next()) {
                    int idprojet = rs.getInt(1);
                    projet.setId_projet(idprojet);
                    notifyObservers();
                    return projet;
                } else {

                    System.err.println("record introuvable");
                    return null;
                }
            } else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public boolean removeProjet(Projet projet) {
        String query = "delete from API2_PROJET where id_projet = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, projet.getId_projet());
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
    public Projet updateProjet(Projet projet) {
        String query = "update API2_PROJET set nom =?,datedebut=?,datefin=?,cout=?,id_employe=? where id_projet = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1, projet.getNom());
            pstm.setDate(2, projet.getDateDebut() != null ? Date.valueOf(projet.getDateDebut()) : null);
            pstm.setDate(2, projet.getDateFin() != null ? Date.valueOf(projet.getDateFin()) : null);
            pstm.setBigDecimal(4, projet.getCout());
            pstm.setInt(5, projet.getResponsable().getId_employe());
            pstm.setInt(6, projet.getId_projet());
            int n = pstm.executeUpdate();
            notifyObservers();
            if (n != 0) return readProjet(projet.getId_projet());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Projet readProjet(int idProjet) {
        String query = "select * from API2_PROJET where id_projet = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, idProjet);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int id_employe = rs.getInt(2);
                String matricule = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String tel = rs.getString(6);
                String mail = rs.getString(7);
                Employe emp = new Employe(id_employe, matricule, nom, prenom, tel, mail);

                int id_projet = rs.getInt(8);
                String nom_projet = rs.getString(9);
                Date date = rs.getDate(10);
                LocalDate datedebut = date != null ? date.toLocalDate() : null;
                date = rs.getDate(11);
                LocalDate datefin = date != null ? date.toLocalDate() : null;
                BigDecimal cout = rs.getBigDecimal(12);
                return new Projet(id_projet, nom_projet, datedebut, datefin, cout, emp);


            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public List<Projet> getProjets() {
        List<Projet> lp = new ArrayList<>();
        String query = "select * from API2_PROJET";
        try (Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int id_employe = rs.getInt(2);
                String matricule = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String tel = rs.getString(6);
                String mail = rs.getString(7);
                Employe emp = new Employe(id_employe, matricule, nom, prenom, tel, mail);

                int id_projet = rs.getInt(8);
                String nom_projet = rs.getString(9);
                Date date = rs.getDate(10);
                LocalDate datedebut = date != null ? date.toLocalDate() : null;
                date = rs.getDate(11);
                LocalDate datefin = date != null ? date.toLocalDate() : null;
                BigDecimal cout = rs.getBigDecimal(12);
                Projet projet = new Projet(id_projet, nom_projet, datedebut, datefin, cout, emp);
                lp.add(projet);
            }
            return lp;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public boolean addDiscipline(Projet projet, Disciplines disciplines, int quantiteJH) {
        String query = "insert into API2_INVESTISSEMENT(id_projet,id_discipline,quantiteJH) values(?,?,?)";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, projet.getId_projet());
            pstm.setInt(2, disciplines.getId_discipline());
            pstm.setInt(3, quantiteJH);
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return false;
        }
    }

    @Override
    public boolean updateDiscipline(Projet projet, Disciplines disciplines, int quantiteJH) {
        String query = "update API2_INVESTISSEMENT set quantiteJH=? where id_projet=? and id_discipline=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, quantiteJH);
            pstm.setInt(2, projet.getId_projet());
            pstm.setInt(3, disciplines.getId_discipline());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql: " + e);
            return false;
        }
    }

    @Override
    public boolean removeDiscipline(Projet projet, Disciplines disciplines) {
        String query = "delete from API2_INVESTISSEMENT where id_projet=? and id_discipline=?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, projet.getId_projet());
            pstm.setInt(2, disciplines.getId_discipline());
            int n = pstm.executeUpdate();
            if (n != 0) return true;
            else return false;
        } catch (SQLException e) {
            System.err.println("erreur sql : " + e);
            return false;
        }
    }

    @Override
    public List<Investissement> getProjets(Projet projet) {

        return List.of();
    }

    @Override
    public List getNotification() {

        return List.of();
    }
}
