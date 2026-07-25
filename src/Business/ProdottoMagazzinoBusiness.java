package Business;

import DAO.IProdottoDAO;
import DAO.IProdottoMagazzinoDAO;
import DAO.ProdottoDAO;
import DAO.ProdottoMagazzinoDAO;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.ProdottoMagazzino;

import java.util.ArrayList;
import java.util.List;

public class ProdottoMagazzinoBusiness {

    private IProdottoMagazzinoDAO prodottoMagazzinoDAO = ProdottoMagazzinoDAO.getInstance();
    private IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();

    public List<ProdottoMagazzino> getProdottiByManagerId(int idManager) {
        List<ProdottoMagazzino> prodottiMagazzino = new ArrayList<>();

        prodottiMagazzino.addAll(prodottoMagazzinoDAO.getProdottiByManagerId(idManager));
        List<ProdottoMagazzino> prodottiCompositiMagazzino = prodottoMagazzinoDAO.getProdottiCompositiByManagerId(idManager);


        for (ProdottoMagazzino prodottoCompositoMagazzino : prodottiCompositiMagazzino) {

            List<Prodotto> sottoprodotti = prodottoDAO.findByProdottoCompositoID(prodottoCompositoMagazzino.getProdotto().getId());
            for (Prodotto sottoprodotto : sottoprodotti) {
                ((ProdottoComposito) prodottoCompositoMagazzino.getProdotto()).add(sottoprodotto);
            }
        }
        prodottiMagazzino.addAll(prodottiCompositiMagazzino);
        return prodottiMagazzino;
    }

    public int updateQuantita(int idProdotto, int quantita, boolean isComposito) {

        return prodottoMagazzinoDAO.updateQuantita(idProdotto, quantita, isComposito);
    }

    public ProdottoMagazzino getProdottoByName(String nomeProdotto, int idProdotto) {

        return prodottoMagazzinoDAO.getProdottoByNameAndId(nomeProdotto, idProdotto);
    }

    public List<ProdottoMagazzino> getProdottiMagazzino() {

        return prodottoMagazzinoDAO.findAll();
    }

    public List<ProdottoMagazzino> getProdottiMagazzinoByCliente(int idUtente) {

        return prodottoMagazzinoDAO.getProdottiMagazzinoByCliente(idUtente);
    }
}
