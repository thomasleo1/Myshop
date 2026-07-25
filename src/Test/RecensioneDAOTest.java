package Test;

import DAO.IRecensioneDAO;
import DAO.RecensioneDAO;
import Model.Recensione;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class RecensioneDAOTest {

    private LocalDateTime currentDateTime = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String dataFormatted = currentDateTime.format(formatter);
    private Date data = java.sql.Date.valueOf(dataFormatted);

    @Before
    public void setUp() {
        IRecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        recensioneDAO.addRecensione("Test", data, 185, Recensione.Feedback.OTTIMO);
    }

    @After
    public void tearDown() {
        IRecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        int id = recensioneDAO.getIdRecensione("Test", data, 185, Recensione.Feedback.OTTIMO);
        recensioneDAO.removeById(id);
    }

    @Test
    public void findByIdtest() {
        IRecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        int id = recensioneDAO.getIdRecensione("Test", data, 185, Recensione.Feedback.OTTIMO);
        Recensione recensione = recensioneDAO.findById(id);
        Assert.assertEquals("Test",recensione.getTesto());
    }

    @Test
    public void findAllTest() {
        IRecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        ArrayList<Recensione> recensioni = recensioneDAO.findAll();
        Assert.assertEquals(4, recensioni.size());
    }

    @Test
    public void getRecensioniByIdClienteTest() {
        IRecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        ArrayList<Recensione> recensioni = recensioneDAO.getRecensioniByIdCliente(185);
        Assert.assertEquals(false, recensioni.isEmpty());
    }

    @Test
    public void getRecensioniByIdManagerTest() {
        IRecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        ArrayList<Recensione> recensioni = recensioneDAO.getRecensioniByIdManager(112);
        Assert.assertEquals(false, recensioni.isEmpty());
    }

    @Test
    public void updateVisualizzatoTest() {
        IRecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        int id = recensioneDAO.getIdRecensione("Test", data, 185, Recensione.Feedback.OTTIMO);
        Recensione recensione = recensioneDAO.findById(id);
        recensione.setVisualizzato(true);
        recensioneDAO.updateVisualizzato(id);
        Assert.assertEquals(true, recensione.getVisualizzato());
    }

    @Test
    public void updateRispostaTest() {
        IRecensioneDAO recensioneDAO = RecensioneDAO.getInstance();
        int id = recensioneDAO.getIdRecensione("Test", data, 185, Recensione.Feedback.OTTIMO);
        Recensione recensione = recensioneDAO.findById(id);
        recensione.setRisposta("Risposta test");
        recensioneDAO.updateRisposta(recensione.getIdRecensione(), recensione.getRisposta());
        Assert.assertEquals("Risposta test", recensione.getRisposta());
    }
}
