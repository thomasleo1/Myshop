package Business;

import DAO.CollocazioneDAO;
import DAO.IMagazzinoDAO;
import DAO.MagazzinoDAO;
import Model.Collocazione;
import Model.Magazzino;

import java.util.ArrayList;

public class MagazzinoBusiness {

    private IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();

    public ArrayList<Magazzino> getMagazzini() {

        return magazzinoDAO.findAll();
    }

    public int getIdMagazzinoByIdProdotto(int idProdotto) {

        return magazzinoDAO.getIdMagazzinoByIdProdotto(idProdotto);
    }

    public int getIdMagazzinoByIdProdottoComposito(int idProdottoComposito) {

        return magazzinoDAO.getIdMagazzinoByIdProdottoComposito(idProdottoComposito);
    }

}
