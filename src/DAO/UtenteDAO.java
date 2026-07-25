package DAO;

import DBConnection.Command.DbOperationExecutor;
import DBConnection.Command.IDbOperation;
import DBConnection.Command.ReadOperation;
import DBConnection.DbConnection;
import DBConnection.IDbConnection;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;
import Model.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteDAO implements IUtenteDAO {

    private static UtenteDAO instance = new UtenteDAO();
    private Utente utente;
    private static IDbConnection conn;
    private static ResultSet rs;

    private UtenteDAO() {
        utente = null;
        conn = null;
        rs = null;
    }

    public static UtenteDAO getInstance() {
        return instance;
    }


    public Utente findById(int id) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT Idutente, Nome, Cognome, Username, Password, Email, Tipo, Stato FROM myshop.utente WHERE Idutente = '" + id + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("Idutente"));
                utente.setNome(rs.getString("Nome"));
                utente.setCognome(rs.getString("Cognome"));
                utente.setUsername(rs.getString("Username"));
                utente.setPassword(rs.getString("Password"));
                utente.setEmail(rs.getString("Email"));
                utente.setTipo(rs.getString("Tipo"));
                utente.setStato(Utente.Stato.valueOf(rs.getString("Stato")));
                return utente;
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

    public Utente findByName(String username) {

        DbOperationExecutor executor = new DbOperationExecutor();
        String sql = "SELECT Idutente, Nome, Cognome, Username, Password, Email, Tipo, Stato FROM myshop.utente WHERE Username = '" + username + "';";
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                utente = new Utente();
                utente.setIdUtente(rs.getInt("Idutente"));
                utente.setNome(rs.getString("Nome"));
                utente.setCognome(rs.getString("Cognome"));
                utente.setUsername(rs.getString("Username"));
                utente.setPassword(rs.getString("Password"));
                utente.setEmail(rs.getString("Email"));
                utente.setTipo(rs.getString("Tipo"));
                utente.setStato(Utente.Stato.valueOf(rs.getString("Stato")));
                return utente;
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


    public ArrayList<Utente> findAll() {
        conn = DbConnection.getInstance();
        rs = conn.executeQuery("SELECT Nome, Cognome, Username, Password, Email, Tipo, Stato FROM utente");
        ArrayList<Utente> utenti = new ArrayList<>();
        try {
            while (rs.next()) {
                utente = new Utente();
                utente.setNome(rs.getString("Nome"));
                utente.setCognome(rs.getString("Cognome"));
                utente.setUsername(rs.getString("Username"));
                utente.setPassword(rs.getString("Password"));
                utente.setEmail(rs.getString("Email"));
                utente.setTipo(rs.getString("Tipo"));
                utente.setStato(Utente.Stato.valueOf(rs.getString("Stato")));
                utenti.add(utente);
            }
            return utenti;
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

    @Override
    public int addUtente(Utente utente) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("INSERT INTO myshop.utente (Nome, Cognome, Eta, Telefono, Email, Residenza, Professione,Username, Password, Tipo) VALUES ('" + utente.getNome() + "', '" + utente.getCognome() + "', '" + utente.getEta() + "', '" + utente.getTelefono() + "', '" + utente.getEmail() +  "', '" + utente.getResidenza() + "', '" + utente.getProfessione() + "', '" + utente.getUsername() + "', '" + utente.getPassword() + "', '" + utente.getTipo() + "');");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeById(int id) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM utente WHERE Idutente = '" + id + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int removeByName(String username) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate( "DELETE FROM utente WHERE Username = '" + username + "';");
        conn.close();
        return rowCount;
    }

    @Override
    public int update(Utente utente) {
        conn = DbConnection.getInstance();
        int rowCount = conn.executeUpdate("UPDATE utente SET Nome = '" + utente.getNome() + "', Cognome = '" + utente.getCognome() + "', Password = '" + utente.getPassword() + "', Email = '" + utente.getEmail() + "', Tipo = '" + utente.getTipo() + "' WHERE Username = '" + utente.getUsername() + "';");
        conn.close();
        return rowCount;
    }

    public boolean userExists(String username) {

        String sql = "SELECT count(*) AS count from myshop.utente as U where U.username='" + username + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkEmail(String email) {

        String sql = "SELECT count(*) AS count from myshop.utente as U where U.email='" + email + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkCredentials(String username, String password) {

        String sql = "SELECT count(*) AS count from myshop.utente as U where U.username='" + username + "' AND U.password='" + password + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean isCliente(String username) {

        String sql = "SELECT count(*) AS count FROM myshop.utente AS U INNER JOIN myshop.cliente AS C ON U.idutente = C.idcliente WHERE U.username='" + username + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isManager(String username) {

        String sql = "SELECT count(*) AS count FROM myshop.utente AS U INNER JOIN myshop.manager AS M ON U.idutente = M.idmanager WHERE U.username='" + username + "';";


        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isAmministratore(String username) {

        String sql = "SELECT count(*) AS count FROM myshop.utente AS U INNER JOIN myshop.amministratore AS A ON U.idutente = A.idamministratore WHERE U.username='" + username + "';";


        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("count");
                return count == 1;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cliente caricaCliente(String username) {

        Cliente c = new Cliente();

        String sql = "SELECT U.idutente, U.nome, U.cognome, U.email, U.username, U.stato FROM myshop.utente AS U INNER JOIN myshop.cliente as C ON U.idutente = C.idcliente WHERE U.username = '" + username + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                c.setIdUtente(rs.getInt("Idutente"));
                c.setNome(rs.getString("Nome"));
                c.setCognome(rs.getString("Cognome"));
                c.setEmail(rs.getString("Email"));
                c.setUsername(rs.getString("Username"));
                c.setStato(Utente.Stato.valueOf(rs.getString("Stato")));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }


    public Manager caricaManager(String username) {
        Manager m = new Manager();

        String sql = "SELECT U.idutente, U.nome, U.cognome, U.email, U.username FROM myshop.utente AS U INNER JOIN myshop.manager as M ON U.idutente = M.idmanager WHERE U.username = '" + username + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                m.setIdUtente(rs.getInt("Idutente"));
                m.setNome(rs.getString("Nome"));
                m.setCognome(rs.getString("Cognome"));
                m.setEmail(rs.getString("Email"));
                m.setUsername(rs.getString("Username"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Amministratore caricaAmministratore(String username) {

        Amministratore a = new Amministratore();

        String sql = "SELECT U.idutente, U.nome, U.cognome, U.email, U.username FROM myshop.utente AS U INNER JOIN myshop.amministratore as A ON U.idutente = A.idamministratore WHERE U.username = '" + username + "';";

        DbOperationExecutor executor = new DbOperationExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp).getResultSet();

        try {
            rs.next();
            if (rs.getRow() == 1) {
                a.setIdUtente(rs.getInt("Idutente"));
                a.setNome(rs.getString("Nome"));
                a.setCognome(rs.getString("Cognome"));
                a.setEmail(rs.getString("Email"));
                a.setUsername(rs.getString("Username"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }


}
