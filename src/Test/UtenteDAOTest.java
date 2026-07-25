package Test;

import Business.LoginResult;
import Business.SessionManager;
import Business.UtenteBusiness;
import DAO.ClienteDAO;
import DAO.IUtenteDAO;
import DAO.UtenteDAO;
import Model.Cliente;
import Model.Utente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UtenteDAOTest {

    @Before
    public void setUp() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("Utente" , "Test" , 27 , "1234567890", "test@gmail.com", "Via Lecce, Lecce", "Tester", "test123", "12345", "c", Utente.Stato.ATTIVO);
        utenteDAO.addUtente(utente);
    }

    @After
    public void tearDown() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        utenteDAO.removeByName("test123");
    }

    @Test
    public void findByNameTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByName("test123");
        Assert.assertEquals("Utente", u.getNome());
    }

    @Test
    public void findByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente u = utenteDAO.findByName("test123");
        Utente utente = utenteDAO.findById(u.getIdUtente());
        Assert.assertEquals("Utente", utente.getNome());
    }

    @Test
    public void  findAllTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        Assert.assertEquals(7, utenti.size());
    }

    @Test
    public void removeByNameTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        int rowCount = utenteDAO.removeByName("test123");
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void removeByIdTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByName("test123");
        int rowCount = utenteDAO.removeById(utente.getIdUtente());
        Assert.assertEquals(1, rowCount);
    }

    @Test
    public void addUtenteTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = new Utente("Utente2" , "Test2" , 27 , "1234567890", "testABC@gmail.com", "Via Lecce, Lecce", "Tester", "testABC", "12345", "c", Utente.Stato.ATTIVO);
        int rowCount = utenteDAO.addUtente(utente);
        Assert.assertEquals(1,rowCount);
        utenteDAO.removeByName("testABC");
    }


    @Test
    public void updateTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByName("test123");
        utente.setCognome("CognomeUpdate");
        int rowCount = utenteDAO.update(utente);
        Assert.assertEquals(1, rowCount);
        Assert.assertEquals("CognomeUpdate", utente.getCognome());
    }

    @Test
    public void userExistsTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean exist = utenteDAO.userExists("test123");
        Assert.assertEquals(true, exist);
    }

    @Test
    public void checkEmailTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        Utente utente = utenteDAO.findByName("test123");
        boolean emailExist = utenteDAO.checkEmail(utente.getEmail());
        Assert.assertEquals(true, emailExist);
    }

    @Test
    public void checkCredentialsTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        String username = "test123";
        String password = "12345";
        boolean crediantalsOk = utenteDAO.checkCredentials(username, password);
        Assert.assertEquals(true, crediantalsOk);
    }

    @Test
    public void isClienteTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        ClienteDAO clienteDAO = ClienteDAO.getInstance();
        Utente utente = utenteDAO.findByName("test123");
        clienteDAO.addById(utente.getIdUtente(), 16);
        boolean isCliente = utenteDAO.isCliente("test123");
        Assert.assertEquals(true, isCliente);
    }

    @Test
    public void isManagerTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean isManager = utenteDAO.isCliente("test123");
        Assert.assertEquals(false, isManager);
    }

    @Test
    public void isAmministratoreTest() {
        IUtenteDAO utenteDAO = UtenteDAO.getInstance();
        boolean isAmministratore = utenteDAO.isCliente("test123");
        Assert.assertEquals(false, isAmministratore);
    }

    @Test
    public void loginTest() {
        UtenteBusiness uB = UtenteBusiness.getInstance();
        String username = "tleo";
        String password = "12345";
        LoginResult result = uB.login(username, password);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.getResult() == LoginResult.Result.LOGIN_OK);
        Assert.assertNotNull(SessionManager.getSession().get(SessionManager.LOGGED_USER));
        Assert.assertTrue(SessionManager.getSession().get(SessionManager.LOGGED_USER) instanceof Cliente);

        Cliente c = (Cliente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        Assert.assertTrue(c.getIdUtente() == 185);
    }
}
