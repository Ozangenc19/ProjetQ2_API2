package mvc.model;

import Informatique.metier.*;

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
            pstm1.setDate(3, projet.getDateFin() != null ? Date.valueOf(projet.getDateFin()) : null);
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
        String query1 = "select * from API2_PROJET where id_projet = ?";
        String query2 = "select * from API2_EMPLOYE where id_employe = ?";

        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2);) {
            pstm1.setInt(1, idProjet);
            ResultSet rs = pstm1.executeQuery();
            if (rs.next()) {
                String nom = rs.getString(2);
                LocalDate datedebut = rs.getDate(3).toLocalDate();
                LocalDate datefin = rs.getDate(4).toLocalDate();
                BigDecimal cout = rs.getBigDecimal(5);
                int idEmploye = rs.getInt(6);

                pstm2.setInt(1, idEmploye);
                ResultSet rs2 = pstm2.executeQuery();
                Employe emp = null;
                if (rs2.next()) {
                    String matricule = rs2.getString(2);
                    String nom_emp = rs2.getString(3);
                    String prenom = rs2.getString(4);
                    String tel = rs2.getString(5);
                    String mail = rs2.getString(6);
                    emp = new Employe(idEmploye, matricule, nom_emp, prenom, tel, mail);
                }
                return new Projet(idProjet, nom, datedebut, datefin, cout, emp);
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
        String query1 = "select * from API2_PROJET";
        String query2 = "select * from API2_EMPLOYE where id_employe = ?";

        try (Statement stm = dbConnect.createStatement();
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {

            ResultSet rs = stm.executeQuery(query1);
            while (rs.next()) {
                int idProjet = rs.getInt(1);
                String nom = rs.getString(2);
                LocalDate datedebut = rs.getDate(3).toLocalDate();
                LocalDate datefin = rs.getDate(4).toLocalDate();
                BigDecimal cout = rs.getBigDecimal(5);
                int idEmploye = rs.getInt(6);

                pstm2.setInt(1, idEmploye);
                ResultSet rsEmp = pstm2.executeQuery();
                Employe emp = null;
                if (rsEmp.next()) {
                    int id_employe = rsEmp.getInt(1);
                    String matricule = rsEmp.getString(2);
                    String nom_emp = rsEmp.getString(3);
                    String prenom = rsEmp.getString(4);
                    String tel = rsEmp.getString(5);
                    String mail = rsEmp.getString(6);
                    emp = new Employe(id_employe, matricule, nom_emp, prenom, tel, mail);
                }
                Projet p = new Projet(idProjet, nom, datedebut, datefin, cout, emp);
                lp.add(p);
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
    public boolean modifDiscipline(Projet projet, Disciplines disciplines, int quantiteJH) {
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
    public boolean suppDiscipline(Projet projet, Disciplines disciplines) {
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
    public List<Investissement> listeDisciplinesEtInvestissement(Projet projet) {
        String query1 = "SELECT * FROM API2_INVESTISSEMENT WHERE id_projet = ?";
        String query2 = "SELECT * FROM API2_DISCIPLINE WHERE id_discipline = ?";
        List<Investissement> li = new ArrayList<>();
        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2)) {
            pstm1.setInt(1, projet.getId_projet());
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {
                int id_investissement = rs1.getInt(1);
                int quantiteJH = rs1.getInt(2);
                int id_discicipline = rs1.getInt(3);
                pstm2.setInt(1, id_discicipline);
                ResultSet rs2 = pstm2.executeQuery();
                Disciplines dis = null;
                if (rs2.next()) {
                    String nom = rs2.getString(2);
                    String desccription = rs2.getString(3);
                    dis = new Disciplines(id_discicipline,nom,desccription);
                }
                Investissement invest = new Investissement(id_investissement, quantiteJH, dis,projet);
                li.add(invest);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return li;
    }

    @Override
    public List<Competence> niveauResponsableDiscipline(Projet projet) {
        String query1 = "select c.id_discipline, c.id_employe, c.niveau, c.id_competence, from API2_COMPETENCE c join API2_PROJET p on c.id_employe = p.id_employe where p.id_projet = ? and c.id_employe = ?";
        String query2 = "SELECT nom, description FROM API2_DISCIPLINE WHERE id_discipline = ?";

        List<Competence> lc = new ArrayList<>();

        try (PreparedStatement pstm1 = dbConnect.prepareStatement(query1);
             PreparedStatement pstm2 = dbConnect.prepareStatement(query2))
             {
            pstm1.setInt(1, projet.getId_projet());
            pstm1.setInt(2, projet.getResponsable().getId_employe());
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {

                int idDisc = rs1.getInt(1);
                int niveau = rs1.getInt(2);
                int idCompet = rs1.getInt(3);
                pstm2.setInt(1, idDisc);
                ResultSet rs2 = pstm2.executeQuery();
                Disciplines discipline = null;

                if (rs2.next()) {
                    String nom = rs2.getString(2);
                    String description = rs2.getString(3);
                    discipline = new Disciplines(idDisc, nom, description);
                }

                Competence comp = new Competence(idCompet,niveau,discipline,projet.getResponsable());
                lc.add(comp);
            }
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e);
        }

        return lc;
    }

    @Override
    public int investissementTotal(Projet projet) {
        String query = "{ ? = call total_investissement_projet(?) }"; //On utilise la fonction total_investissements crée sur oracle
        try (CallableStatement cs = dbConnect.prepareCall(query)) {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setInt(2, projet.getId_projet());
            cs.executeQuery();
            return cs.getInt(1);
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
            return -1;
        }
    }




    @Override
    public List<Investissement> getInvestissement(Projet projet) {
        String query = "select * from API2_INVESTISSEMENT where id_investissement ";
        List<Investissement> li = new ArrayList<>();
        try (PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1, projet.getId_projet());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idInvest = rs.getInt(1);
                int quantiteJH = rs.getInt(2);
                Investissement invest = new Investissement(idInvest,quantiteJH,projet);
                li.add(invest);
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);
        }
        return li;
    }

    @Override
    public List getNotification() {

        return getProjets();
    }
}
