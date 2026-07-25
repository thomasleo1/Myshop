package Test;

import DAO.IOrdineDAO;
import DAO.OrdineDAO;
import DAO.ProdottoDAO;
import Model.Composite.Prodotto;
import Model.Ordine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class OrdineDAOTest {

    @Before
    public void setUp() {
        IOrdineDAO ordineDAO = OrdineDAO.getInstance();
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findById(46);
        ordineDAO.addOrdine(prodotto, 2, true);
    }

    @After
    public void tearDown() {
        IOrdineDAO ordineDAO = OrdineDAO.getInstance();
        ordineDAO.removeById(4);
    }

    @Test
    public void getProdottiOrdinatiByManagerId() {
        IOrdineDAO ordineDAO = OrdineDAO.getInstance();
        ArrayList<Ordine> ordini = ordineDAO.getProdottiOrdinatiByManagerId(112);
        Assert.assertEquals(0, ordini.size());
    }

    @Test
    public void getProdottiCompositiOrdinatiByManagerId() {
        IOrdineDAO ordineDAO = OrdineDAO.getInstance();
        ArrayList<Ordine> ordini = ordineDAO.getProdottiCompositiOrdinatiByManagerId(112);
        Assert.assertEquals(0, ordini.size());
    }


}
