package Test;

import DAO.CategoriaProdottoDAO;
import DAO.ICategoriaProdottoDAO;
import Model.CategoriaProdotto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CategoriaProdottoDAOTest {

    @Before
    public void setUp() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        categoriaProdottoDAO.add(new CategoriaProdotto("Categoria Prodotto Test"));
    }

    @After
    public void tearDown() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        categoriaProdottoDAO.removeByName("Categoria Prodotto Test");
    }

    @Test
    public void findAllTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAll();
        Assert.assertEquals(4, categorie.size());
    }

    @Test
    public void addTest() {
        ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
        int rowCount = categoriaProdottoDAO.add(new CategoriaProdotto("Test"));
        Assert.assertEquals(1, rowCount);
        categoriaProdottoDAO.removeByName("Test");
    }
}
