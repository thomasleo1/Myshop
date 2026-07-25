package Test;

import DAO.CategoriaServizioDAO;
import DAO.ICategoriaServizioDAO;
import Model.CategoriaServizio;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CategoriaServizioDAOTest {

    @Before
    public void setUp() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        categoriaServizioDAO.add(new CategoriaServizio("Categoria Servizio Test"));
    }

    @After
    public void tearDown() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        categoriaServizioDAO.remove("Categoria Servizio Test");
    }

    @Test
    public void findAllTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        ArrayList<CategoriaServizio> categorie = categoriaServizioDAO.findAll();
        Assert.assertEquals(3, categorie.size());
    }

    @Test
    public void addTest() {
        ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
        int rowCount = categoriaServizioDAO.add(new CategoriaServizio("Test"));
        Assert.assertEquals(1, rowCount);
        categoriaServizioDAO.remove("Test");
    }
}
