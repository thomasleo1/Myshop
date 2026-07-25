package DAO;

import Model.Recensione;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IRecensioneDAO {

    Recensione findById(int id);
    ArrayList<Recensione> findAll();
    int removeById(int id);
    int addRecensione(String commento, Date date, int idCliente, Recensione.Feedback feedback);
    int getIdRecensione(String commento, Date date, int idCliente, Recensione.Feedback feedback);

    ArrayList<Recensione> getRecensioniByIdCliente(int idCliente);

    ArrayList<Recensione> getRecensioniByIdManager(int idManager);

    int updateVisualizzato(int idRecensione);

    int updateRisposta(int idRecensione, String risposta);
}
