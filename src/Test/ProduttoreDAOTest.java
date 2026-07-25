package Test;

import DAO.IProduttoreDAO;
import DAO.ProduttoreDAO;
import Model.Produttore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ProduttoreDAOTest {

   @Before
    public void setUp() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        produttoreDAO.add(new Produttore("ProduttoreTest", "test.com", "Lecce", "Italia"));
    }

    @After
    public void tearDown() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        produttoreDAO.removeByName("ProduttoreTest");
    }


    @Test
    public void findAllTest() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        ArrayList<Produttore> produttore = produttoreDAO.findAll();
        Assert.assertEquals(4, produttore.size());
    }

    @Test
    public void addTest() {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        int rowCount = produttoreDAO.add(new Produttore("ProduttoreTest2", "test.com", "Torino", "Italia"));
        Assert.assertEquals(1, rowCount);
        produttoreDAO.removeByName("ProduttoreTest2");
    }

    @Test
    public void removeByIdTest()  {
        IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
        int rowCount = produttoreDAO.removeByName("ProduttoreTest");
        Assert.assertEquals(1, rowCount);
    }

}
