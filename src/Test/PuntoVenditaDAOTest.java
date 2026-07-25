package Test;

import DAO.*;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.Magazzino;
import Model.PuntoVendita;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PuntoVenditaDAOTest {

    @Before
    public void setUp() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ArrayList<Magazzino> magazzini = magazzinoDAO.findAll();
        puntoVenditaDAO.add(new PuntoVendita("Punto vendita Test", "Indirizzo Test", magazzini.get(0)));
    }

    @After
    public void tearDown() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        puntoVenditaDAO.removeByName("Punto vendita Test");
    }

    @Test
    public void addTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        MagazzinoDAO magazzinoDAO = MagazzinoDAO.getInstance();
        ArrayList<Magazzino> magazzini = magazzinoDAO.findAll();
        int rowCount = puntoVenditaDAO.add(new PuntoVendita("Test", "Test", magazzini.get(1)));
        Assert.assertEquals(1, rowCount);
        puntoVenditaDAO.removeByName("Test");

    }

    @Test
    public void findByNameTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("Punto vendita Test");
        Assert.assertEquals("Punto vendita Test", puntoVendita.getNome());

    }

    @Test
    public void findByIdTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita pV = puntoVenditaDAO.findByName("Punto vendita Test");
        PuntoVendita puntoVendita = puntoVenditaDAO.findById(pV.getIdPuntoVendita());
        Assert.assertEquals("Punto vendita Test", puntoVendita.getNome());

    }

    @Test
    public void findAllTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        ArrayList<PuntoVendita> puntiVendita = puntoVenditaDAO.findAll();
        Assert.assertEquals(4, puntiVendita.size());
    }

    @Test
    public void getPuntiVenditaSenzaManagerTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        ArrayList<PuntoVendita> puntiVendita = puntoVenditaDAO.getPuntiVenditaSenzaManager();
        Assert.assertEquals(0, puntiVendita.get(0).getManager().getIdManager());
    }

    @Test
    public void updateManagerTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("Punto vendita Test");
        int rowCount = puntoVenditaDAO.updateManager(112, puntoVendita);
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void associaProdottoTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("Punto vendita Test");
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("Sedia");
        int rowCount = puntoVenditaDAO.associaProdotto(puntoVendita.getIdPuntoVendita(), prodotto.getId());
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void associaProdottoCompositoTest() {
        IPuntoVenditaDAO puntoVenditaDAO = PuntoVenditaDAO.getInstance();
        PuntoVendita puntoVendita = puntoVenditaDAO.findByName("Punto vendita Test");
        ProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Kit soggiorno");
        int rowCount = puntoVenditaDAO.associaProdottoComposito(puntoVendita.getIdPuntoVendita(), prodottoComposito.getId());
        Assert.assertEquals(1, rowCount);
    }

}
