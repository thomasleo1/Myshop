package DAO;

import Model.CategoriaServizio;

import java.util.ArrayList;

public interface ICategoriaServizioDAO {

    ArrayList<CategoriaServizio> findAll();
    int add(CategoriaServizio categoriaServizio);
    int remove(String nome);
}
