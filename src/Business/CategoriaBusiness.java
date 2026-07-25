package Business;

import DAO.*;
import Model.CategoriaProdotto;
import Model.CategoriaServizio;
import Model.SottoCategoria;

import java.util.ArrayList;

public class CategoriaBusiness {

    private ICategoriaProdottoDAO categoriaProdottoDAO = CategoriaProdottoDAO.getInstance();
    private ICategoriaServizioDAO categoriaServizioDAO = CategoriaServizioDAO.getInstance();
    private ISottocategoriaDAO sottocategoriaDAO = SottocategoriaDAO.getInstance();

    public ArrayList<CategoriaProdotto> getCategorieProdotto() {

        return categoriaProdottoDAO.findAll();

    }

    public ArrayList<CategoriaServizio> getCategorieServizio() {

        return categoriaServizioDAO.findAll();

    }

    public ArrayList<SottoCategoria> getSottocategorie(int idCategoria) {

        return sottocategoriaDAO.findAllSottocategorie(idCategoria);

    }

    public void addCategoriaProdotto(CategoriaProdotto categoriaProdotto) {

        categoriaProdottoDAO.add(categoriaProdotto);

    }

    public void addCategoriaServizio(CategoriaServizio categoriaServizio) {

        categoriaServizioDAO.add(categoriaServizio);

    }

    public void addSottocategoria(String nome, CategoriaProdotto categoriaProdotto) {

        sottocategoriaDAO.addSottocategoria(nome, categoriaProdotto);

    }

}
