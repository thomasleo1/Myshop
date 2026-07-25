package Test;

import DAO.*;
import Model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ServizioDAOTest {

    private ProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
    private ArrayList<Produttore> produttori = produttoreDAO.findAll();
    private CategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
    private ArrayList<CategoriaServizio> categorieServizi = categoriaServizioDAO.findAll();

   @Before
    public void setUp() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio = new Servizio("ServizioTest","Servizio da testare", 150F, produttori.get(1), categorieServizi.get(1),new Recensione());
        servizioDAO.add(servizio);
    }

   @After
    public void tearDown() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        servizioDAO.removeByName("ServizioTest");
   }

    @Test
    public void findByIdTest() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio s = servizioDAO.findByName("ServizioTest");
        Servizio servizio = servizioDAO.findById(s.getId());
        Assert.assertTrue(servizio.getPrezzo() == 150F);
    }


    @Test
    public void findAllTest() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        ArrayList<Servizio> servizio = servizioDAO.findAll();
        Assert.assertEquals(3, servizio.size());
    }

    @Test
    public void removeByIdTest()  {
        IServizioDAO servizioDAO= ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("ServizioTest");
        int rowCount = servizioDAO.removeById(servizio.getId());
        Assert.assertEquals(1, rowCount);
    }


    @Test
    public void removeByNameTest()  {
        IServizioDAO servizioDAO= ServizioDAO.getInstance();
        int rowCount = servizioDAO.removeByName("ServizioTest");
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void addTest() {
        IServizioDAO servizioDAO = ServizioDAO.getInstance();
        Servizio servizio2 = new Servizio("ServizioTest2", "Test" ,400F,produttori.get(1), categorieServizi.get(1), new Recensione());
        int rowCount = servizioDAO.add(servizio2);
        Assert.assertEquals(1, rowCount);
        servizioDAO.removeByName("ServizioTest2");
    }

    @Test
    public void updateTest() {
        IServizioDAO servizioDAO= ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("ServizioTest");
        servizio.setDescrizione("Descrizione aggiornata");
        int rowCount = servizioDAO.update(servizio);
        Assert.assertEquals(1, rowCount);
        Assert.assertEquals("Descrizione aggiornata", servizio.getDescrizione());
    }

    @Test
    public void updateRecensioneTest() {
        IServizioDAO servizioDAO= ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("ServizioTest");
        RecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        ArrayList<Recensione> recensioni = recensioneDAO.findAll();
        int rowCount = servizioDAO.updateRecensione(servizio.getId(), recensioni.get(0).getIdRecensione());
        Assert.assertEquals(1, rowCount);

    }

    @Test
    public void getServiziByListaIdTest() {
        IServizioDAO servizioDAO= ServizioDAO.getInstance();
        ListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ArrayList<ListaAcquisto> liste = listaAcquistoDAO.findAll();
        ArrayList<Servizio> servizi = servizioDAO.getServiziByListaId(liste.get(0).getIdListaAcquisto());
        Assert.assertEquals(true, servizi.isEmpty());

    }

    @Test
    public void servizioExistsTest() {
        IServizioDAO servizioDAO= ServizioDAO.getInstance();
        Servizio servizio = servizioDAO.findByName("ServizioTest");
        boolean exists = servizioDAO.servizioExists(servizio.getId(), servizio.getNome());
        Assert.assertEquals(true, exists);
    }

    @Test
    public void getServiziRecensitiByIdCliente() {
        IServizioDAO servizioDAO= ServizioDAO.getInstance();
        List<Servizio> servizi = servizioDAO.getServiziRecensitiByIdCliente(185);
        Assert.assertEquals(1,servizi.size());
    }

    @Test
    public void getServiziRecensitiByIdManager() {
        IServizioDAO servizioDAO= ServizioDAO.getInstance();
        List<Servizio> servizi = servizioDAO.getServiziRecensitiByIdCliente(112);
        Assert.assertEquals(0,servizi.size());

    }
}
