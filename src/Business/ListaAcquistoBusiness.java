package Business;

import DAO.*;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import Model.ListaAcquisto;
import Model.ProdottoMagazzino;
import Model.Servizio;

import java.util.ArrayList;

public class ListaAcquistoBusiness {

    private IListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
    private IProdottoMagazzinoDAO prodottoMagazzinoDAO = ProdottoMagazzinoDAO.getInstance();
    private IProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
    private IServizioDAO servizioDAO = ServizioDAO.getInstance();
    public ListaAcquisto getListaById(int idListaAcquisto) {

        ListaAcquisto listaAcquisto = listaAcquistoDAO.findListaById(idListaAcquisto) ;
        Float prezzo = 0F;
        ArrayList<ProdottoMagazzino> prodotti = prodottoMagazzinoDAO.getProdottiByListaId(listaAcquisto.getIdListaAcquisto());
        for (ProdottoMagazzino prodotto : prodotti) {
            listaAcquisto.addArticolo(prodotto);
            prezzo += prodotto.getProdotto().getPrezzo() * prodotto.getQuantita();
            if (prodotto.getProdotto() instanceof ProdottoComposito) {
                ArrayList<Prodotto> sottoprodotti = prodottoDAO.findByProdottoCompositoID(prodotto.getProdotto().getId());
                Float prezzoProdottoComposito = 0F;
                for (Prodotto sottoprodotto : sottoprodotti) {
                    ((ProdottoComposito) prodotto.getProdotto()).add(sottoprodotto);
                    prezzoProdottoComposito += sottoprodotto.getPrezzo();
                }
                prodotto.getProdotto().setPrezzo(prezzoProdottoComposito);
                prezzo += prodotto.getProdotto().getPrezzo() * prodotto.getQuantita();
            }
        }

        ArrayList<Servizio> servizi = servizioDAO.getServiziByListaId(listaAcquisto.getIdListaAcquisto());
        Float prezzoServizi = 0F;
        for (Servizio servizio: servizi) {
            listaAcquisto.addServizo(servizio);
            prezzoServizi += servizio.getPrezzo();
        }

        listaAcquisto.setPrezzo(prezzo + prezzoServizi);
        return listaAcquisto;
    }

    public void addLista(ListaAcquisto listaAcquisto, int idCliente) {

        listaAcquistoDAO.add(listaAcquisto);
        ListaAcquisto listaAcquistoCreata = listaAcquistoDAO.findByName(listaAcquisto.getNome());
        listaAcquistoDAO.creaAssociazioneCliente(idCliente, listaAcquistoCreata.getIdListaAcquisto());
    }

    public void update(ListaAcquisto listaAcquisto) {
        ListaAcquistoDAO listaAcquistoDAO = ListaAcquistoDAO.getInstance();
        listaAcquistoDAO.update(listaAcquisto);
    }

    public ArrayList<ListaAcquisto> getListeByIdCliente(int idUtente) {

        ArrayList<ListaAcquisto> liste = listaAcquistoDAO.findByIdCliente(idUtente);

        for (ListaAcquisto listaAcquisto : liste) {
            Float prezzo = 0F;
            ArrayList<ProdottoMagazzino> prodotti = prodottoMagazzinoDAO.getProdottiByListaId(listaAcquisto.getIdListaAcquisto());
            for (ProdottoMagazzino prodotto : prodotti) {
                listaAcquisto.addArticolo(prodotto);
                prezzo += prodotto.getProdotto().getPrezzo() * prodotto.getQuantita();
                if (prodotto.getProdotto() instanceof ProdottoComposito) {
                    ArrayList<Prodotto> sottoprodotti = prodottoDAO.findByProdottoCompositoID(prodotto.getProdotto().getId());
                    Float prezzoProdottoComposito = 0F;
                    for (Prodotto sottoprodotto : sottoprodotti) {
                        ((ProdottoComposito) prodotto.getProdotto()).add(sottoprodotto);
                        prezzoProdottoComposito += sottoprodotto.getPrezzo();
                    }
                    prodotto.getProdotto().setPrezzo(prezzoProdottoComposito);
                    prezzo += prodotto.getProdotto().getPrezzo() * prodotto.getQuantita();
                }
            }
            ArrayList<Servizio> servizi = servizioDAO.getServiziByListaId(listaAcquisto.getIdListaAcquisto());
            Float prezzoServizi = 0F;
            for (Servizio servizio: servizi) {
                listaAcquisto.addServizo(servizio);
                prezzoServizi += servizio.getPrezzo();
            }
            listaAcquisto.setPrezzo(prezzo + prezzoServizi);
        }
        return liste;
    }

    public int addProdottoToList(ListaAcquisto listaAcquisto, int quantita, int idProdotto) {

        return listaAcquistoDAO.addProdottiToList(listaAcquisto, quantita, idProdotto);
    }

    public int addServizioToList(int idServizio, int idListaAcquisto) {

        return listaAcquistoDAO.addServizioToList(idListaAcquisto, idServizio);

    }

    public ListaAcquisto getProdottiAcquistatiByIdUtente(int idCliente) {

        return listaAcquistoDAO.getProdottiAcquistatiByIdUtente(idCliente);
    }
}
