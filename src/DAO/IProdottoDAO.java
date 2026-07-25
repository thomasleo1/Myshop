package DAO;

import Model.Composite.Prodotto;

import java.util.ArrayList;
import java.util.List;

public interface IProdottoDAO {

    Prodotto findByName(String nome);
    Prodotto findById(int id);
    ArrayList<Prodotto> findAll();
    int add(Prodotto prodotto, int idSottocategoria);
    int removeById(int id);
    int update(Prodotto prodotto);
    ArrayList<Prodotto>  findByProdottoCompositoID(int id);
    ArrayList<Prodotto> getProdottiByIdMagazzino(int idMagazzino);
    boolean prodottoExists(int id, String nome);
    int updateRecensione(int idProdotto, int idRecensione);

    ArrayList<Prodotto> getProdottiRecensitiByIdCliente(int idCliente);

    ArrayList<Prodotto> getProdottiRecensitiByIdManager(int idManager);

    List<Prodotto> getProdottiNonDisponibiliByIdUtente(int idUtente);
}
