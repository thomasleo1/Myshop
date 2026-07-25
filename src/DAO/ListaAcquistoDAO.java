package DAO;

import DBConnection.Command.DbOperationExecutor;
import DBConnection.Command.IDbOperation;
import DBConnection.Command.ReadOperation;
import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.ListaAcquisto;
import Model.ProdottoMagazzino;
import Model.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaAcquistoDAO implements IListaAcquistoDAO {

    private static ListaAcquistoDAO instance = new ListaAcquistoDAO();
    private ListaAcquisto listaAcquisto;
    private static IDbConnection conn;
    private static ResultSet rs;

    private ListaAcquistoDAO() {
        listaAcquisto = null;
        conn = null;
        rs = null;
    }

    public static ListaAcquistoDAO getInstance() {
        return instance;
    }

    public int add(ListaAcquisto listaAcquisto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO lista_acquisto (Nome, Data) VALUES ('" + listaAcquisto.getNome() + "', '" + listaAcquisto.getData() + "');");
        conn.close();
        return rowCount;
    }

    public int update(ListaAcquisto listaAcquisto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE lista_acquisto SET Nome = '" + listaAcquisto.getNome() + "', Data = '" + listaAcquisto.getData() + "', StatoLista = '" + listaAcquisto.getStatoLista() + "' WHERE Nome = '" + listaAcquisto.getNome() + "';");
        conn.close();
        return rowCount;
    }

    public int removeByName(String nome) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM lista_acquisto WHERE Nome = '" + nome + "';");
        conn.close();
        return rowCount;
    }

    public ListaAcquisto findByName(String nome) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT LA.Idlista_acquisto, LA.nome AS NomeLista, LA.data, LA.statolista FROM myshop.lista_acquisto as LA\n" +
                "LEFT JOIN associazione_cliente_lista AS map ON map.idlista_Acquisto = LA.idlista_acquisto\n" +
                "WHERE map.idcliente IS NULL AND LA.nome = '" + nome + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdListaAcquisto(rs.getInt("Idlista_acquisto"));
                listaAcquisto.setNome(rs.getString("NomeLista"));
                listaAcquisto.setData(rs.getDate("Data"));
                listaAcquisto.setStatoLista(ListaAcquisto.StatoLista.valueOf(rs.getString("Statolista")));
                return listaAcquisto;
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

    public ListaAcquisto findListaById(int idListaAcquisto) {
        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT LA.Idlista_acquisto, LA.nome AS NomeLista, LA.data, LA.statolista FROM myshop.lista_acquisto as LA\n" +
                "WHERE LA.Idlista_acquisto = '" + idListaAcquisto + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdListaAcquisto(rs.getInt("Idlista_acquisto"));
                listaAcquisto.setNome(rs.getString("NomeLista"));
                listaAcquisto.setData(rs.getDate("Data"));
                listaAcquisto.setStatoLista(ListaAcquisto.StatoLista.valueOf(rs.getString("Statolista")));
                return listaAcquisto;
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

    public ArrayList<ListaAcquisto> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT LA.Idlista_acquisto, LA.nome AS NomeLista, LA.data, LA.statolista FROM myshop.lista_acquisto as LA");
        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();

        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdListaAcquisto(rs.getInt("Idlista_acquisto"));
                listaAcquisto.setNome(rs.getString("NomeLista"));
                listaAcquisto.setData(rs.getDate("Data"));
                listaAcquisto.setStatoLista(ListaAcquisto.StatoLista.valueOf(rs.getString("StatoLista")));
                listeAcquisto.add(listaAcquisto);
            }
            return listeAcquisto;
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

    public int addProdottiToList(ListaAcquisto listaAcquisto, int quantita, int idProdotto) {

        conn = DbConnection.getInstance();
        String idParam = "";
        if (listaAcquisto.getArticoli().get(0).getProdotto() instanceof Prodotto) {
            idParam = "idProdotto";
        } else {
            idParam = "idProdotto_Composito";
        }
        int rowCount = conn.executeUpdate("INSERT INTO associazione_prodotti_lista (Idlista_acquisto, " + idParam + ", Quantita) VALUES ('" + listaAcquisto.getIdListaAcquisto() + "', '" + idProdotto + "', '" + quantita + "');");
        conn.close();
        return rowCount;

    }

    public ArrayList<ListaAcquisto> findByIdCliente(int idUtente) {

        conn = DbConnection.getInstance();
        String sql = "SELECT LA.Idlista_acquisto, LA.nome AS NomeLista, LA.data, LA.statolista FROM myshop.lista_acquisto as LA " +
                "INNER JOIN associazione_cliente_lista AS map ON map.IdLista_acquisto = LA.Idlista_acquisto " +
                "WHERE map.Idcliente = '" + idUtente + "'";
        rs = conn.executeQuery(sql);
        ArrayList<ListaAcquisto> listeAcquisto = new ArrayList<>();

        try {
            while (rs.next()) {
                listaAcquisto = new ListaAcquisto();
                listaAcquisto.setIdListaAcquisto(rs.getInt("Idlista_acquisto"));
                listaAcquisto.setNome(rs.getString("NomeLista"));
                listaAcquisto.setData(rs.getDate("Data"));
                listaAcquisto.setStatoLista(ListaAcquisto.StatoLista.valueOf(rs.getString("StatoLista")));
                listeAcquisto.add(listaAcquisto);
            }
            return listeAcquisto;
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


    public int creaAssociazioneCliente(int idCliente, int idListaAcquisto) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO associazione_cliente_lista (Idcliente, Idlista_acquisto) VALUES ('" + idCliente + "', '" + idListaAcquisto + "');");
        conn.close();
        return rowCount;
    }

    public int addServizioToList(int idListaAcquisto, int idServizio) {
        conn = DbConnection.getInstance();
        String sql = "INSERT INTO associazione_prodotti_lista (Idlista_acquisto, Idservizio) VALUES ('" + idListaAcquisto + "', '" + idServizio + "');";
        int rowCount = conn.executeUpdate(sql);
        conn.close();
        return rowCount;

    }

    public ListaAcquisto getProdottiAcquistatiByIdUtente(int idCliente) {
        conn = DbConnection.getInstance();
        String sql = "SELECT la.idlista_acquisto, la.statolista, p.nome As NomeProdotto, pc.nome AS NomeProdottoComp, s.nome As NomeServizio, p.idprodotto, pc.idprodotto_composito, s.idservizio FROM lista_acquisto AS la " +
        "INNER JOIN associazione_cliente_lista AS cl ON cl.idlista_acquisto = la.idlista_acquisto " +
        "INNER JOIN associazione_prodotti_lista AS pl ON pl.idlista_acquisto = la.idlista_acquisto " +
        "LEFT JOIN prodotto AS p ON p.idprodotto = pl.idprodotto AND p.idrecensione IS NULL " +
        "LEFT JOIN prodotto_composito as pc On pc.idprodotto_composito = pl.idprodotto_composito AND pc.idrecensione IS NULL " +
        "LEFT JOIN servizio as s On s.idservizio = pl.idservizio AND s.idrecensione IS NULL " +
        "WHERE cl.idcliente = '" + idCliente + "' AND la.statolista = 'PAGATA';";
        rs = conn.executeQuery(sql);

        try {
            listaAcquisto = new ListaAcquisto();
            while (rs.next()) {
                ProdottoMagazzino prodottoMagazzino = new ProdottoMagazzino();
                if (rs.getObject("pc.Idprodotto_composito") != null) {
                    ProdottoComposito prodottoComposito = new ProdottoComposito();
                    prodottoComposito.setIdProdottoComposito(rs.getInt("pc.idProdotto_composito"));
                    prodottoComposito.setNome(rs.getString("NomeProdottoComp"));
                    prodottoMagazzino.setProdotto(prodottoComposito);
                    if (!listaAcquisto.checkIfProdottoDoesNotExists(prodottoMagazzino)) {
                        listaAcquisto.addArticolo(prodottoMagazzino);
                    }
                } else if (rs.getObject("p.Idprodotto") != null){
                    Prodotto prodotto = new Prodotto();
                    prodotto.setIdProdotto(rs.getInt("p.idProdotto"));
                    prodotto.setNome(rs.getString("NomeProdotto"));
                    prodottoMagazzino.setProdotto(prodotto);
                    if (!listaAcquisto.checkIfProdottoDoesNotExists(prodottoMagazzino)) {
                        listaAcquisto.addArticolo(prodottoMagazzino);
                    }
                } else if (rs.getObject("s.Idservizio") != null) {
                    Servizio servizio = new Servizio();
                    servizio.setIdServizio(rs.getInt("s.idservizio"));
                    servizio.setNome(rs.getString("NomeServizio"));
                    if (!listaAcquisto.checkIfServizioDoesNotExists(servizio)) {
                        listaAcquisto.addServizo(servizio);
                    }

                }
            }
            return listaAcquisto;
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
