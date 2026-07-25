package Business;

import DAO.IOrdineDAO;
import DAO.OrdineDAO;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Ordine;
import java.util.ArrayList;


public class OrdineBusiness {

    private IOrdineDAO ordineDAO = OrdineDAO.getInstance();
    public void addOrdine(IProdotto prodotto, int quantita) {

        ordineDAO.addOrdine(prodotto, quantita, prodotto instanceof Prodotto);
    }

    public ArrayList<Ordine> getOrdiniByManagerId(int idManager) {

        ArrayList<Ordine> ordini = ordineDAO.getProdottiOrdinatiByManagerId(idManager);
        ordini.addAll(ordineDAO.getProdottiCompositiOrdinatiByManagerId(idManager));
        return ordini;
    }

    public int removeById(int idOrdine) {

        return ordineDAO.removeById(idOrdine);
    }
}
