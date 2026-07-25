package Test;

import DAO.*;
import Model.*;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProdottoCompositoDAOTest {

    /*

    int updateRecensione(int idProdottoComp, int idRecensione);

    List<ProdottoComposito> getProdottiCompositiRecensitiByIdCliente(int idCliente);

    List<ProdottoComposito> getProdottiCompositiRecensitiByIdManager(int idManager);

    List<ProdottoComposito> getProdottiNonDisponibiliByIdUtente(int idUtente);
     */

    @Before
    public void setUp() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        ArrayList<Produttore> produttori = produttoreDAO.findAll();
        CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAllByIdMagazzino(1);
        CategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAll();
        RecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        Recensione recensione = recensioneDAO.findById(20);
        ProdottoComposito prodottoComposito = new ProdottoComposito("Test", "Prodotto Comp Test",
                produttori.get(0), collocazioni.get(0),
                categorie.get(0),
                recensione,"C:/Users/thoma/OneDrive/Desktop/MyShop/Immagini/Sedia.jpg");
        prodottoCompositoDAO.add(prodottoComposito);
    }

    @After
    public void tearDown() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Test");
        prodottoCompositoDAO.removeById(prodottoComposito.getId());
    }

    @Test
    public void findByNameTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Test");
        Assert.assertEquals("Test", prodottoComposito.getNome());
    }

    @Test
    public void findAllTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.findAll();
        Assert.assertEquals(false, prodottiCompositi.isEmpty());
    }

    @Test
    public void updateTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Test");
        prodottoComposito.setDescrizione("Descrizione Update");
        prodottoCompositoDAO.update(prodottoComposito);
        Assert.assertEquals("Descrizione Update", prodottoComposito.getDescrizione());
    }

    @Test
    public void addSottoprodottiTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Test");
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findById(46);
        int rowCount = prodottoCompositoDAO.addSottoprodotto(prodottoComposito.getId(), prodotto.getId());
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void removeSottoprodottoTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Test");
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findById(46);
        prodottoCompositoDAO.addSottoprodotto(prodottoComposito.getId(), prodotto.getId());
        int rowCount = prodottoCompositoDAO.removeSottoprodottoById(prodottoComposito.getId(), prodotto.getId());
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void getProdottiCompositiByIdMagazzinoTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ArrayList<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.getProdottiCompositiByIdMagazzino(1);
        Assert.assertEquals(false, prodottiCompositi.isEmpty());
    }

    @Test
    public void prodottoExistsTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Test");
        boolean exists = prodottoCompositoDAO.prodottoCompExists(prodottoComposito.getId(), "Test");
        Assert.assertEquals(true, exists);
    }

    @Test
    public void updateRecensioneTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        ProdottoComposito prodottoComposito = prodottoCompositoDAO.findByName("Test");
        prodottoComposito.setRecensione(21, null, Recensione.Feedback.DISCRETO);
        prodottoCompositoDAO.updateRecensione(prodottoComposito.getId(), 21);
        Assert.assertEquals(21, prodottoComposito.getRecensione().getIdRecensione());
    }

    @Test
    public void getProdottiRecensitiByIdClienteTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        List<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.getProdottiCompositiRecensitiByIdCliente(185);
        Assert.assertEquals(false, prodottiCompositi.isEmpty());
    }

    @Test
    public void getProdottiRecensitiByIdManagerTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        List<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.getProdottiCompositiRecensitiByIdManager(112);
        Assert.assertEquals(false, prodottiCompositi.isEmpty());
    }

    @Test
    public void getProdottiCompisitiNonDisponibiliByIdUtenteTest() {
        IProdottoCompositoDAO prodottoCompositoDAO = ProdottoCompositoDAO.getInstance();
        List<ProdottoComposito> prodottiCompositi = prodottoCompositoDAO.getProdottiCompositiNonDisponibiliByIdUtente(185);
        Assert.assertEquals(true, prodottiCompositi.isEmpty());
    }
}
