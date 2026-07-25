package Test;

import DAO.CollocazioneDAO;
import DAO.ICollocazioneDAO;
import Model.Collocazione;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CollocazioneDAOTest {


    @Test
    public void findAllByIdMagazzinoTest() {
        ICollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAllByIdMagazzino(2);
        Assert.assertEquals(3, collocazioni.size());
    }
}
