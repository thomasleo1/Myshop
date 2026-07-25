package DAO;

import DBConnection.Command.DbOperationExecutor;
import DBConnection.Command.IDbOperation;
import DBConnection.Command.ReadOperation;
import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.PuntoVendita;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuntoVenditaDAO implements IPuntoVenditaDAO {

    private static PuntoVenditaDAO instance = new PuntoVenditaDAO();
    private PuntoVendita puntoVendita;
    private static IDbConnection conn;
    private static ResultSet rs;

    private PuntoVenditaDAO() {
        puntoVendita = null;
        conn = null;
        rs = null;
    }

    public static PuntoVenditaDAO getInstance() {
        return instance;
    }

    public PuntoVendita findByName(String nome) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT Idpunto_vendita, Nome, Indirizzo, Idmanager, Idmagazzino FROM punto_vendita WHERE Nome = '" + nome + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("Idpunto_vendita"));
                puntoVendita.setNome(rs.getString("Nome"));
                puntoVendita.setIndirizzo(rs.getString("Indirizzo"));
                puntoVendita.setManager(rs.getInt("Idmanager"));
                puntoVendita.setMagazzino(rs.getInt("Idmagazzino"));
                return puntoVendita;
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


    public ArrayList<PuntoVendita> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Idpunto_vendita, Nome, Indirizzo, Idmanager, Idmagazzino FROM punto_vendita");
        ArrayList<PuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("Idpunto_vendita"));
                puntoVendita.setNome(rs.getString("Nome"));
                puntoVendita.setIndirizzo(rs.getString("Indirizzo"));
                puntoVendita.setManager(rs.getInt("Idmanager"));
                puntoVendita.setMagazzino(rs.getInt("Idmagazzino"));
                puntiVendita.add(puntoVendita);
            }
            return puntiVendita;
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

    @Override
    public int add(PuntoVendita puntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO punto_vendita (Nome, Indirizzo, Idmagazzino) VALUES ('" + puntoVendita.getNome() + "', '" + puntoVendita.getIndirizzo() + "', '" + puntoVendita.getMagazzino().getIdMagazzino() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeByName(String nome) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM punto_vendita WHERE nome = '" + nome + "';");
        conn.close();
        return rowCount;
    }


    public ArrayList<PuntoVendita> getPuntiVenditaSenzaManager() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Idpunto_vendita, Nome, Indirizzo, Idmanager, Idmagazzino FROM punto_vendita WHERE Idmanager IS NULL");
        ArrayList<PuntoVendita> puntiVendita = new ArrayList<>();
        try {
            while (rs.next()) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("Idpunto_vendita"));
                puntoVendita.setNome(rs.getString("Nome"));
                puntoVendita.setIndirizzo(rs.getString("Indirizzo"));
                puntoVendita.setManager(rs.getInt("Idmanager"));
                puntoVendita.setMagazzino(rs.getInt("Idmagazzino"));
                puntiVendita.add(puntoVendita);
            }
            return puntiVendita;
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

    public int updateManager(int idManager, PuntoVendita puntoVendita) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE punto_vendita SET Idmanager = '" + idManager + "' WHERE Idpunto_vendita = '" + puntoVendita.getIdPuntoVendita() + "';");
        conn.close();
        return rowCount;

    }

    public int associaProdotto(int idPuntoVendita, int idProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO associazione_punto_vendita_prodotti (Idpunto_vendita, IdProdotto) VALUES ('" + idPuntoVendita + "', '" + idProdotto + "');");
        conn.close();
        return rowCount;
    }

    public int associaProdottoMagazzino(int idMagazzino, int idProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO prodotti_magazzino (Idmagazzino, IdProdotto) VALUES ('" + idMagazzino + "', '" + idProdotto + "');");
        conn.close();
        return rowCount;
    }

    public int associaProdottoComposito(int idPuntoVendita, int idProdottoComposito) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO associazione_punto_vendita_prodotti (Idpunto_vendita, Idprodotto_composito) VALUES ('" + idPuntoVendita + "', '" + idProdottoComposito + "');");
        conn.close();
        return rowCount;
    }

    public int associaProdottoCompositoMagazzino(int idMagazzino, int idProdottoComposito) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO prodotti_magazzino (Idmagazzino, IdProdotto_composito) VALUES ('" + idMagazzino + "', '" + idProdottoComposito + "');");
        conn.close();
        return rowCount;
    }


    public PuntoVendita findById(int idPuntovendita) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Idpunto_vendita, Nome, Indirizzo, Idmanager, Idmagazzino FROM punto_vendita WHERE IdPunto_Vendita = '" + idPuntovendita + "';";
        rs = conn.executeQuery(sql);

        try {
            rs.next();
            if (rs.getRow() == 1) {
                puntoVendita = new PuntoVendita();
                puntoVendita.setIdPuntoVendita(rs.getInt("Idpunto_vendita"));
                puntoVendita.setNome(rs.getString("Nome"));
                puntoVendita.setIndirizzo(rs.getString("Indirizzo"));
                puntoVendita.setManager(rs.getInt("Idmanager"));
                puntoVendita.setMagazzino(rs.getInt("Idmagazzino"));
                return puntoVendita;
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


}
