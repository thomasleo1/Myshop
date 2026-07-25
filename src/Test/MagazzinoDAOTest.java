package Test;

import DAO.IMagazzinoDAO;
import DAO.MagazzinoDAO;
import DAO.ProdottoCompositoDAO;
import DAO.ProdottoDAO;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.Magazzino;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MagazzinoDAOTest {

    @Before
    public void setUp() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = new Magazzino("Magazzino Test");
        magazzinoDAO.add(magazzino);
    }

    @After
    public void tearDown() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        magazzinoDAO.removeByName("Magazzino Test");
    }

    @Test
    public void findAllTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ArrayList<Magazzino> magazzini = magazzinoDAO.findAll();
        Assert.assertEquals(4, magazzini.size());
    }

    @Test
    public void addTest() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        Magazzino magazzino = new Magazzino("Test");
        int rowCount = magazzinoDAO.add(magazzino);
        Assert.assertEquals(1, rowCount);
        magazzinoDAO.removeByName("Test");
    }

    @Test
    public void getIdMagazzinoByIdProdotto() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("Sedia");
        int idMagazzino = magazzinoDAO.getIdMagazzinoByIdProdotto(prodotto.getId());
        Assert.assertEquals(2, idMagazzino);
    }

    @Test
    public void getIdMagazzinoByIdProdottoComposito() {
        IMagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Kit tessile");
        int idMagazzino = magazzinoDAO.getIdMagazzinoByIdProdottoComposito(prodottoComposito.getId());
        Assert.assertEquals(1, idMagazzino);
    }

}
