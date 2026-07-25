package DAO;

import Model.PuntoVendita;

import java.util.ArrayList;

public interface IPuntoVenditaDAO {

    PuntoVendita findByName(String nome);
    PuntoVendita findById(int idPuntovendita);
    ArrayList<PuntoVendita> findAll();
    int add(PuntoVendita puntoVendita);
    int removeByName(String nome);
    ArrayList<PuntoVendita> getPuntiVenditaSenzaManager();
    int updateManager(int idManager, PuntoVendita puntoVendita);
    int associaProdottoComposito(int idPuntoVendita, int idProdottoComposito);
    int associaProdotto(int idPuntoVendita, int idProdotto);
    int associaProdottoMagazzino(int idMagazzino, int idProdotto);
    int associaProdottoCompositoMagazzino(int idMagazzino, int idProdottoComposito);
}
