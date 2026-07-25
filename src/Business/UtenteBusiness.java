package Business;

import DAO.*;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;
import Model.Utente;

import java.util.List;

public class UtenteBusiness {

    private static UtenteBusiness instance;
    private IUtenteDAO utenteDAO = UtenteDAO.getInstance();
    private IManagerDAO managerDAO = ManagerDAO.getInstance();
    private IClienteDAO clienteDAO = ClienteDAO.getInstance();

    public static synchronized UtenteBusiness getInstance() {

        if (instance == null) {
            instance = new UtenteBusiness();
        }
        return instance;
    }

    public LoginResult login(String username, String password) {

        LoginResult result = new LoginResult();
        boolean userExists = utenteDAO.userExists(username);
        if (!userExists) {
            result.setResult(LoginResult.Result.USER_DOES_NOT_EXIST);
            result.setMessage("L'username inserito non esiste");
        }

        boolean credentialsOk = utenteDAO.checkCredentials(username, password);
        if (!credentialsOk) {
            result.setResult(LoginResult.Result.WRONG_PASSWORD);
            result.setMessage("La password digitata non è corretta");
            return result;
        }

        boolean isCliente = utenteDAO.isCliente(username);
        boolean isManager = utenteDAO.isManager(username);
        boolean isAmministratore = utenteDAO.isAmministratore(username);

        if (isCliente) {
            Cliente c = utenteDAO.caricaCliente(username);
            if (c.getStato() == Utente.Stato.DISABILITATO) {
                result.setResult(LoginResult.Result.USER_BLOCKED);
                result.setMessage("Sei stato disabilitato");
                return result;
            } else if (c.getStato() == Utente.Stato.CANCELLATO) {
                result.setResult(LoginResult.Result.USER_DOES_NOT_EXIST);
                result.setMessage("L'username inserito non esiste");
                return result;

            }
            SessionManager.getSession().put(SessionManager.LOGGED_USER, c);
            result.setMessage("Benvenuto " + c.getNome() + "!");
        } else if (isAmministratore) {
            Amministratore a = utenteDAO.caricaAmministratore(username);
            SessionManager.getSession().put(SessionManager.LOGGED_USER, a);
            result.setMessage("Benvenuto " + a.getNome() + "!");

        } else if (isManager) {
            Manager m = utenteDAO.caricaManager(username);
            SessionManager.getSession().put(SessionManager.LOGGED_USER, m);
            result.setMessage("Benvenuto " + m.getNome() + "!");
        }

        result.setResult(LoginResult.Result.LOGIN_OK);

        return result;

    }

    public void registrazione(String nome, String cognome, int eta, String telefono, String email, String residenza, String professione, String username, String password, String tipo, Float salario, int idPuntoVendita) {

        Utente utente = new Utente(nome,cognome,eta,telefono,email,residenza,professione,username,password,tipo,Utente.Stato.ATTIVO);
        utenteDAO.addUtente(utente);
        if (tipo.equalsIgnoreCase("c")) {
            ClienteDAO clienteDAO = ClienteDAO.getInstance();
            clienteDAO.addById(utenteDAO.findByName(username).getIdUtente(), idPuntoVendita);
        } else if (tipo.equalsIgnoreCase("m")) {
            ManagerDAO managerDAO = ManagerDAO.getInstance();
            managerDAO.addById(new Manager(nome,cognome,eta,telefono,email,residenza,professione,username,password,tipo,Utente.Stato.ATTIVO, salario),utenteDAO.findByName(username).getIdUtente());
        }

    }

    public boolean checkUtente(String username) {
         if (utenteDAO.userExists(username)) {
             return true;
         }
         else {
             return false;
         }
    }

    public boolean checkEmail(String email) {
        if (utenteDAO.checkEmail(email)) {
            return true;
        }
        else {
            return false;
        }
    }

    public Manager getManager(String username) {

        return managerDAO.findByUsername(username);
    }

    public List<Cliente> getClientiByIdManager(int idManager) {

        return clienteDAO.getClientiByIdManager(idManager);
    }

    public Cliente getCliente(int idUtente) {

        return clienteDAO.findById(idUtente);
    }

    public int updateStato(int idCliente, Utente.Stato stato) {

        return clienteDAO.updateStato(idCliente, stato);
    }

    public int removeById(int idCliente) {

        return utenteDAO.removeById(idCliente);
    }
}
