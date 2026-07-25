package DAO;

import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.CategoriaProdotto;
import Model.SottoCategoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SottocategoriaDAO implements ISottocategoriaDAO {

    private static SottocategoriaDAO instance = new SottocategoriaDAO();
    private SottoCategoria sottoCategoria;
    private static IDbConnection conn;
    private static ResultSet rs;

    private SottocategoriaDAO() {
        sottoCategoria = null;
        conn = null;
        rs = null;
    }

    public static SottocategoriaDAO getInstance() {
        return instance;
    }

    public int addSottocategoria(String nome, CategoriaProdotto categoriaProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO sottocategoria (Nome, Idcategoria_prodotto) VALUES ('" + nome +  "', '" + categoriaProdotto.getId() + "');");
        conn.close();
        return rowCount;
    }

    public int removeByName(String nome) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("DELETE FROM sottocategoria WHERE Nome = '" + nome + "';");
        conn.close();
        return rowCount;
    }

    public ArrayList<SottoCategoria> findAllSottocategorie(int idCategoria) {
        conn = DbConnection.getInstance();
        String sql = "SELECT Idsottocategoria, Nome, Idcategoria_prodotto FROM sottocategoria WHERE Idcategoria_prodotto = '" + idCategoria + "';";
        rs = conn.executeQuery(sql);

        ArrayList<SottoCategoria> sottoCategorie = new ArrayList<>();

        try {
            while (rs.next()) {
                sottoCategoria = new SottoCategoria();
                sottoCategoria.setIdSottoCategoria(rs.getInt("Idsottocategoria"));
                sottoCategoria.setNome(rs.getString("Nome"));
                sottoCategoria.setIdCategoriaProdotto(rs.getInt("Idcategoria_prodotto"));
                sottoCategorie.add(sottoCategoria);
            }
            return sottoCategorie;
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
