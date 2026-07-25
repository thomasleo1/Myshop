package Test;

import DAO.IProdottoMagazzinoDAO;
import DAO.ProdottoMagazzinoDAO;
import Model.ProdottoMagazzino;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProdottoMagazzinoDAOTest {

    @Test
    public void getProdottiByManagerIdTest() {
        IProdottoMagazzinoDAO prodottoMagazzinoDAO = ProdottoMagazzinoDAO.getInstance();
        List<ProdottoMagazzino> prodottiMagazzino = prodottoMagazzinoDAO.getProdottiByManagerId(112);
        Assert.assertEquals(false, prodottiMagazzino.isEmpty());
    }

    @Test
    public void getProdottiCompositiByManagerIdTest() {
        IProdottoMagazzinoDAO prodottoMagazzinoDAO = ProdottoMagazzinoDAO.getInstance();
        List<ProdottoMagazzino> prodottiMagazzino = prodottoMagazzinoDAO.getProdottiCompositiByManagerId(112);
        Assert.assertEquals(false, prodottiMagazzino.isEmpty());
    }

    @Test
    public void getProdottoByNameAndIdTest() {
        IProdottoMagazzinoDAO prodottoMagazzinoDAO = ProdottoMagazzinoDAO.getInstance();
        ProdottoMagazzino prodottoMagazzino = prodottoMagazzinoDAO.getProdottoByNameAndId("Sedia", 46);
        Assert.assertEquals(50,prodottoMagazzino.getQuantita());
    }

    @Test
    public void getProdottiByListaIdTest() {
        IProdottoMagazzinoDAO prodottoMagazzinoDAO = ProdottoMagazzinoDAO.getInstance();
        ArrayList<ProdottoMagazzino> prodottiMagazzino = prodottoMagazzinoDAO.getProdottiByListaId(49);
        Assert.assertEquals(false, prodottiMagazzino.isEmpty());
    }

    @Test
    public void findAllTest() {
        IProdottoMagazzinoDAO prodottoMagazzinoDAO = ProdottoMagazzinoDAO.getInstance();
        List<ProdottoMagazzino> prodottiMagazzino = prodottoMagazzinoDAO.findAll();
        Assert.assertEquals(7, prodottiMagazzino.size());
    }

    @Test
    public void getProdottiMagazzinoByIdClienteTest() {
        IProdottoMagazzinoDAO prodottoMagazzinoDAO = ProdottoMagazzinoDAO.getInstance();
        List<ProdottoMagazzino> prodottiMagazzino = prodottoMagazzinoDAO.getProdottiMagazzinoByCliente(185);
        Assert.assertEquals(false, prodottiMagazzino.isEmpty());

    }
}
