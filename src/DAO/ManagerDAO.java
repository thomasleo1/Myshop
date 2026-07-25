package DAO;

import DBConnection.Command.DbOperationExecutor;
import DBConnection.Command.IDbOperation;
import DBConnection.Command.ReadOperation;
import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDAO implements IManagerDAO{

    private static ManagerDAO instance = new ManagerDAO();
    private Manager manager;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ManagerDAO() {
        manager = null;
        conn = null;
        rs = null;
    }

    public static ManagerDAO getInstance() {
        return instance;
    }

    public int addById(Manager manager, int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO manager (Idmanager, Salario) VALUES ('" + id + "', '" + manager.getSalario() + "');");
        conn.close();
        return rowCount;
    }

    public int update(Manager manager) {
        conn = DbConnection.getInstance();
        //int rowCount = conn.executeUpdate("UPDATE manager SET Nome = '" + manager.getNome() + "' Idlista_prodotti = " + catalogo.getArticoli() + "' WHERE Idcatalogo = '" + catalogo.getIdCatalogo() + "';");
        conn.close();
        return 1;
    }

    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM catalogo WHERE Idcatalogo = '" + id + "';");
        conn.close();
        return rowCount;
    }

    public Manager findByUsername(String username) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT Idmanager, Salario, u.Username FROM myshop.manager AS m\n" +
                "INNER JOIN myshop.utente as u ON u.Idutente = m.Idmanager\n" +
                "WHERE u.Username = '" + username + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                manager = new Manager();
                manager.setIdManager(rs.getInt("Idmanager"));
                manager.setSalario(rs.getFloat("Salario"));
                return manager;
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

    public ArrayList<Manager> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Idmanager, Salario, u.Nome FROM manager INNER JOIN myshop.utente AS u on Idmanager = u.Idutente");
        ArrayList<Manager> managers = new ArrayList<>();

        try {
            while (rs.next()) {
                manager = new Manager();
                manager.setIdManager(rs.getInt("Idmanager"));
                manager.setSalario(rs.getFloat("Salario"));
                manager.setNome(rs.getString("Nome"));
                managers.add(manager);
            }
            return managers;
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
