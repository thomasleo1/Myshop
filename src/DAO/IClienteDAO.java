package DAO;

import Model.Cliente;
import Model.Utente;

import java.util.ArrayList;
import java.util.List;

public interface IClienteDAO {

    Cliente findById(int id);
    int addById(int idCliente, int idPuntoVendita);
    List<Cliente> getClientiByIdManager(int idManager);
    int updateStato(int idCliente, Utente.Stato stato);

}
