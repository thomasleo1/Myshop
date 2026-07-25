package Business;

import DAO.CollocazioneDAO;
import DAO.ICollocazioneDAO;
import Model.Collocazione;


import java.util.ArrayList;

public class CollocazioneBusiness {

    public ArrayList<Collocazione> getCollocazioni(int idMagazzino) {

        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();

        return collocazioneDAO.findAllByIdMagazzino(idMagazzino);

    }
}
