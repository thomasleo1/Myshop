package DAO;

import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.CategoriaServizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaServizioDAO implements ICategoriaServizioDAO {

    private static CategoriaServizioDAO instance = new CategoriaServizioDAO();
    private CategoriaServizio categoriaServizio;
    private static IDbConnection conn;
    private static ResultSet rs;

    private CategoriaServizioDAO() {
        categoriaServizio = null;
        conn = null;
        rs = null;
    }

    public static CategoriaServizioDAO getInstance() {
        return instance;
    }

    public int add(CategoriaServizio categoria) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO categoria_servizio (Nome) VALUES ('" + categoria.getNome() + "');");
        conn.close();
        return rowCount;
    }

    public int remove(String nome) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM categoria_servizio WHERE Nome = '" + nome + "';");
        conn.close();
        return rowCount;
    }


    public ArrayList<CategoriaServizio> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Idcategoria_servizio, Nome FROM categoria_servizio");
        ArrayList<CategoriaServizio> categorieServizio = new ArrayList<>();

        try {
            while (rs.next()) {
                categoriaServizio = new CategoriaServizio();
                categoriaServizio.setIdCategoriaServizio(rs.getInt("Idcategoria_servizio"));
                categoriaServizio.setNome(rs.getString("Nome"));
                categorieServizio.add(categoriaServizio);
            }
            return categorieServizio;
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
