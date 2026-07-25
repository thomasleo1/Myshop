package DAO;

import Model.Produttore;

import java.util.ArrayList;

public interface IProduttoreDAO {

    ArrayList<Produttore> findAll();
    int add(Produttore produttore);
    int removeByName(String nome);
}
