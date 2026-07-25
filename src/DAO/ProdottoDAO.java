package DAO;

import DBConnection.Command.DbOperationExecutor;
import DBConnection.Command.IDbOperation;
import DBConnection.Command.ReadOperation;
import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Composite.Prodotto;
import Model.Recensione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO implements IProdottoDAO {

    private static ProdottoDAO instance = new ProdottoDAO();
    private Prodotto prodotto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ProdottoDAO() {
        prodotto = null;
        conn = null;
        rs = null;
    }

    public static ProdottoDAO getInstance() {
        return instance;
    }

    public int add(Prodotto prodotto, int idSottocategoria) {
        conn = DbConnection.getInstance();
        String sql = "INSERT INTO prodotto (Nome, Descrizione, Prezzo, Idproduttore, Idcollocazione, Idcategoria, Idsottocategoria, Immagine) " +
                "VALUES ('" + prodotto.getNome() + "', '" +
                prodotto.getDescrizione() + "', '" +
                prodotto.getPrezzo() + "', '" +
                prodotto.getProduttore().getIdProduttore() + "', '" +
                prodotto.getCollocazione().getIdCollocazione() + "', '" +
                prodotto.getCategoria().getId() + "', '" +
                idSottocategoria + "', '" +
                prodotto.getImmagine() + "');";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public int update(Prodotto prodotto) {
        conn = DbConnection.getInstance();
        String sql = "UPDATE prodotto SET " +
                "Nome = '" + prodotto.getNome() +
                "', Descrizione = '" + prodotto.getDescrizione() +
                "', Prezzo = '" + prodotto.getPrezzo() +
                "', Idproduttore = '" + prodotto.getProduttore().getIdProduttore() +
                "', Idcollocazione = '" + prodotto.getCollocazione().getIdCollocazione() +
                "', Idcategoria = '" + prodotto.getCategoria().getId() +
                "', Idsottocategoria = '" + prodotto.getSottocategoria().getId() +
                "', Immagine = '" + prodotto.getImmagine() +
                "' WHERE Idprodotto = '" + prodotto.getId() + "';";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }



    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM prodotto WHERE Idprodotto = '" + id + "';");
        conn.close();
        return rowCount;
    }

    public Prodotto findByName(String nome) {

        conn = DbConnection.getInstance();
        String sql = "SELECT p.Idprodotto, p.Nome AS NomeProdotto, p.Descrizione, p.Prezzo, p.Idproduttore, p.Idcollocazione, p.Idcategoria, p.Idsottocategoria, p.Idrecensione, p.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria,\n" +
                "sottocat.Nome as NomeSottocategoria, \n" +
                "col.idcollocazione, col.corsia, col.scaffale,\n" +
                "rec.idrecensione, rec.testo, rec.feedback\n" +
                "FROM myshop.prodotto AS p\n" +
                "INNER JOIN myshop.produttore AS pr on p.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_prodotto AS cat on p.Idcategoria = cat.Idcategoria_prodotto\n" +
                "INNER JOIN myshop.sottocategoria AS sottocat on p.Idsottocategoria = sottocat.Idsottocategoria\n" +
                "INNER JOIN myshop.collocazione AS col on p.Idcollocazione = col.Idcollocazione\n" +
                "LEFT JOIN myshop.recensione AS rec on p.Idrecensione = rec.Idrecensione\n" +
                "WHERE p.Nome = '" + nome + "';";
        rs = conn.executeQuery(sql);

        try {

            rs.next();
            if (rs.getRow() == 1) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("Idprodotto"));
                prodotto.setNome(rs.getString("NomeProdotto"));
                prodotto.setDescrizione(rs.getString("Descrizione"));
                prodotto.setPrezzo(rs.getFloat("Prezzo"));
                prodotto.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                prodotto.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodotto.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                prodotto.setSottocategoria(rs.getInt("Idsottocategoria"), rs.getString("NomeSottocategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodotto.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodotto.setImmagine(rs.getString("Immagine"));
                return prodotto;
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

    public Prodotto findById(int id) {

        conn = DbConnection.getInstance();
        String sql = "SELECT p.Idprodotto, p.Nome AS NomeProdotto, p.Descrizione, p.Prezzo, p.Idproduttore, p.Idcollocazione, p.Idcategoria, p.Idsottocategoria, p.Idrecensione, p.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria,\n" +
                "sottocat.Nome as NomeSottocategoria, \n" +
                "col.idcollocazione, col.corsia, col.scaffale,\n" +
                "rec.idrecensione, rec.testo, rec.feedback \n" +
                "FROM myshop.prodotto AS p\n" +
                "INNER JOIN myshop.produttore AS pr on p.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_prodotto AS cat on p.Idcategoria = cat.Idcategoria_prodotto\n" +
                "INNER JOIN myshop.sottocategoria AS sottocat on p.Idsottocategoria = sottocat.Idsottocategoria\n" +
                "INNER JOIN myshop.collocazione AS col on p.Idcollocazione = col.Idcollocazione\n" +
                "LEFT JOIN myshop.recensione AS rec on p.Idrecensione = rec.Idrecensione\n" +
                "WHERE p.Idprodotto = '" + id + "';";
        rs = conn.executeQuery(sql);

        try {

            rs.next();
            if (rs.getRow() == 1) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("Idprodotto"));
                prodotto.setNome(rs.getString("NomeProdotto"));
                prodotto.setDescrizione(rs.getString("Descrizione"));
                prodotto.setPrezzo(rs.getFloat("Prezzo"));
                prodotto.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                prodotto.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodotto.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                prodotto.setSottocategoria(rs.getInt("Idsottocategoria"), rs.getString("NomeSottocategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodotto.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodotto.setImmagine(rs.getString("Immagine"));
                return prodotto;
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


    public ArrayList<Prodotto> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT p.Idprodotto, p.Nome AS NomeProdotto, p.Descrizione, p.Prezzo, p.Idproduttore, p.Idcollocazione, p.Idcategoria, p.Idsottocategoria, p.Idrecensione, p.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria,\n" +
                "sottocat.Nome as NomeSottocategoria, \n" +
                "col.idcollocazione, col.corsia, col.scaffale,\n" +
                "rec.idrecensione, rec.testo, rec.feedback \n" +
                "FROM myshop.prodotto AS p\n" +
                "INNER JOIN myshop.produttore AS pr on p.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_prodotto AS cat on p.Idcategoria = cat.Idcategoria_prodotto\n" +
                "INNER JOIN myshop.sottocategoria AS sottocat on p.Idsottocategoria = sottocat.Idsottocategoria\n" +
                "INNER JOIN myshop.collocazione AS col on p.Idcollocazione = col.Idcollocazione\n" +
                "LEFT JOIN myshop.recensione AS rec on p.Idrecensione = rec.Idrecensione\n");
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("Idprodotto"));
                prodotto.setNome(rs.getString("NomeProdotto"));
                prodotto.setDescrizione(rs.getString("Descrizione"));
                prodotto.setPrezzo(rs.getFloat("Prezzo"));
                prodotto.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                prodotto.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodotto.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                prodotto.setSottocategoria(rs.getInt("Idsottocategoria"), rs.getString("NomeSottocategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodotto.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodotto.setImmagine(rs.getString("Immagine"));
                prodotti.add(prodotto);
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

    public ArrayList<Prodotto> findByProdottoCompositoID(int id) {

        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT p.Idprodotto, p.Nome AS NomeProdotto, p.Descrizione, p.Prezzo, p.Idproduttore, p.Idcollocazione, p.Idcategoria, p.Idsottocategoria, p.Idrecensione, p.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria,\n" +
                "sottocat.Nome as NomeSottocategoria, \n" +
                "col.idcollocazione, col.corsia, col.scaffale,\n" +
                "rec.idrecensione, rec.testo, rec.feedback\n" +
                "FROM myshop.prodotto AS p\n" +
                "INNER JOIN myshop.produttore AS pr on p.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_prodotto AS cat on p.Idcategoria = cat.Idcategoria_prodotto\n" +
                "INNER JOIN myshop.sottocategoria AS sottocat on p.Idsottocategoria = sottocat.Idsottocategoria\n" +
                "INNER JOIN myshop.collocazione AS col on p.Idcollocazione = col.Idcollocazione\n" +
                "LEFT JOIN myshop.recensione AS rec on p.Idrecensione = rec.Idrecensione\n" +
                "INNER JOIN myshop.associazione_prodotto_composito AS map on p.Idprodotto = map.Idprodotto\n" +
                "WHERE map.idprodotto_composito =" + id + ";");
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("Idprodotto"));
                prodotto.setNome(rs.getString("NomeProdotto"));
                prodotto.setDescrizione(rs.getString("Descrizione"));
                prodotto.setPrezzo(rs.getFloat("Prezzo"));
                prodotto.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                prodotto.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodotto.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                prodotto.setSottocategoria(rs.getInt("Idsottocategoria"), rs.getString("NomeSottocategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodotto.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodotto.setImmagine(rs.getString("Immagine"));
                prodotti.add(prodotto);
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


    public ArrayList<Prodotto> getProdottiByIdMagazzino(int idMagazzino) {

        conn = DbConnection.getInstance();
        String sql = "SELECT p.Idprodotto, p.Nome AS NomeProdotto, p.Prezzo, p.Descrizione, p.Idproduttore, p.Idcollocazione, p.Idcategoria, p.Idsottocategoria, p.Idrecensione, p.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria,\n" +
                "sottocat.Nome as NomeSottocategoria, \n" +
                "col.idcollocazione, col.corsia, col.scaffale,\n" +
                "rec.idrecensione, rec.testo, rec.feedback\n" +
                "FROM myshop.prodotto AS p\n" +
                "INNER JOIN myshop.produttore AS pr on p.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_prodotto AS cat on p.Idcategoria = cat.Idcategoria_prodotto\n" +
                "INNER JOIN myshop.sottocategoria AS sottocat on p.Idsottocategoria = sottocat.Idsottocategoria\n" +
                "INNER JOIN myshop.collocazione AS col on p.Idcollocazione = col.Idcollocazione\n" +
                "LEFT JOIN myshop.recensione AS rec on p.Idrecensione = rec.Idrecensione\n" +
                "INNER JOIN myshop.prodotti_magazzino AS pm on pm.Idprodotto = p.Idprodotto\n" +
                "WHERE pm.Quantita > 0 AND pm.Idmagazzino  = " + idMagazzino + ";";
        rs = conn.executeQuery(sql);
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("Idprodotto"));
                prodotto.setNome(rs.getString("NomeProdotto"));
                prodotto.setDescrizione(rs.getString("Descrizione"));
                prodotto.setPrezzo(rs.getFloat("Prezzo"));
                prodotto.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                prodotto.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodotto.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                prodotto.setSottocategoria(rs.getInt("Idsottocategoria"), rs.getString("NomeSottocategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodotto.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodotto.setImmagine(rs.getString("Immagine"));
                prodotti.add(prodotto);
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

    public boolean prodottoExists(int id, String nome) {

        conn = DbConnection.getInstance();
        String sql = "SELECT * FROM myshop.prodotto AS p WHERE p.Idprodotto = '" + id + "' AND p.Nome = '" + nome + "';";
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

    public int updateRecensione(int idProdotto, int idRecensione) {
        conn = DbConnection.getInstance();
        String sql = "UPDATE prodotto SET Idrecensione = '" + idRecensione + "' WHERE Idprodotto = " + idProdotto + ";";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public ArrayList<Prodotto> getProdottiRecensitiByIdCliente(int idCliente) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT p.Idprodotto, p.Nome, r.Idrecensione, r.Testo, r.Feedback FROM prodotto AS p\n" +
                        "INNER JOIN recensione as r on r.idrecensione = p.idrecensione\n" +
                        "WHERE r.idcliente = '" + idCliente + "';");
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("Idprodotto"));
                prodotto.setNome(rs.getString("Nome"));
                prodotto.setRecensione(rs.getInt("r.Idrecensione"), rs.getString("r.Testo"), Recensione.Feedback.valueOf(rs.getString("r.Feedback")));
                prodotti.add(prodotto);
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

    public ArrayList<Prodotto> getProdottiRecensitiByIdManager(int idManager) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT p.Idprodotto, p.Nome, r.idrecensione, r.testo, r.feedback FROM prodotto as p\n" +
                "INNER JOIN recensione as r on r.idrecensione = p.idrecensione\n" +
                "INNER JOIN cliente as c on c.idcliente = r.idcliente \n" +
                "INNER JOIN utente as u on u.idutente = c.idcliente\n" +
                "INNER JOIN punto_vendita as pv on pv.idpunto_vendita = c.idpunto_vendita \n" +
                "WHERE r.risposta IS NULL AND pv.Idmanager = '" + idManager + "';");
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("Idprodotto"));
                prodotto.setNome(rs.getString("Nome"));
                prodotto.setRecensione(rs.getInt("r.Idrecensione"), rs.getString("r.Testo"), Recensione.Feedback.valueOf(rs.getString("r.Feedback")));
                prodotti.add(prodotto);
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

    public List<Prodotto> getProdottiNonDisponibiliByIdUtente(int idUtente) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT p.Idprodotto, p.Nome AS NomeProdotto, p.Prezzo, p.Descrizione, p.Idproduttore, p.Idcollocazione, p.Idcategoria, p.Idsottocategoria, p.Idrecensione, p.Immagine, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione, " +
                "cat.idcategoria_prodotto, cat.Nome AS NomeCategoria, " +
                "sottocat.Nome as NomeSottocategoria, " +
                "col.idcollocazione, col.corsia, col.scaffale, " +
                "rec.idrecensione, rec.testo, rec.feedback " +
                "FROM myshop.prodotto AS p " +
                "INNER JOIN myshop.produttore AS pr on p.Idproduttore = pr.Idproduttore " +
                "INNER JOIN myshop.categoria_prodotto AS cat on p.Idcategoria = cat.Idcategoria_prodotto " +
                "INNER JOIN myshop.sottocategoria AS sottocat on p.Idsottocategoria = sottocat.Idsottocategoria " +
                "INNER JOIN myshop.collocazione AS col on p.Idcollocazione = col.Idcollocazione " +
                "LEFT JOIN myshop.recensione AS rec on p.Idrecensione = rec.Idrecensione " +
                "INNER JOIN myshop.prodotti_magazzino AS pm on pm.Idprodotto = p.Idprodotto " +
                "INNER JOIN myshop.punto_vendita AS pv on pv.idmagazzino = pm.idmagazzino " +
                "INNER JOIN myshop.cliente as c on c.idpunto_vendita = pv.idpunto_vendita " +
                "WHERE pm.quantita = 0 AND c.Idcliente = '" + idUtente + "';");
        ArrayList<Prodotto> prodotti = new ArrayList<>();

        try {
            while (rs.next()) {
                prodotto = new Prodotto();
                prodotto.setIdProdotto(rs.getInt("Idprodotto"));
                prodotto.setNome(rs.getString("NomeProdotto"));
                prodotto.setDescrizione(rs.getString("Descrizione"));
                prodotto.setPrezzo(rs.getFloat("Prezzo"));
                prodotto.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                prodotto.setCollocazione(rs.getInt("Idcollocazione"), rs.getInt("Corsia"), rs.getInt("Scaffale"));
                prodotto.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                prodotto.setSottocategoria(rs.getInt("Idsottocategoria"), rs.getString("NomeSottocategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    prodotto.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                prodotto.setImmagine(rs.getString("Immagine"));
                prodotti.add(prodotto);
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
