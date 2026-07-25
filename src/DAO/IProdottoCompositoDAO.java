package DAO;

import Model.Composite.ProdottoComposito;

import java.util.ArrayList;
import java.util.List;

public interface IProdottoCompositoDAO {

    ProdottoComposito findByName(String nome);
    ProdottoComposito findById(int idProdottoComposito);
    ArrayList<ProdottoComposito> findAll();
    int add(ProdottoComposito prodottoComposito);
    int removeById(int id);
    int update(ProdottoComposito prodottoComposito);
    int addSottoprodotto(int idProdottoComposito, int idProdotto);
    ArrayList<ProdottoComposito> getProdottiCompositiByIdMagazzino(int idMagazzino);
    void addSottoprodottoById(int idProdottoComposito, int idSottoprodotto);
    int removeSottoprodottoById(int idProdottoComposito, int idSottoprodotto);
    boolean prodottoCompExists(int id, String nome);
    int updateRecensione(int idProdottoComp, int idRecensione);

    List<ProdottoComposito> getProdottiCompositiRecensitiByIdCliente(int idCliente);

    List<ProdottoComposito> getProdottiCompositiRecensitiByIdManager(int idManager);

    List<ProdottoComposito> getProdottiCompositiNonDisponibiliByIdUtente(int idUtente);
}
