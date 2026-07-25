package Business;

import DAO.IPuntoVenditaDAO;
import DAO.PuntoVenditaDAO;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.PuntoVendita;

import java.util.ArrayList;
import java.util.List;

public class PuntoVenditaBusiness {

    private IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
    public ArrayList<PuntoVendita> getPuntiVenditaSenzaManager() {

        return puntoVenditaDAO.getPuntiVenditaSenzaManager();
    }

    public int addManager(int idManager, PuntoVendita puntoVendita) {

        return puntoVenditaDAO.updateManager(idManager, puntoVendita);
    }

    public void addPuntoVendita(PuntoVendita puntoVendita) {

        puntoVenditaDAO.add(puntoVendita);
    }

    public void associaArticolo(IProdotto prodotto, int idPuntovendita, int idMagazzino) {

        if (prodotto instanceof Prodotto) {
            puntoVenditaDAO.associaProdottoMagazzino(idMagazzino, prodotto.getId());
            puntoVenditaDAO.associaProdotto(idPuntovendita, prodotto.getId());
        } else  {
            puntoVenditaDAO.associaProdottoCompositoMagazzino(idMagazzino, prodotto.getId());
            puntoVenditaDAO.associaProdottoComposito(idPuntovendita, prodotto.getId());
        }
    }

    public PuntoVendita getPuntoVenditaByName(String name) {

        return puntoVenditaDAO.findByName(name);
    }

    public PuntoVendita getPuntoVenditaById(int idPuntovendita) {

        return puntoVenditaDAO.findById(idPuntovendita);
    }

    public List<PuntoVendita> getPuntiVendita() {

        return puntoVenditaDAO.findAll();
    }
}
