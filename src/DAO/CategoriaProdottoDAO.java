package DAO;

import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.CategoriaProdotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaProdottoDAO implements ICategoriaProdottoDAO {

    private static CategoriaProdottoDAO instance = new CategoriaProdottoDAO();
    private CategoriaProdotto categoriaProdotto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private CategoriaProdottoDAO() {
        categoriaProdotto = null;
        conn = null;
        rs = null;
    }

    public static CategoriaProdottoDAO getInstance() {
        return instance;
    }

    public int add(CategoriaProdotto categoria) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO categoria_prodotto (Nome) VALUES ('" + categoria.getNome() + "');");
        conn.close();
        return rowCount;
    }


    public int removeByName(String nome) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM categoria_prodotto WHERE Nome = '" + nome + "';");
        conn.close();
        return rowCount;
    }

    public ArrayList<CategoriaProdotto> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Idcategoria_prodotto, Nome FROM categoria_prodotto");
        ArrayList<CategoriaProdotto> categorieProdotto = new ArrayList<>();

        try {
            while (rs.next()) {
                categoriaProdotto = new CategoriaProdotto();
                categoriaProdotto.setIdCategoriaProdotto(rs.getInt("Idcategoria_prodotto"));
                categoriaProdotto.setNome(rs.getString("Nome"));
                categorieProdotto.add(categoriaProdotto);
            }
            return categorieProdotto;
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
