package Business;

import DAO.IProdottoDAO;
import DAO.ProdottoDAO;
import DAO.RecensioneDAO;
import Model.Composite.Prodotto;

import java.util.ArrayList;
import java.util.List;


public class ProdottoBusiness {

    private IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
    public Prodotto getProdottoByName(String nome) {

        return prodottoDAO.findByName(nome);
    }

    public Prodotto getProdottoById(int id) {

        return prodottoDAO.findById(id);
    }

    public ArrayList<Prodotto> getProdotti() {

        return prodottoDAO.findAll();
    }

    public void addProdotto(Prodotto prodotto, int idSottocategoria) {

        prodottoDAO.add(prodotto, idSottocategoria);
    }

    public void removeProdotto(int id) {

        prodottoDAO.removeById(id);
    }

    public ArrayList<Prodotto> getProdottiByIdMagazzino(int idMagazzino) {

        return prodottoDAO.getProdottiByIdMagazzino(idMagazzino);
    }

    public int updateProdotto(Prodotto prodotto) {

        return prodottoDAO.update(prodotto);
    }

    public ArrayList<Prodotto> getProdottiRecensitiByIdCliente(int idCliente) {

        return prodottoDAO.getProdottiRecensitiByIdCliente(idCliente);
    }

    public ArrayList<Prodotto> getProdottiRecensitiByIdManager(int idManager) {

        return prodottoDAO.getProdottiRecensitiByIdManager(idManager);
    }

    public List<Prodotto> getProdottiNonDisponibiliByIdUtente(int idUtente) {

        return prodottoDAO.getProdottiNonDisponibiliByIdUtente(idUtente);
    }
}
