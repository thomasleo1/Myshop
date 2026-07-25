package Business;


import DAO.IProduttoreDAO;
import DAO.ProduttoreDAO;
import Model.Produttore;

import java.util.ArrayList;

public class ProduttoreBusiness {

    private IProduttoreDAO produttoreDAO = ProduttoreDAO.getInstance();
    public ArrayList<Produttore> getProduttori() {

        return produttoreDAO.findAll();
    }

    public void addProduttore(Produttore produttore) {

        produttoreDAO.add(produttore);
    }


}
