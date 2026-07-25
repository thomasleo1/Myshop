package DAO;

import Model.Magazzino;

import java.util.ArrayList;

public interface IMagazzinoDAO {

    ArrayList<Magazzino> findAll();
    int getIdMagazzinoByIdProdottoComposito(int id);
    int getIdMagazzinoByIdProdotto(int id);
    int add(Magazzino magazzino);
    int removeByName(String nome);
}
