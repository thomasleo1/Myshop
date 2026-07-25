package Business;

import DAO.*;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Recensione;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecensioneBusiness {

    private IRecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
    private IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
    private IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
    private IServizioDAO servizioDAO = ServizioDAO.getInstance();
    public void addRecensione(int id, String nome, Date data, int idCliente, String commento, Recensione.Feedback feedback) {

        recensioneDAO.addRecensione(commento, data, idCliente, feedback);
        int idRecensione = recensioneDAO.getIdRecensione(commento, data, idCliente, feedback);

        if (prodottoDAO.prodottoExists(id, nome)) {
            prodottoDAO.updateRecensione(id, idRecensione);

        } else if (prodottoCompositoDAO.prodottoCompExists(id, nome)) {
            prodottoCompositoDAO.updateRecensione(id,idRecensione);

        } else if (servizioDAO.servizioExists(id, nome)) {
            servizioDAO.updateRecensione(id,idRecensione);
        }
    }

    public ArrayList<Recensione> getRecensioniByIdcliente(int idCliente) {

        return recensioneDAO.getRecensioniByIdCliente(idCliente);
    }


    public ArrayList<Recensione> getRecensioniByIdManager(int idManager) {

        return recensioneDAO.getRecensioniByIdManager(idManager);
    }

    public int updateVisualizzato(int idRecensione) {

        return recensioneDAO.updateVisualizzato(idRecensione);
    }

    public int updateRisposta(int idRecensione, String risposta) {

        return recensioneDAO.updateRisposta(idRecensione, risposta);
    }
}
