package DAO;

import DBConnection.DbConnection;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.ProdottoMagazzino;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IProdottoMagazzinoDAO {


    List<ProdottoMagazzino> getProdottiByManagerId(int idManager);
    List<ProdottoMagazzino> getProdottiCompositiByManagerId(int idManager);
    ProdottoMagazzino getProdottoByNameAndId(String nomeProdotto, int idProdotto);
    ArrayList<ProdottoMagazzino> getProdottiByListaId(int idListaAcquisto);
    List<ProdottoMagazzino> findAll();
    int updateQuantita(int idProdotto, int quantita, boolean isComposito);
    List<ProdottoMagazzino> getProdottiMagazzinoByCliente(int idUtente);


}
