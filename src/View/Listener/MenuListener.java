package View.Listener;

import Business.Strategy.OrdinaProdottiPerNome;
import Business.Strategy.OrdinaProdottiPerPrezzo;
import Business.Strategy.OrdinaServiziPerNome;
import Business.Strategy.OrdinaServiziPerPrezzo;
import View.CatalogoPanel;
import View.CatalogoTableModel;
import View.ServizioTableModel;
import View.ViewModel.RigaCatalogo;
import View.ViewModel.RigaServizio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuListener extends JFrame implements ActionListener {

    public final static String SERVIZIO = "servizio";
    public final static String PRODOTTO = "prodotto";
    public final static String PREZZO = "prezzo";
    public final static String NOME = "nome";
    private CatalogoPanel panel;
    private CatalogoTableModel tableModel;
    private ServizioTableModel tableServizio;
    private List<RigaCatalogo> righe = new ArrayList<>();
    List<RigaServizio> righeServizi = new ArrayList<>();
    List<RigaServizio> righeFiltrateServizio  = new ArrayList<>();
    List<RigaCatalogo> righeFiltrate = new ArrayList<>();
    private boolean isFirstTime = true;
    private boolean isService;


    public MenuListener(CatalogoPanel panel, CatalogoTableModel tableModel, ServizioTableModel tableServizio, boolean isService) {
        this.panel = panel;
        this.tableModel = tableModel;
        this.tableServizio = tableServizio;
        this.isService = isService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (isFirstTime) {
            righe = tableModel.getRighe();
            righeServizi = tableServizio.getRighe();
            isFirstTime = false;
        }

        if (!(SERVIZIO.equals(action) | PRODOTTO.equals(action) | PREZZO.equals(action) | NOME.equals(action))) {
            righeFiltrate = new ArrayList<>();
            for (RigaCatalogo riga : righe) {
                if (riga.getNomeCategoria().equals(action)) {
                    righeFiltrate.add(riga);
                }
            }
            if (righeFiltrate.size() > 0) {
                panel.aggiornaTabellaProdotti(righeFiltrate, tableModel);
            } else {
                righeFiltrateServizio = new ArrayList<>();
                for (RigaServizio riga : righeServizi) {
                    if (riga.getNomeCategoria().equals(action)) {
                        righeFiltrateServizio.add(riga);
                    }
                    panel.aggiornaTabellaServizi(righeFiltrateServizio, tableServizio);
                }
            }
        }

        if (SERVIZIO.equals(action)) {
            panel.aggiornaTabellaProdotti(righe, tableModel);
            panel.scrollPaneVisibility(false, true);
        } else if (PRODOTTO.equals(action)) {
            panel.aggiornaTabellaServizi(righeServizi, tableServizio);
            panel.scrollPaneVisibility(true, true);
        }

        if (PREZZO.equals(action)) {
            if (!isService) {
                if(righeFiltrate.size() > 0) {
                    panel.ordinaProdotti(righeFiltrate, tableModel, new OrdinaProdottiPerPrezzo());
                }
                else {
                    panel.ordinaProdotti(righe, tableModel, new OrdinaProdottiPerPrezzo());
                }
            }
            else {
                if(righeFiltrateServizio.size() > 0) {
                    panel.ordinaServizi(righeFiltrateServizio, tableServizio, new OrdinaServiziPerPrezzo());
                }
                else {
                    panel.ordinaServizi(righeServizi, tableServizio, new OrdinaServiziPerPrezzo());
                }
            }
        }
        else if (NOME.equals(action)) {
            if (!isService) {
                if(righeFiltrate.size() > 0) {
                    panel.ordinaProdotti(righeFiltrate, tableModel, new OrdinaProdottiPerNome());
                }
                else {
                    panel.ordinaProdotti(righe, tableModel, new OrdinaProdottiPerNome());
                }
            }
            else {
                if(righeFiltrateServizio.size() > 0) {
                    panel.ordinaServizi(righeFiltrateServizio, tableServizio, new OrdinaServiziPerNome());
                }
                else {
                    panel.ordinaServizi(righeServizi, tableServizio, new OrdinaServiziPerNome());
                }
            }
        }


    }

}