package DAO;

import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Collocazione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollocazioneDAO implements ICollocazioneDAO {

    private static CollocazioneDAO instance = new CollocazioneDAO();
    private Collocazione collocazione;
    private static IDbConnection conn;
    private static ResultSet rs;

    private CollocazioneDAO() {
        collocazione = null;
        conn = null;
        rs = null;
    }

    public static CollocazioneDAO getInstance() {
        return instance;
    }
    public ArrayList<Collocazione> findAllByIdMagazzino(int idMagazzino) {
        conn = DbConnection.getInstance();
        String sql = "SELECT c.IdCollocazione AS Idcollocazione ,Corsia, Scaffale, Idmagazzino, p.Idcollocazione, pc.Idcollocazione FROM collocazione AS c\n" +
                "LEFT JOIN prodotto AS p ON p.Idcollocazione = c.Idcollocazione\n" +
                "LEFT JOIN prodotto_composito AS pc ON pc.Idcollocazione = c.Idcollocazione\n" +
                "WHERE p.idCollocazione IS NULL AND pc.idcollocazione IS NULL AND Idmagazzino = " + idMagazzino + ";";
        rs = conn.executeQuery(sql);
        ArrayList<Collocazione> collocazioni = new ArrayList<>();

        try {
            while (rs.next()) {
                collocazione = new Collocazione();
                collocazione.setIdCollocazione(rs.getInt("Idcollocazione"));
                collocazione.setCorsia(rs.getInt("Corsia"));
                collocazione.setScaffale(rs.getInt("Scaffale"));
                collocazione.setMagazzino(rs.getInt("Idmagazzino"));
                collocazioni.add(collocazione);
            }
            return collocazioni;
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
}
