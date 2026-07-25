package DAO;

import Model.CategoriaProdotto;
import Model.SottoCategoria;

import java.util.ArrayList;

public interface ISottocategoriaDAO {

    ArrayList<SottoCategoria> findAllSottocategorie(int idCategoria);
    int addSottocategoria(String nome, CategoriaProdotto categoriaProdotto);
    int removeByName(String nome);

}
