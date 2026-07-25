package DAO;

import Model.Composite.IProdotto;
import Model.Ordine;

import java.util.ArrayList;

public interface IOrdineDAO {

    int addOrdine(IProdotto prodotto, int quantita, boolean isProdotto);
    ArrayList<Ordine> getProdottiOrdinatiByManagerId(int idManager);
    ArrayList<Ordine> getProdottiCompositiOrdinatiByManagerId(int idManager);
    int removeById(int idOrdine);
}
