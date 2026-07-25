package DAO;

import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Cliente;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO {

    private static ClienteDAO instance = new ClienteDAO();
    private Cliente cliente;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ClienteDAO() {
        cliente = null;
        conn = null;
        rs = null;
    }

    public static ClienteDAO getInstance() {
        return instance;
    }

    public int addById(int idCliente, int idPuntoVendita) {
        conn = DbConnection.getInstance();
        String sql = "INSERT INTO cliente (Idcliente, Idpunto_vendita) VALUES ('" + idCliente +  "', '" + idPuntoVendita + "');";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }


    public Cliente findById(int id) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Idcliente,Idpunto_vendita,u.email FROM cliente as c INNER JOIN utente as u on u.idutente = c.idcliente WHERE c.Idcliente = '" + id + "';";
        rs = conn.executeQuery(sql);

        try {
            rs.next();
            if (rs.getRow() == 1) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("Idcliente"));
                cliente.setIdPuntoVendita(rs.getInt("Idpunto_vendita"));
                cliente.setEmail(rs.getString("u.email"));
                return cliente;
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


    public List<Cliente> getClientiByIdManager(int idManager) {
        conn = DbConnection.getInstance();
        String sql = "SELECT u.idutente, u.nome, u.cognome, u.email, u.stato FROM utente AS u\n" +
                "INNER JOIN cliente as c ON c.idcliente = u.idutente\n" +
                "INNER JOIN punto_vendita as pv ON pv.idpunto_vendita = c.idpunto_vendita\n" +
                "WHERE u.stato <> 'CANCELLATO' AND pv.idmanager = '" + idManager + "';";
        rs = conn.executeQuery(sql);
        ArrayList<Cliente> clienti = new ArrayList<>();

        try {
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("Idutente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setCognome(rs.getString("Cognome"));
                cliente.setEmail(rs.getString("Email"));
                cliente.setStato(Utente.Stato.valueOf(rs.getString("Stato")));
                clienti.add(cliente);
            }
            return clienti;
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

    public int updateStato(int idCliente, Utente.Stato stato) {

        conn = DbConnection.getInstance();
        String sql = "UPDATE utente SET Stato = '" + stato + "' " +
                "WHERE idutente = '" + idCliente + "';";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;

    }
}

