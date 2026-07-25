package DAO;

import Model.CategoriaProdotto;

import java.util.ArrayList;

public interface ICategoriaProdottoDAO {

    ArrayList<CategoriaProdotto> findAll();
    int add(CategoriaProdotto categoriaProdotto);
    int removeByName(String nome);
}
