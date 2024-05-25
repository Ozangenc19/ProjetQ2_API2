package mvc.model;

import Informatique.metier.Disciplines;
import myconnections.DBConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplineModelDB extends DAODiscipline {
    protected Connection dbConnect;


    public DisciplineModelDB(){
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null){
            System.out.println("erreur de connexion");

            System.exit(1);
        }
    }

    @Override
    public Disciplines addDiscipline(Disciplines disciplines) {
        String query1 = "insert into API2_DISCIPLINES(nom,description) values(?,?)";
        String query2 = "select id_discipline from API2_DISCIPLINES where nom= ?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,disciplines.getNom());
            pstm1.setString(2,disciplines.getDescription());
            int n = pstm1.executeUpdate();
            if(n==1){
                pstm2.setString(1,disciplines.getNom());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int iddiscipline = rs.getInt(1);
                    disciplines.setId_discipline(iddiscipline);
                    notifyObservers();
                    return disciplines;
                }
                else {

                    System.err.println("record introuvable");
                    return null;
                }
            }
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public boolean removeDiscipline(Disciplines disciplines) {
        String query = "delete from API2_DISCIPLINES where id_discipline = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,disciplines.getId_discipline());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return false;
        }
    }

    @Override
    public Disciplines updateDiscipline(Disciplines disciplines) {
        String query = "update API2_DISCIPLINES set nom =?,description=? where id_discipline = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,disciplines.getNom());
            pstm.setString(2,disciplines.getDescription());
            pstm.setInt(3,disciplines.getId_discipline());
            int n = pstm.executeUpdate();
            notifyObservers();
            if(n!=0) return readDiscipline(disciplines.getId_discipline());
            else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :" + e);

            return null;
        }
    }

    @Override
    public Disciplines readDiscipline(int idDiscipline) {

        String query = "select * from API2_DISCIPLINE where id_discipline = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idDiscipline);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                String description = rs.getString(3);
                Disciplines dis = new Disciplines(idDiscipline,nom,description);
                return  dis;

            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List<Disciplines> getDisciplines() {
        List<Disciplines> ld = new ArrayList<>();
        String query="select * from API2_DISCIPLINES";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idDiscipline = rs.getInt(1);
                String nom = rs.getString(2);
                String description = rs.getString(3);
                Disciplines dis = new Disciplines(idDiscipline,nom,description);
                ld.add(dis);
            }
            return ld;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);

            return null;
        }
    }

    @Override
    public List getNotification() {
        return getDisciplines();
    }
}
