package DAO;

import Model.ListaAcquisto;

import java.util.ArrayList;

public interface IListaAcquistoDAO {

    ListaAcquisto findByName(String nome);
    ListaAcquisto findListaById(int idListaAcquisto);
    ArrayList<ListaAcquisto> findAll();
    int add(ListaAcquisto listaAcquisto);
    int removeByName(String nome);
    int update(ListaAcquisto listaAcquisto);
    int addProdottiToList(ListaAcquisto listaAcquisto, int quantita, int idProdotto);
    ArrayList<ListaAcquisto> findByIdCliente(int idUtente);
    ListaAcquisto getProdottiAcquistatiByIdUtente(int idCliente);
    int creaAssociazioneCliente(int idCliente, int idListaAcquisto);
    int addServizioToList(int idListaAcquisto, int idServizio);

}
