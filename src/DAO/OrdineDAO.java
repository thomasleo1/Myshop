package DAO;

import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.Ordine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO implements IOrdineDAO {

    private static OrdineDAO instance = new OrdineDAO();
    private Ordine ordine;
    private static IDbConnection conn;
    private static ResultSet rs;

    private OrdineDAO() {
        ordine = null;
        conn = null;
        rs = null;
    }

    public static OrdineDAO getInstance() {
        return instance;
    }

    public int addOrdine(IProdotto prodotto, int quantita, boolean isProdotto) {
        conn = DbConnection.getInstance();
        String param = "";
        if (isProdotto) {
            param = "idprodotto";
        } else {
            param = "idprodotto_composito";
        }
        String sql = "INSERT INTO ordine (" + param + ", quantita) VALUES ('" + prodotto.getId() + "', '" + quantita + "');";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public ArrayList<Ordine> getProdottiOrdinatiByManagerId(int idManager) {
        conn = DbConnection.getInstance();
        String sql = "SELECT o.idordine, o.quantita, p.idprodotto, p.nome FROM myshop.ordine as o\n" +
                "INNER JOIN prodotto as p on p.idprodotto = o.idprodotto\n" +
                "INNER JOIN prodotti_magazzino as pm on pm.idprodotto = p.idprodotto\n" +
                "INNER JOIN punto_vendita as pv on pv.idmagazzino = pm.idmagazzino\n" +
                "WHERE pv.idmanager = '" + idManager + "';";
        rs = conn.executeQuery(sql);
        ArrayList<Ordine> ordini = new ArrayList<>();

        try {
            while (rs.next()) {
                ordine = new Ordine();
                Prodotto prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("Idprodotto"));
                prodotto.setNome(rs.getString("Nome"));
                ordine.setIdOrdine(rs.getInt("Idordine"));
                ordine.setProdotto(prodotto);
                ordine.setQuantita(rs.getInt("Quantita"));
                ordini.add(ordine);
            }
            return ordini;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return ordini;
    }
    public ArrayList<Ordine> getProdottiCompositiOrdinatiByManagerId(int idManager) {
        conn = DbConnection.getInstance();
        String sql = "SELECT o.idordine, o.quantita, pc.idprodotto_composito, pc.nome FROM myshop.ordine as o\n" +
                "INNER JOIN prodotto_composito as pc on pc.idprodotto_composito = o.idprodotto_composito\n" +
                "INNER JOIN prodotti_magazzino as pm on pm.idprodotto_composito = pc.idprodotto_composito\n" +
                "INNER JOIN punto_vendita as pv on pv.idmagazzino = pm.idmagazzino\n" +
                "WHERE pv.idmanager = '" + idManager + "';";
        rs = conn.executeQuery(sql);
        ArrayList<Ordine> ordini = new ArrayList<>();

        try {
            while (rs.next()) {
                ordine = new Ordine();
                ProdottoComposito prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdProdottoComposito(rs.getInt("Idprodotto_composito"));
                prodottoComposito.setNome(rs.getString("Nome"));
                ordine.setIdOrdine(rs.getInt("Idordine"));
                ordine.setProdotto(prodottoComposito);
                ordine.setQuantita(rs.getInt("Quantita"));
                ordini.add(ordine);
            }
            return ordini;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Resultset: " + e.getMessage());
        } finally {
            conn.close();
        }
        return ordini;
    }

    public int removeById(int idOrdine) {
        conn = DbConnection.getInstance();
        String sql = "DELETE FROM ordine WHERE idordine = '" + idOrdine + "';";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }
}
