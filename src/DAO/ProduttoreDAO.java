package DAO;

import DBConnection.Command.DbOperationExecutor;
import DBConnection.Command.IDbOperation;
import DBConnection.Command.ReadOperation;
import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Produttore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduttoreDAO implements IProduttoreDAO {

    private static ProduttoreDAO instance = new ProduttoreDAO();
    private Produttore produttore;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProduttoreDAO() {
        produttore = null;
        conn = null;
        rs = null;
    }

    public static ProduttoreDAO getInstance() {
        return instance;
    }

    public int add(Produttore produttore) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO produttore (Nome, SitoWeb, Citta, Nazione) VALUES ('" + produttore.getNome() + "', '" + produttore.getSitoWeb() + "', '" + produttore.getCitta() + "', '" + produttore.getNazione() + "');");
        conn.close();
        return rowCount;
    }

    public int removeByName(String nome) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM produttore WHERE Nome = '" + nome + "';");
        conn.close();
        return rowCount;
    }


    public ArrayList<Produttore> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Idproduttore, Nome, SitoWeb, Citta, Nazione FROM produttore");
        ArrayList<Produttore> produttori = new ArrayList<>();

        try {
            while (rs.next()) {
                produttore = new Produttore();
                produttore.setIdProduttore(rs.getInt("Idproduttore"));
                produttore.setNome(rs.getString("Nome"));
                produttore.setSitoWeb(rs.getString("SitoWeb"));
                produttore.setCitta(rs.getString("Citta"));
                produttore.setNazione(rs.getString("Nazione"));
                produttori.add(produttore);
            }
            return produttori;
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
