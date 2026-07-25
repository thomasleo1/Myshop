package Business;

import DAO.IServizioDAO;
import DAO.ServizioDAO;
import Model.Servizio;

import java.util.ArrayList;
import java.util.List;

public class ServizioBusiness {

    private IServizioDAO servizioDAO = ServizioDAO.getInstance();

    public ArrayList<Servizio> getServizi() {

        return servizioDAO.findAll();
    }

    public Servizio getServizio(String nome) {

        return servizioDAO.findByName(nome);
    }

    public void addServizio(Servizio servizio) {

        servizioDAO.add(servizio);
    }

    public void removeServizio(int id) {

        servizioDAO.removeById(id);
    }

    public Servizio getServizioById(int idServizio) {

        return servizioDAO.findById(idServizio);
    }

    public int updateServizio(Servizio servizio) {

        return servizioDAO.update(servizio);
    }

    public List<Servizio> getServiziRecensitiByIdCliente(int idCliente) {

        return servizioDAO.getServiziRecensitiByIdCliente(idCliente);
    }

    public List<Servizio> getServiziRecensitiByIdManager(int idManager) {

        return servizioDAO.getServiziRecensitiByIdManager(idManager);
    }
}
