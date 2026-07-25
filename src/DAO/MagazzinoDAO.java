package DAO;

import DBConnection.Command.DbOperationExecutor;
import DBConnection.Command.IDbOperation;
import DBConnection.Command.ReadOperation;
import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Magazzino;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MagazzinoDAO implements IMagazzinoDAO {

    private static MagazzinoDAO instance = new MagazzinoDAO();
    private Magazzino magazzino;
    private static IDbConnection conn;
    private static ResultSet rs;

    private MagazzinoDAO() {
        magazzino = null;
        conn = null;
        rs = null;
    }

    public static MagazzinoDAO getInstance() {
        return instance;
    }

    public int add(Magazzino magazzino) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO magazzino (Idmagazzino, Nome) VALUES ('" + magazzino.getIdMagazzino() + "', '" + magazzino.getNome() + "');");
        conn.close();
        return rowCount;
    }


    public int removeByName(String nome) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM magazzino WHERE Nome = '" + nome + "';");
        conn.close();
        return rowCount;
    }

    public ArrayList<Magazzino> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Idmagazzino, Nome FROM magazzino");
        ArrayList<Magazzino> magazzini = new ArrayList<>();

        try {
            while (rs.next()) {
                magazzino = new Magazzino();
                magazzino.setIdMagazzino(rs.getInt("Idmagazzino"));
                magazzino.setNome(rs.getString("Nome"));
                magazzini.add(magazzino);
            }
            return magazzini;
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

    public int getIdMagazzinoByIdProdotto(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Idmagazzino FROM prodotti_magazzino WHERE IdProdotto = '" + id + "';";
        rs = conn.executeQuery(sql);

        try {
            rs.next();
            if (rs.getRow() == 1) {
                return rs.getInt("Idmagazzino");
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

    public int getIdMagazzinoByIdProdottoComposito(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Idmagazzino FROM prodotti_magazzino WHERE IdProdotto_composito = '" + id + "';";
        rs = conn.executeQuery(sql);

        try {
            rs.next();
            if (rs.getRow() == 1) {
                return rs.getInt("Idmagazzino");
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


}
