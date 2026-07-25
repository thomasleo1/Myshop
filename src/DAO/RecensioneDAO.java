package DAO;

import DBConnection.Command.DbOperationExecutor;
import DBConnection.Command.IDbOperation;
import DBConnection.Command.ReadOperation;
import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Cliente;
import Model.Recensione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecensioneDAO implements IRecensioneDAO{

    private static RecensioneDAO instance = new RecensioneDAO();
    private Recensione recensione;
    private static IDbConnection conn;
    private static ResultSet rs;

    private RecensioneDAO() {
        recensione = null;
        conn = null;
        rs = null;
    }

    public static RecensioneDAO getInstance() {
        return instance;
    }


    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM recensione WHERE Idrecensione = '" + id + "';");
        conn.close();
        return rowCount;
    }

    public Recensione findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT IdRecensione, Testo, Visualizzato, Data, Idcliente, Feedback, risposta FROM recensione WHERE Idrecensione = '" + id + "';";
        rs = conn.executeQuery(sql);

        try {
            rs.next();
            if (rs.getRow() == 1) {
                recensione = new Recensione();
                recensione.setIdRecensione(rs.getInt("Idrecensione"));
                recensione.setTesto(rs.getString("Testo"));
                recensione.setVisualizzato(rs.getBoolean("Visualizzato"));
                recensione.setData(rs.getDate("Data"));
                recensione.setCliente(rs.getInt("Idcliente"));
                recensione.setFeedback(Recensione.Feedback.valueOf(rs.getString("Feedback")));
                recensione.setRisposta(rs.getString("Risposta"));
                return recensione;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Recensione> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Idrecensione, Testo, Visualizzato, Data, Idcliente, Feedback, risposta FROM recensione");
        ArrayList<Recensione> recensioni = new ArrayList<>();

        try {
            while (rs.next()) {
                recensione = new Recensione();
                recensione.setIdRecensione(rs.getInt("Idrecensione"));
                recensione.setTesto(rs.getString("Testo"));
                recensione.setVisualizzato(rs.getBoolean("Visualizzato"));
                recensione.setData(rs.getDate("Data"));
                recensione.setCliente(rs.getInt("Idcliente"));
                recensione.setFeedback(Recensione.Feedback.valueOf(rs.getString("Feedback")));
                recensione.setRisposta(rs.getString("Risposta"));
                recensioni.add(recensione);
            }
            return recensioni;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    public int addRecensione(String commento, Date date, int idCliente, Recensione.Feedback feedback) {
        conn = DbConnection.getInstance();
        String sql = "INSERT INTO recensione (Testo, Data, Idcliente, Feedback) VALUES ('" + commento + "', '" + date + "', '" + idCliente +  "', '" + feedback +"');";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public int getIdRecensione(String commento, Date date, int idCliente, Recensione.Feedback feedback) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Idrecensione FROM recensione WHERE Testo = '" + commento + "' AND Data = '" + date + "' AND Idcliente = '" +  idCliente + "' AND Feedback = '" + feedback + "';";
        rs = conn.executeQuery(sql);

        try {
            rs.next();
            if (rs.getRow() == 1) {
                return  rs.getInt("Idrecensione");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return 0;
    }

    public ArrayList<Recensione> getRecensioniByIdCliente(int idCliente) {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Idrecensione, Testo, Visualizzato, Data, Idcliente, Feedback, risposta FROM recensione WHERE Idcliente = '" + idCliente + "';");
        ArrayList<Recensione> recensioni = new ArrayList<>();

        try {
            while (rs.next()) {
                recensione = new Recensione();
                recensione.setIdRecensione(rs.getInt("Idrecensione"));
                recensione.setTesto(rs.getString("Testo"));
                recensione.setVisualizzato(rs.getBoolean("Visualizzato"));
                recensione.setData(rs.getDate("Data"));
                recensione.setCliente(rs.getInt("Idcliente"));
                recensione.setFeedback(Recensione.Feedback.valueOf(rs.getString("Feedback")));
                recensione.setRisposta(rs.getString("Risposta"));
                recensioni.add(recensione);
            }
            return recensioni;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;
    }

    public ArrayList<Recensione> getRecensioniByIdManager(int idManager) {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT r.Idrecensione, Testo, Visualizzato, Data, r.Idcliente, Feedback, risposta, u.email FROM recensione as r " +
                "INNER JOIN cliente as c on c.idcliente = r.idcliente " +
                "INNER JOIN utente as u on u.idutente = c.idcliente " +
                "INNER JOIN punto_vendita as pv on pv.idpunto_vendita = c.idpunto_vendita " +
                "WHERE r.risposta IS NULL AND r.visualizzato = 0 AND pv.Idmanager = '" + idManager + "';");
        ArrayList<Recensione> recensioni = new ArrayList<>();

        try {
            while (rs.next()) {
                recensione = new Recensione();
                recensione.setIdRecensione(rs.getInt("Idrecensione"));
                recensione.setTesto(rs.getString("Testo"));
                recensione.setVisualizzato(rs.getBoolean("Visualizzato"));
                recensione.setData(rs.getDate("Data"));
                recensione.setCliente(rs.getInt("Idcliente"));
                recensione.setFeedback(Recensione.Feedback.valueOf(rs.getString("Feedback")));
                recensione.setRisposta(rs.getString("Risposta"));
                recensione.setCliente(rs.getInt("r.Idcliente"));
                recensioni.add(recensione);
            }
            return recensioni;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return null;

    }

    public int updateVisualizzato(int idRecensione) {
        conn = DbConnection.getInstance();
        String sql = "UPDATE recensione SET Visualizzato = 1 WHERE Idrecensione = '" + idRecensione + "';";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public int updateRisposta(int idRecensione, String risposta) {
        conn = DbConnection.getInstance();
        String sql = "UPDATE recensione SET Risposta = '" + risposta + "' WHERE Idrecensione = " + idRecensione + ";";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }
}

