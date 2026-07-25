package Test;

import DAO.CategoriaProdottoDAO;
import DAO.ICategoriaProdottoDAO;
import DAO.ISottocategoriaDAO;
import DAO.SottocategoriaDAO;
import Model.CategoriaProdotto;
import Model.Servizio;
import Model.SottoCategoria;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SottocategoriaDAOTest {

    private CategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
    private ArrayList<CategoriaProdotto> categorie = categoriaProdottoDAO.findAll();

    @Before
    public void setUp() {
        ISottocategoriaDAO sottocategoriaDAO = SottocategoriaDAO.getInstance();
        sottocategoriaDAO.addSottocategoria("Sottocategoria Test", categorie.get(0));
    }

    @After
    public void tearDown() {
        ISottocategoriaDAO sottocategoriaDAO = SottocategoriaDAO.getInstance();
        sottocategoriaDAO.removeByName("Sottocategoria Test");
    }

    @Test
    public void findAllTest() {
        ISottocategoriaDAO sottocategoriaDAO = SottocategoriaDAO.getInstance();
        ArrayList<SottoCategoria> sottocategorie = sottocategoriaDAO.findAllSottocategorie(categorie.get(0).getId());
        Assert.assertEquals(4, sottocategorie.size());
    }

    @Test
    public void addTest() {
        ISottocategoriaDAO sottocategoriaDAO = SottocategoriaDAO.getInstance();
        int rowCount = sottocategoriaDAO.addSottocategoria("Test", categorie.get(0));
        Assert.assertEquals(1, rowCount);
        sottocategoriaDAO.removeByName("Test");
    }
}
