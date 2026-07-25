package DAO;

import Model.Servizio;

import java.util.ArrayList;
import java.util.List;

public interface IServizioDAO {

    Servizio findByName(String nome);
    Servizio findById(int idServizio);
    ArrayList<Servizio> findAll();
    int add(Servizio servizio);
    int removeById(int id);
    int update(Servizio servizio);
    boolean servizioExists(int id, String nome);
    int updateRecensione(int idServizio, int idRecensione);
    ArrayList<Servizio> getServiziByListaId(int idListaAcquisto);
    int removeByName(String nome);

    List<Servizio> getServiziRecensitiByIdCliente(int idCliente);

    List<Servizio> getServiziRecensitiByIdManager(int idManager);

}
