package Test;


import DAO.IListaAcquistoDAO;
import DAO.ListaAcquistoDAO;
import DAO.ProdottoMagazzinoDAO;
import DAO.ServizioDAO;
import Model.ListaAcquisto;
import Model.ProdottoMagazzino;
import Model.Servizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaAcquistoDAOTest {

    @Before
    public void setUp() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date data = java.sql.Timestamp.valueOf(currentDateTime);
        listaAcquistoDAO.add(new ListaAcquisto("Lista Test", data));
    }

    @After
    public void tearDown() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        listaAcquistoDAO.removeByName("Lista Test");
    }

    @Test
    public void findByNameTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("Lista Test");
        Assert.assertEquals("Lista Test", listaAcquisto.getNome());
    }

    @Test
    public void findByListaIdTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findListaById(49);
        Assert.assertEquals("Natale", listaAcquisto.getNome());
    }

    @Test
    public void findAll() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ArrayList<ListaAcquisto> liste = listaAcquistoDAO.findAll();
        Assert.assertEquals(3, liste.size());
    }

    @Test
    public void addTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Date data = java.sql.Timestamp.valueOf(currentDateTime);
        int rowCount = listaAcquistoDAO.add(new ListaAcquisto("Test", data));
        Assert.assertEquals(1, rowCount);
        listaAcquistoDAO.removeByName("Test");
    }

    @Test
    public void removeByNameTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        int rowCount = listaAcquistoDAO.removeByName("Lista Test");
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void updateTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("Lista Test");
        listaAcquisto.setStatoLista(ListaAcquisto.StatoLista.PAGATA);
        listaAcquistoDAO.update(listaAcquisto);
        Assert.assertEquals(ListaAcquisto.StatoLista.PAGATA, listaAcquisto.getStatoLista());
    }

    @Test
    public void addProdottiToListTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ProdottoMagazzinoDAO prodottoMagazzinoDAO = ProdottoMagazzinoDAO.getInstance();
        List<ProdottoMagazzino> prodotti = prodottoMagazzinoDAO.findAll();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("Lista Test");
        listaAcquisto.addArticolo(prodotti.get(1));
        int rowCount = listaAcquistoDAO.addProdottiToList(listaAcquisto, 3, listaAcquisto.getArticoli().get(0).getProdotto().getId());
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void addServiziToListTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ServizioDAO servizioDAO = ServizioDAO.getInstance();
        ArrayList<Servizio> servizio = servizioDAO.findAll();
        ListaAcquisto listaAcquisto = listaAcquistoDAO.findByName("Lista Test");
        listaAcquisto.addServizo(servizio.get(0));
        int rowCount = listaAcquistoDAO.addServizioToList(listaAcquisto.getIdListaAcquisto(), servizio.get(0).getId());
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void findByIdClienteTest() {
        IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        ArrayList<ListaAcquisto> listeAcquisto = listaAcquistoDAO.findByIdCliente(185);
        Assert.assertEquals("Natale", listeAcquisto.get(0).getNome());
    }

}
