package DAO;

import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Composite.ProdottoComposito;
import Model.Recensione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoCompositoDAO implements IProdottoCompositoDAO {
    private static ProdottoCompositoDAO instance = new ProdottoCompositoDAO();
    private ProdottoComposito prodottoComposito;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoCompositoDAO() {
        prodottoComposito = null;
        conn = null;
        rs = null;
    }

    public static ProdottoCompositoDAO getInstance() {
        return instance;
    }


    public ProdottoComposito findByName(String nome) {

        conn = DbConnection.getInstance();
        String sql = "SELECT pc.Idprodotto_composito, pc.Nome AS NomeProdottoComposito, pc.Descrizione, pc.Idproduttore, pc.Idcollocazione, pc.Idcategoria,  pc.Idrecensione, pc.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria,\n" +
                "col.idcollocazione, col.corsia, col.scaffale,\n" +
                "rec.idrecensione, rec.testo, rec.feedback \n" +
                "FROM myshop.prodotto_composito AS pc\n" +
                "INNER JOIN myshop.produttore AS pr on pc.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_prodotto AS cat on pc.Idcategoria = cat.Idcategoria_prodotto\n" +
                "INNER JOIN myshop.collocazione AS col on pc.Idcollocazione = col.Idcollocazione\n" +
                "LEFT JOIN myshop.recensione AS rec on pc.Idrecensione = rec.Idrecensione\n" +
                "WHERE pc.Nome = '" + nome + "';";
        rs = conn.executeQuery(sql);

        try {

            rs.next();
            if (rs.getRow() == 1) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdProdottoComposito(rs.getInt("Idprodotto_composito"));
                prodottoComposito.setNome(rs.getString("NomeProdottoComposito"));
                prodottoComposito.setDescrizione(rs.getString("Descrizione"));
                prodottoComposito.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                prodottoComposito.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodottoComposito.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodottoComposito.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodottoComposito.setImmagine(rs.getString("Immagine"));
                return prodottoComposito;
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


    public ArrayList<ProdottoComposito> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT pc.Idprodotto_composito, pc.Nome AS NomeProdottoComposito, pc.Descrizione, pc.Idproduttore, pc.Idcollocazione, pc.Idcategoria, pc.Idrecensione, pc.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria,\n" +
                "col.idcollocazione, col.corsia, col.scaffale,\n" +
                "rec.idrecensione, rec.testo, rec.feedback \n" +
                "FROM myshop.prodotto_composito AS pc\n" +
                "INNER JOIN myshop.produttore AS pr on pc.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_prodotto AS cat on pc.Idcategoria = cat.Idcategoria_prodotto\n" +
                "INNER JOIN myshop.collocazione AS col on pc.Idcollocazione = col.Idcollocazione\n" +
                "LEFT JOIN myshop.recensione AS rec on pc.Idrecensione = rec.Idrecensione\n");
        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdProdottoComposito(rs.getInt("Idprodotto_composito"));
                prodottoComposito.setNome(rs.getString("NomeProdottoComposito"));
                prodottoComposito.setDescrizione(rs.getString("Descrizione"));
                prodottoComposito.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                prodottoComposito.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodottoComposito.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodottoComposito.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodottoComposito.setImmagine(rs.getString("Immagine"));
                prodottiCompositi.add(prodottoComposito);
            }
            return prodottiCompositi;
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

    public int add(ProdottoComposito prodottoComposito) {
        conn = DbConnection.getInstance();
        String sql = "INSERT INTO prodotto_composito (Nome, Descrizione, Idproduttore, Idcollocazione, Idcategoria, Immagine) VALUES ('" +
                prodottoComposito.getNome() + "', '" +
                prodottoComposito.getDescrizione() + "', '" +
                prodottoComposito.getProduttore().getIdProduttore() + "', '" +
                prodottoComposito.getCollocazione().getIdCollocazione() + "', '" +
                prodottoComposito.getCategoria().getId() + "', '" +
                prodottoComposito.getImmagine() + "');";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public int update(ProdottoComposito prodottoComposito) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE prodotto_composito SET " +
                "Nome = '" + prodottoComposito.getNome() +
                "', Descrizione = '" + prodottoComposito.getDescrizione() +
                "', Idproduttore = '" + prodottoComposito.getProduttore().getIdProduttore() +
                "', Idcollocazione = '" + prodottoComposito.getCollocazione().getIdCollocazione() +
                "', Idcategoria = '" + prodottoComposito.getCategoria().getId() +
                "', Immagine = '" + prodottoComposito.getImmagine() +
                "' WHERE Idprodotto_composito = '" + prodottoComposito.getId() + "';");
        conn.close();
        return rowCount;
    }

    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM prodotto_composito WHERE Idprodotto_composito = '" + id + "';");
        conn.close();
        return rowCount;
    }

    public int addSottoprodotto(int idProdottoComposito, int idProdotto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO associazione_prodotto_composito (idprodotto_composito, idprodotto) VALUES ('" + idProdottoComposito + "', '" + idProdotto + "');");
        conn.close();
        return rowCount;
    }

    public ArrayList<ProdottoComposito> getProdottiCompositiByIdMagazzino(int idMagazzino) {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT pc.Idprodotto_composito, pc.Nome AS NomeProdottoComposito, pc.Descrizione, pc.Idproduttore, pc.Idcollocazione, pc.Idcategoria, pc.Idrecensione, pc.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria,\n" +
                "col.idcollocazione, col.corsia, col.scaffale,\n" +
                "rec.idrecensione, rec.testo, rec.feedback \n" +
                "FROM myshop.prodotto_composito AS pc\n" +
                "INNER JOIN myshop.produttore AS pr on pc.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_prodotto AS cat on pc.Idcategoria = cat.Idcategoria_prodotto\n" +
                "INNER JOIN myshop.collocazione AS col on pc.Idcollocazione = col.Idcollocazione\n" +
                "LEFT JOIN myshop.recensione AS rec on pc.Idrecensione = rec.Idrecensione\n" +
                "INNER JOIN myshop.prodotti_magazzino AS pm on pc.Idprodotto_composito = pm.Idprodotto_composito\n" +
                "WHERE pm.Quantita > 0 AND pm.Idmagazzino  = " + idMagazzino + ";");
        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdProdottoComposito(rs.getInt("Idprodotto_composito"));
                prodottoComposito.setNome(rs.getString("NomeProdottoComposito"));
                prodottoComposito.setDescrizione(rs.getString("Descrizione"));
                prodottoComposito.setProduttore(rs.getInt("Idproduttore"), rs.getString("NomeProduttore"), rs.getString("sitoWeb"), rs.getString("citta"), rs.getString("nazione"));
                prodottoComposito.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodottoComposito.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodottoComposito.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodottoComposito.setImmagine(rs.getString("Immagine"));
                prodottiCompositi.add(prodottoComposito);
            }
            return prodottiCompositi;
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


    public ProdottoComposito findById(int idProdottoComposito) {
        conn = DbConnection.getInstance();
        String sql = "SELECT pc.Idprodotto_composito, pc.Nome AS NomeProdottoComposito, pc.Descrizione, pc.Idproduttore, pc.Idcollocazione, pc.Idcategoria,  pc.Idrecensione, pc.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria,\n" +
                "col.idcollocazione, col.corsia, col.scaffale,\n" +
                "rec.idrecensione, rec.testo, rec.feedback\n" +
                "FROM myshop.prodotto_composito AS pc\n" +
                "INNER JOIN myshop.produttore AS pr on pc.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_prodotto AS cat on pc.Idcategoria = cat.Idcategoria_prodotto\n" +
                "INNER JOIN myshop.collocazione AS col on pc.Idcollocazione = col.Idcollocazione\n" +
                "LEFT JOIN myshop.recensione AS rec on pc.Idrecensione = rec.Idrecensione\n" +
                "WHERE pc.Idprodotto_composito = '" + idProdottoComposito + "';";
        rs = conn.executeQuery(sql);

        try {

            rs.next();
            if (rs.getRow() == 1) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdProdottoComposito(rs.getInt("Idprodotto_composito"));
                prodottoComposito.setNome(rs.getString("NomeProdottoComposito"));
                prodottoComposito.setDescrizione(rs.getString("Descrizione"));
                prodottoComposito.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                prodottoComposito.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodottoComposito.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodottoComposito.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodottoComposito.setImmagine(rs.getString("Immagine"));
                return prodottoComposito;
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

    public int removeSottoprodottoById(int idProdottoComposito, int idSottoprodotto) {
        conn = DbConnection.getInstance();
        String sql = "DELETE FROM associazione_prodotto_composito " +
                "WHERE Idprodotto_composito = '" + idProdottoComposito + "' " +
                "AND Idprodotto = '" + idSottoprodotto + "';";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public void addSottoprodottoById(int idProdottoComposito, int idSottoprodotto) {
        conn = DbConnection.getInstance();
        String sql = "INSERT INTO associazione_prodotto_composito (Idprodotto_composito, Idprodotto) " +
                "VALUES ('" + idProdottoComposito + "', '" + idSottoprodotto + "');";
        conn.executeUpdate(sql);
        conn.close();
    }

    public boolean prodottoCompExists(int id, String nome) {

        conn = DbConnection.getInstance();
        String sql = "SELECT * FROM prodotto_composito WHERE Idprodotto_composito = '" + id + "' AND Nome = '" + nome + "';";
        rs = conn.executeQuery(sql);

        try {
            rs.next();
            if (rs.getRow() == 1) {
                return true;
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
        return false;
    }

    public int updateRecensione(int idProdottoComp, int idRecensione) {
        conn = DbConnection.getInstance();
        String sql = "UPDATE prodotto_composito SET Idrecensione = '" + idRecensione + "' WHERE Idprodotto_composito = '" + idProdottoComp + "';";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public List<ProdottoComposito> getProdottiCompositiRecensitiByIdCliente(int idCliente) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT pc.Idprodotto_composito, pc.Nome, r.Idrecensione, r.Testo, r.Feedback FROM prodotto_composito AS pc\n" +
                "INNER JOIN recensione as r on r.idrecensione = pc.idrecensione\n" +
                "WHERE r.idcliente = '" + idCliente + "';");
        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdProdottoComposito(rs.getInt("Idprodotto_composito"));
                prodottoComposito.setNome(rs.getString("Nome"));
                prodottoComposito.setRecensione(rs.getInt("r.Idrecensione"), rs.getString("r.Testo"), Recensione.Feedback.valueOf(rs.getString("r.Feedback")));
                prodottiCompositi.add(prodottoComposito);
            }
            return prodottiCompositi;
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

    public List<ProdottoComposito> getProdottiCompositiRecensitiByIdManager(int idManager) {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT pc.Idprodotto_composito, pc.Nome, r.idrecensione, r.testo, r.feedback FROM prodotto_composito as pc " +
                "INNER JOIN recensione as r on r.idrecensione = pc.idrecensione " +
                "INNER JOIN cliente as c on c.idcliente = r.idcliente " +
                "INNER JOIN utente as u on u.idutente = c.idcliente " +
                "INNER JOIN punto_vendita as pv on pv.idpunto_vendita = c.idpunto_vendita " +
                "WHERE r.risposta IS NULL AND pv.Idmanager = '" + idManager + "';");
        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdProdottoComposito(rs.getInt("Idprodotto_composito"));
                prodottoComposito.setNome(rs.getString("Nome"));
                prodottoComposito.setRecensione(rs.getInt("r.Idrecensione"), rs.getString("r.Testo"), Recensione.Feedback.valueOf(rs.getString("r.Feedback")));
                prodottiCompositi.add(prodottoComposito);
            }
            return prodottiCompositi;
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

    public List<ProdottoComposito> getProdottiCompositiNonDisponibiliByIdUtente(int idUtente) {
        conn = DbConnection.getInstance();
        String sql = "SELECT pc.Idprodotto_composito, pc.Nome AS NomeProdottoComposito, pc.Descrizione, pc.Idproduttore, pc.Idcollocazione, pc.Idcategoria, pc.Idrecensione, pc.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione, " +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria, " +
                "col.idcollocazione, col.corsia, col.scaffale, " +
                "rec.idrecensione, rec.testo, rec.feedback " +
                "FROM myshop.prodotto_composito AS pc " +
                "INNER JOIN myshop.produttore AS pr on pc.Idproduttore = pr.Idproduttore " +
                "INNER JOIN myshop.categoria_prodotto AS cat on pc.Idcategoria = cat.Idcategoria_prodotto " +
                "INNER JOIN myshop.collocazione AS col on pc.Idcollocazione = col.Idcollocazione " +
                "LEFT JOIN myshop.recensione AS rec on pc.Idrecensione = rec.Idrecensione " +
                "INNER JOIN myshop.prodotti_magazzino AS pm on pm.Idprodotto_composito = pc.Idprodotto_composito " +
                "INNER JOIN myshop.punto_vendita AS pv on pv.idmagazzino = pm.idmagazzino " +
                "INNER JOIN myshop.cliente as c on c.idpunto_vendita = pv.idpunto_vendita " +
                "WHERE pm.quantita = 0 AND c.Idcliente = '" + idUtente + "';";
        rs = conn.executeQuery(sql);
        ArrayList<ProdottoComposito> prodottiCompositi = new ArrayList<>();

        try {
            while (rs.next()) {
                prodottoComposito = new ProdottoComposito();
                prodottoComposito.setIdProdottoComposito(rs.getInt("Idprodotto_composito"));
                prodottoComposito.setNome(rs.getString("NomeProdottoComposito"));
                prodottoComposito.setDescrizione(rs.getString("Descrizione"));
                prodottoComposito.setProduttore(rs.getInt("Idproduttore"), rs.getString("NomeProduttore"), rs.getString("sitoWeb"), rs.getString("citta"), rs.getString("nazione"));
                prodottoComposito.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodottoComposito.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodottoComposito.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodottoComposito.setImmagine(rs.getString("Immagine"));
                prodottiCompositi.add(prodottoComposito);
            }
            return prodottiCompositi;
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
