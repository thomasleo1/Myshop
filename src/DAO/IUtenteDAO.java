package DAO;

import Model.Amministratore;
import Model.Cliente;
import Model.Manager;
import Model.Utente;

import java.util.ArrayList;

public interface IUtenteDAO {

    Utente findById(int id);
    Utente findByName(String username);
    ArrayList<Utente> findAll();
    int addUtente(Utente utente);
    int removeById(int id);
    int removeByName(String username);
    int update(Utente utente);
    boolean userExists(String username);
    boolean checkEmail(String email);
    boolean checkCredentials(String username, String password);
    boolean isCliente(String username);
    boolean isManager(String username);
    boolean isAmministratore(String username);
    Cliente caricaCliente(String username);
    Manager caricaManager(String username);
    Amministratore caricaAmministratore(String username);
}


