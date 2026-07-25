package DAO;

import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.Magazzino;
import Model.ProdottoMagazzino;
import Model.Produttore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoMagazzinoDAO implements IProdottoMagazzinoDAO {

    private static ProdottoMagazzinoDAO instance = new ProdottoMagazzinoDAO();
    private ProdottoMagazzino prodottoMagazzino;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoMagazzinoDAO() {
        prodottoMagazzino = null;
        conn = null;
        rs = null;
    }

    public static ProdottoMagazzinoDAO getInstance() {
        return instance;
    }


    public List<ProdottoMagazzino> getProdottiByManagerId(int idManager) {
        conn = DbConnection.getInstance();
        String sql = "SELECT P.idprodotto, P.Nome AS NomeProdotto, P.prezzo, PM.quantita FROM myshop.punto_vendita AS PV " +
                "INNER JOIN prodotti_magazzino AS PM ON PV.idmagazzino = PM.idmagazzino " +
                "INNER JOIN prodotto AS P ON PM.idprodotto = P.idprodotto " +
                "WHERE pv.Idmanager = '" + idManager + "';";
        rs = conn.executeQuery(sql);
        ArrayList<ProdottoMagazzino> prodottiMagazzino = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoMagazzino = new ProdottoMagazzino();
                Prodotto prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("p.Idprodotto"));
                prodotto.setNome(rs.getString("NomeProdotto"));
                prodotto.setPrezzo(rs.getFloat("p.Prezzo"));
                prodottoMagazzino.setProdotto(prodotto);
                prodottoMagazzino.setQuantita(rs.getInt("PM.quantita"));
                prodottiMagazzino.add(prodottoMagazzino);
            }
            return prodottiMagazzino;
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


    public List<ProdottoMagazzino> getProdottiCompositiByManagerId(int idManager) {
        conn = DbConnection.getInstance();
        String sql = "SELECT PC.idprodotto_composito, PC.Nome AS NomeProdottoComposito, PM.quantita FROM myshop.punto_vendita AS PV " +
                "INNER JOIN prodotti_magazzino AS PM ON PV.idmagazzino = PM.idmagazzino " +
                "INNER JOIN prodotto_composito AS PC ON PM.idprodotto_composito = PC.idprodotto_composito " +
                "WHERE pv.Idmanager = '" + idManager + "';";
        rs = conn.executeQuery(sql);
        ArrayList<ProdottoMagazzino> prodottiMagazzino = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoMagazzino = new ProdottoMagazzino();
                ProdottoComposito prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdProdottoComposito(rs.getInt("pc.Idprodotto_composito"));
                prodottoComposito.setNome(rs.getString("NomeProdottoComposito"));
                prodottoMagazzino.setProdotto(prodottoComposito);
                prodottoMagazzino.setQuantita(rs.getInt("PM.quantita"));
                prodottiMagazzino.add(prodottoMagazzino);
            }
            return prodottiMagazzino;
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


    public int updateQuantita(int idProdotto, int quantita, boolean isComposito) {
        conn = DbConnection.getInstance();
        String idParam = "";
        if (isComposito) {
            idParam = "Idprodotto_composito";
        } else {
            idParam = "Idprodotto";
        }
        String sql = "UPDATE prodotti_magazzino SET " +
                "Quantita = '" + quantita +
                "' WHERE " + idParam + " = '" + idProdotto + "';";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;

    }

    public ProdottoMagazzino getProdottoByNameAndId(String nomeProdotto, int idProdotto) {
        conn = DbConnection.getInstance();
        String sql = "SELECT pm.quantita, pm.idprodotto, pm.idprodotto_composito FROM myshop.prodotti_magazzino AS PM " +
        "LEFT JOIN prodotto AS P ON P.idprodotto = Pm.Idprodotto " +
        "LEFT JOIN prodotto_composito as PC on Pc.idprodotto_composito = PM.idprodotto_composito " +
        "WHERE (p.idprodotto = '" + idProdotto + "' AND p.nome = '" + nomeProdotto + "') OR " +
                "(pc.idprodotto_composito = '" + idProdotto + "' AND pc.nome = '" + nomeProdotto.split("\\(")[0] + "');";
        rs = conn.executeQuery(sql);

        try {
            rs.next();
            if (rs.getRow() == 1) {
                prodottoMagazzino = new ProdottoMagazzino();

                if (rs.getObject("pm.Idprodotto") == null) {
                    ProdottoComposito prodottoComposito = new ProdottoComposito();
                    prodottoComposito.setIdProdottoComposito(rs.getInt("pm.idProdotto_composito"));
                    prodottoMagazzino.setProdotto(prodottoComposito);
                } else {
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("pm.idProdotto"));
                    prodottoMagazzino.setProdotto(prodotto);
                }
                prodottoMagazzino.setQuantita(rs.getInt("pm.Quantita"));
                return prodottoMagazzino;
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

    public ArrayList<ProdottoMagazzino> getProdottiByListaId(int idListaAcquisto) {

        conn = DbConnection.getInstance();
        String sql = "SELECT p.Nome AS NomeProdotto, p.Idprodotto, p.Prezzo, pc.Nome AS NomeProdottoComp, pc.Idprodotto_composito, pl.quantita \n" +
                "FROM associazione_prodotti_lista AS pl \n" +
                "LEFT JOIN prodotto as P ON p.idprodotto = pl.idprodotto\n" +
                "LEFT JOIN prodotto_composito AS pc on pc.idprodotto_composito = pl.idprodotto_composito\n" +
                "WHERE pl.idLista_acquisto = '" + idListaAcquisto + "';";
        rs = conn.executeQuery(sql);
        ArrayList<ProdottoMagazzino> prodotti = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoMagazzino = new ProdottoMagazzino();
                prodottoMagazzino.setQuantita(rs.getInt("pl.quantita"));
                if (rs.getObject("pc.Idprodotto_composito") != null) {
                    ProdottoComposito prodottoComposito = new ProdottoComposito();
                    prodottoComposito.setIdProdottoComposito(rs.getInt("pc.idProdotto_composito"));
                    prodottoComposito.setNome(rs.getString("NomeProdottoComp"));
                    prodottoMagazzino.setProdotto(prodottoComposito);
                    prodotti.add(prodottoMagazzino);

                } else if (rs.getObject("p.Idprodotto") != null){
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("p.idProdotto"));
                    prodotto.setNome(rs.getString("NomeProdotto"));
                    prodotto.setPrezzo(rs.getFloat("p.Prezzo"));
                    prodottoMagazzino.setProdotto(prodotto);
                    prodotti.add(prodottoMagazzino);
                }

            }
            return prodotti;
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

    public List<ProdottoMagazzino> findAll() {

        conn = DbConnection.getInstance();
        String sql = "SELECT pm.quantita, pm.idprodotto, pm.idprodotto_composito FROM myshop.prodotti_magazzino AS PM " +
                "LEFT JOIN prodotto AS P ON P.idprodotto = Pm.Idprodotto " +
                "LEFT JOIN prodotto_composito as PC on Pc.idprodotto_composito = PM.idprodotto_composito ";
        rs = conn.executeQuery(sql);
        ArrayList<ProdottoMagazzino> prodotti = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoMagazzino = new ProdottoMagazzino();

                if (rs.getObject("pm.Idprodotto") == null) {
                    ProdottoComposito prodottoComposito = new ProdottoComposito();
                    prodottoComposito.setIdProdottoComposito(rs.getInt("pm.idProdotto_composito"));
                    prodottoMagazzino.setProdotto(prodottoComposito);
                } else {
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("pm.idProdotto"));
                    prodottoMagazzino.setProdotto(prodotto);
                }
                prodottoMagazzino.setQuantita(rs.getInt("pm.Quantita"));
                 prodotti.add(prodottoMagazzino);
            }
            return prodotti;
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

    public List<ProdottoMagazzino> getProdottiMagazzinoByCliente(int idUtente) {
        conn = DbConnection.getInstance();
        String sql = "SELECT pm.quantita, pm.idprodotto, pm.idprodotto_composito FROM myshop.prodotti_magazzino AS pm\n" +
                "INNER JOIN myshop.punto_vendita AS pv ON pm.idmagazzino = pv.idmagazzino\n" +
                "INNER JOIN myshop.cliente AS c ON c.idpunto_vendita = pv.idpunto_vendita\n" +
                "WHERE c.idcliente = '" + idUtente + "';";
        rs = conn.executeQuery(sql);
        ArrayList<ProdottoMagazzino> prodotti = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoMagazzino = new ProdottoMagazzino();

                if (rs.getObject("pm.Idprodotto") == null) {
                    ProdottoComposito prodottoComposito = new ProdottoComposito();
                    prodottoComposito.setIdProdottoComposito(rs.getInt("pm.idProdotto_composito"));
                    prodottoMagazzino.setProdotto(prodottoComposito);
                } else {
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("pm.idProdotto"));
                    prodottoMagazzino.setProdotto(prodotto);
                }
                prodottoMagazzino.setQuantita(rs.getInt("pm.Quantita"));
                prodotti.add(prodottoMagazzino);
            }
            return prodotti;
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
