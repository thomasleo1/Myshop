package Test;

import DAO.*;
import Model.*;
import Model.Composite.Prodotto;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ProdottoDAOTest {

    @Before
    public void setUp() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        ArrayList<Produttore> produttori = produttoreDAO.findAll();
        CollocazioneDAO collocazioneDAO = CollocazioneDAO.getInstance();
        ArrayList<Collocazione> collocazioni = collocazioneDAO.findAllByIdMagazzino(1);
        CategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAll();
        SottocategoriaDAO sottocategoriaDAO = SottocategoriaDAO.getInstance();
        ArrayList<SottoCategoria> sottoCategorie = sottocategoriaDAO.findAllSottocategorie(categorie.get(0).getId());
        RecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        Recensione recensione = recensioneDAO.findById(20);
        Prodotto prodotto = new Prodotto("Test", "Descrizione Test",
                23F, produttori.get(0), collocazioni.get(0),
                categorie.get(0), sottoCategorie.get(0),
                recensione,"C:/Users/thoma/OneDrive/Desktop/MyShop/Immagini/Sedia.jpg");
        prodottoDAO.add(prodotto, sottoCategorie.get(0).getIdSottoCategoria());
    }

    @After
    public void tearDown() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("Test");
        prodottoDAO.removeById(prodotto.getId());
    }

    @Test
    public void findByNameTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("Test");
        Assert.assertEquals("Test", prodotto.getNome());
    }

    @Test
    public void findAllTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.findAll();
        Assert.assertEquals(false, prodotti.isEmpty());
    }

    @Test
    public void updateTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("Test");
        prodotto.setDescrizione("Descrizione Update");
        prodottoDAO.update(prodotto);
        Assert.assertEquals("Descrizione Update", prodotto.getDescrizione());
    }

    @Test
    public void findByProdottoCompositoId() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.findByProdottoCompositoID(13);
        Assert.assertEquals("Sedia", prodotti.get(0).getNome());
    }

    @Test
    public void getProdottiByIdMagazzino() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.getProdottiByIdMagazzino(1);
        Assert.assertEquals("Tenda Rossa", prodotti.get(0).getNome());
    }

    @Test
    public void prodottoExistsTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("Test");
        boolean exists = prodottoDAO.prodottoExists(prodotto.getId(), "Test");
        Assert.assertEquals(true, exists);
    }

    @Test
    public void updateRecensioneTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prodotto = prodottoDAO.findByName("Test");
        prodotto.setRecensione(21, null, Recensione.Feedback.DISCRETO);
        prodottoDAO.updateRecensione(prodotto.getId(), 21);
        Assert.assertEquals(21, prodotto.getRecensione().getIdRecensione());
    }

    @Test
    public void getProdottiRecensitiByIdClienteTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.getProdottiRecensitiByIdCliente(185);
        Assert.assertEquals(false, prodotti.isEmpty());
    }

    @Test
    public void getProdottiRecensitiByIdManagerTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        ArrayList<Prodotto> prodotti = prodottoDAO.getProdottiRecensitiByIdManager(112);
        Assert.assertEquals(false, prodotti.isEmpty());
    }

    @Test
    public void getProdottiNonDisponibiliByIdUtenteTest() {
        IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        List<Prodotto> prodotti = prodottoDAO.getProdottiNonDisponibiliByIdUtente(185);
        Assert.assertEquals(true, prodotti.isEmpty());
    }
}

