package DAO;

import DBConnection.Command.DbOperationExecutor;
import DBConnection.Command.IDbOperation;
import DBConnection.Command.ReadOperation;
import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.ProdottoMagazzino;
import Model.Recensione;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServizioDAO implements IServizioDAO {

    private static ServizioDAO instance = new ServizioDAO();
    private Servizio servizio;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ServizioDAO() {
        servizio = null;
        conn = null;
        rs = null;
    }

    public static ServizioDAO getInstance() {
        return instance;
    }

    public int add(Servizio servizio) {
        conn = DbConnection.getInstance();
        String sql = "INSERT INTO servizio (Nome, Descrizione, Prezzo, Idproduttore, Idcategoria) VALUES ('" +
                servizio.getNome() + "', '" +
                servizio.getDescrizione() + "', '" +
                servizio.getPrezzo() + "', '" +
                servizio.getProduttore().getIdProduttore() + "', '" +
                servizio.getCategoria().getId() + "');";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public int update(Servizio servizio) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE servizio SET " +
                "Nome = '" + servizio.getNome() +
                "', Descrizione = '" + servizio.getDescrizione() +
                "', Prezzo = '" + servizio.getPrezzo() +
                "', Idproduttore = '" + servizio.getProduttore().getIdProduttore() +
                "', Idcategoria = '" + servizio.getCategoria().getId() +
                "' WHERE Idservizio = '" + servizio.getId() + "';");
        conn.close();
        return rowCount;
    }

    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM servizio WHERE Idservizio = '" + id + "';");
        conn.close();
        return rowCount;
    }

    public int removeByName(String nome) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM servizio WHERE Nome = '" + nome + "';");
        conn.close();
        return rowCount;
    }

    public Servizio findByName(String nome) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT s.Idservizio, s.Nome AS NomeServizio, s.Descrizione, s.Prezzo, s.Idproduttore, s.Idcategoria, s.Idrecensione, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
        "cat.idcategoria_servizio, cat.Nome AS NomeCategoria,\n" +
                "rec.idrecensione, rec.testo, rec.feedback\n" +
                "FROM myshop.servizio AS s\n" +
                "INNER JOIN myshop.produttore AS pr on s.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_servizio AS cat on s.Idcategoria = cat.Idcategoria_servizio\n" +
                "LEFT JOIN myshop.recensione AS rec on s.Idrecensione = rec.Idrecensione\n" +
                "WHERE s.Nome = '" + nome + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("Idservizio"));
                servizio.setNome(rs.getString("NomeServizio"));
                servizio.setDescrizione(rs.getString("Descrizione"));
                servizio.setPrezzo(rs.getFloat("Prezzo"));
                servizio.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                servizio.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    servizio.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                return servizio;
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

    public ArrayList<Servizio> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT s.Idservizio, s.Nome AS NomeServizio, s.Descrizione, s.Prezzo, s.Idproduttore, s.Idcategoria, s.Idrecensione, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_servizio, cat.Nome AS NomeCategoria,\n" +
                "rec.idrecensione, rec.testo, rec.feedback \n" +
                "FROM myshop.servizio AS s\n" +
                "INNER JOIN myshop.produttore AS pr on s.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_servizio AS cat on s.Idcategoria = cat.Idcategoria_servizio\n" +
                "LEFT JOIN myshop.recensione AS rec on s.Idrecensione = rec.Idrecensione\n");
        ArrayList<Servizio> servizi = new ArrayList<>();

        try {
            while (rs.next()) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("Idservizio"));
                servizio.setNome(rs.getString("NomeServizio"));
                servizio.setDescrizione(rs.getString("Descrizione"));
                servizio.setPrezzo(rs.getFloat("Prezzo"));
                servizio.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                servizio.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    servizio.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                servizi.add(servizio);
            }
            return servizi;
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


    public Servizio findById(int idServizio) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT s.Idservizio, s.Nome AS NomeServizio, s.Descrizione, s.Prezzo, s.Idproduttore, s.Idcategoria, s.Idrecensione, pr.idproduttore, pr.Nome AS NomeProduttore, pr.sitoweb, pr.citta, pr.nazione,\n" +
                "cat.idcategoria_servizio, cat.Nome AS NomeCategoria,\n" +
                "rec.idrecensione, rec.testo, rec.feedback \n" +
                "FROM myshop.servizio AS s\n" +
                "INNER JOIN myshop.produttore AS pr on s.Idproduttore = pr.Idproduttore\n" +
                "INNER JOIN myshop.categoria_servizio AS cat on s.Idcategoria = cat.Idcategoria_servizio\n" +
                "LEFT JOIN myshop.recensione AS rec on s.Idrecensione = rec.Idrecensione\n" +
                "WHERE s.Idservizio = '" + idServizio + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("Idservizio"));
                servizio.setNome(rs.getString("NomeServizio"));
                servizio.setDescrizione(rs.getString("Descrizione"));
                servizio.setPrezzo(rs.getFloat("Prezzo"));
                servizio.setProduttore(rs.getInt("Idproduttore"),rs.getString("NomeProduttore"), rs.getString("sitoWeb"),rs.getString("citta"), rs.getString("nazione"));
                servizio.setCategoria(rs.getInt("Idcategoria"), rs.getString("NomeCategoria"));
                if (rs.getObject("rec.feedback") != null) {
                    servizio.setRecensione(rs.getInt("Idrecensione"), rs.getString("Testo"), Recensione.Feedback.valueOf(rs.getString("Feedback")));
                }
                return servizio;
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

    public ArrayList<Servizio> getServiziByListaId(int idListaAcquisto) {
        conn = DbConnection.getInstance();
        String sql = "SELECT s.Nome AS NomeServizio, s.Idservizio, s.Prezzo\n" +
                "FROM associazione_prodotti_lista AS pl \n" +
                "LEFT JOIN servizio as s ON s.idservizio = pl.idservizio\n" +
                "WHERE pl.idLista_acquisto = '" + idListaAcquisto + "';";
        rs = conn.executeQuery(sql);
        ArrayList<Servizio> servizi = new ArrayList<>();

        try {
            while (rs.next()) {
                if (rs.getObject("s.Idservizio") != null) {
                    servizio = new Servizio();
                    servizio.setIdServizio(rs.getInt("s.Idservizio"));
                    servizio.setNome(rs.getString("NomeServizio"));
                    servizio.setPrezzo(rs.getFloat("s.Prezzo"));
                    servizi.add(servizio);
                }
            }
            return servizi;
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

    public boolean servizioExists(int id, String nome) {

        conn = DbConnection.getInstance();
        String sql = "SELECT * FROM myshop.servizio WHERE Idservizio = '" + id + "' AND Nome = '" + nome + "';";
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

    public int updateRecensione(int idServizio, int idRecensione) {
        conn = DbConnection.getInstance();
        String sql = "UPDATE servizio SET Idrecensione = '" + idRecensione + "' WHERE Idservizio = '" + idServizio + "';";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;
    }

    public List<Servizio> getServiziRecensitiByIdCliente(int idCliente) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT s.Idservizio, s.Nome, r.Idrecensione, r.Testo, r.Feedback FROM servizio AS s\n" +
                "INNER JOIN recensione as r on r.idrecensione = s.idrecensione\n" +
                "WHERE r.idcliente = '" + idCliente + "';");
        ArrayList<Servizio> servizi = new ArrayList<>();

        try {
            while (rs.next()) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("Idservizio"));
                servizio.setNome(rs.getString("Nome"));
                servizio.setRecensione(rs.getInt("r.Idrecensione"), rs.getString("r.Testo"), Recensione.Feedback.valueOf(rs.getString("r.Feedback")));
                servizi.add(servizio);
            }
            return servizi;
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

    public List<Servizio> getServiziRecensitiByIdManager(int idManager) {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT s.Idservizio, s.Nome, r.idrecensione, r.testo, r.feedback FROM servizio as s\n" +
                        "INNER JOIN recensione as r on r.idrecensione = s.idrecensione\n" +
                        "INNER JOIN cliente as c on c.idcliente = r.idcliente \n" +
                        "INNER JOIN utente as u on u.idutente = c.idcliente\n" +
                        "INNER JOIN punto_vendita as pv on pv.idpunto_vendita = c.idpunto_vendita \n" +
                        "WHERE r.risposta IS NULL AND pv.Idmanager = '" + idManager + "';");
        ArrayList<Servizio> servizi = new ArrayList<>();

        try {
            while (rs.next()) {
                servizio = new Servizio();
                servizio.setIdServizio(rs.getInt("Idservizio"));
                servizio.setNome(rs.getString("Nome"));
                servizio.setRecensione(rs.getInt("r.Idrecensione"), rs.getString("r.Testo"), Recensione.Feedback.valueOf(rs.getString("r.Feedback")));
                servizi.add(servizio);
            }
            return servizi;
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
