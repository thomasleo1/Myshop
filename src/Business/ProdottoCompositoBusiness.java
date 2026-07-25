package Business;

import DAO.IProdottoCompositoDAO;
import DAO.IProdottoDAO;
import DAO.ProdottoCompositoDAO;
import DAO.ProdottoDAO;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;

import java.util.ArrayList;
import java.util.List;

public class ProdottoCompositoBusiness {

    private IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
    private IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

    public ProdottoComposito getProdottoCompositoByName(String nome) {

        ProdottoComposito prodottoComposito =  prodottoCompositoDAO.findByName(nome);

        ArrayList<Prodotto> sottoprodotti = prodottoDAO.findByProdottoCompositoID(prodottoComposito.getId());
        Float prezzo = 0F;
        for (Prodotto sottoprodotto : sottoprodotti) {
            prodottoComposito.add(sottoprodotto);
            prezzo += sottoprodotto.getPrezzo();
        }
        prodottoComposito.setPrezzo(prezzo);
        return prodottoComposito;
    }
    public ArrayList<ProdottoComposito> getProdottiCompositi() {

        ArrayList<ProdottoComposito> prodottiComp = prodottoCompositoDAO.findAll();

        for (ProdottoComposito prodottoComp : prodottiComp) {
            ArrayList<Prodotto> sottoprodotti = prodottoDAO.findByProdottoCompositoID(prodottoComp.getId());
            Float prezzo = 0F;
            for (Prodotto sottoprodotto : sottoprodotti) {
                prodottoComp.add(sottoprodotto);
                prezzo += sottoprodotto.getPrezzo();
            }
            prodottoComp.setPrezzo(prezzo);
        }
        return prodottiComp;
    }

    public void addProdottoComposito(ProdottoComposito prodottoComposito) {

        prodottoCompositoDAO.add(prodottoComposito);
    }

    public void addSottoprodotto(int idProdottoComposito, int idProdotto) {

        prodottoCompositoDAO.addSottoprodotto(idProdottoComposito, idProdotto);
    }

    public int removeProdottoComposito(int id) {

        return prodottoCompositoDAO.removeById(id);
    }

    public ArrayList<ProdottoComposito> getProdottiCompositiByIdMagazzino(int idMagazzino) {

        ArrayList<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.getProdottiCompositiByIdMagazzino(idMagazzino);

        for (ProdottoComposito prodottoComposito : prodottiCompositi) {
            ArrayList<Prodotto> sottoprodotti = prodottoDAO.findByProdottoCompositoID(prodottoComposito.getId());
            Float prezzo = 0F;
            for (Prodotto sottoprodotto : sottoprodotti) {
                prodottoComposito.add(sottoprodotto);
                prezzo += sottoprodotto.getPrezzo();
            }
            prodottoComposito.setPrezzo(prezzo);
        }
        return prodottiCompositi;
    }

    public ProdottoComposito getProdottoCompositoById(int idProdottoComposito) {
        ProdottoComposito prodottoComposito =  prodottoCompositoDAO.findById(idProdottoComposito);


        ArrayList<Prodotto> sottoprodotti = prodottoDAO.findByProdottoCompositoID(prodottoComposito.getId());
        Float prezzo = 0F;
        for (Prodotto sottoprodotto : sottoprodotti) {
            prodottoComposito.add(sottoprodotto);
            prezzo += sottoprodotto.getPrezzo();
        }
        prodottoComposito.setPrezzo(prezzo);
        return prodottoComposito;
    }

    public int updateProdottoComposito(ProdottoComposito prodottoComposito) {
        return prodottoCompositoDAO.update(prodottoComposito);
    }

    public void updateSottoprodotti(int idProdottoComposito, List<Prodotto> listToRemove, List<Prodotto> listToAdd) {

        for (Prodotto sottoprodottoToRemove : listToRemove){
            prodottoCompositoDAO.removeSottoprodottoById(idProdottoComposito,sottoprodottoToRemove.getId());
        }

        for (Prodotto sottoprodottoToAdd : listToAdd){
            prodottoCompositoDAO.addSottoprodottoById(idProdottoComposito,sottoprodottoToAdd.getId());
        }


    }

    public List<ProdottoComposito> getProdottiCompositiRecensitiByIdCliente(int idCliente) {
        ProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        return prodottoCompositoDAO.getProdottiCompositiRecensitiByIdCliente(idCliente);

    }

    public List<ProdottoComposito> getProdottiCompositiRecensitiByIdManager(int idManager) {
        ProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        return prodottoCompositoDAO.getProdottiCompositiRecensitiByIdManager(idManager);

    }

    public List<ProdottoComposito> getProdottiNonDisponibiliByIdUtente(int idUtente) {
        ProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        List<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.getProdottiCompositiNonDisponibiliByIdUtente(idUtente);
        for (ProdottoComposito prodottoComposito : prodottiCompositi) {
            ArrayList<Prodotto> sottoprodotti = prodottoDAO.findByProdottoCompositoID(prodottoComposito.getId());
            Float prezzo = 0F;
            for (Prodotto sottoprodotto : sottoprodotti) {
                prodottoComposito.add(sottoprodotto);
                prezzo += sottoprodotto.getPrezzo();
            }
            prodottoComposito.setPrezzo(prezzo);
        }

        return prodottiCompositi;
    }
}
