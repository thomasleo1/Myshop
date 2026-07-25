package View.Listener;

import Business.*;
import Model.*;
import Model.ICategoria;
import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.Composite.ProdottoComposito;
import View.FinestraIniziale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class AmministratoreListener implements ActionListener {



    private FinestraIniziale frame;
    public static final String AGGIUNGI_PRODOTTO = "Aggiungi prodotto";
    public static final String AGGIUNGI_SERVIZIO = "Aggiungi servizio";
    public static final String AGGIUNGI_PRODOTTO_COMPOSITO = "Aggiungi prodotto composito";
    public static final String AGGIUNGI_CATEGORIA = "Aggiungi categoria";
    public static final String AGGIUNGI_PRODUTTORE = "Aggiungi produttore";
    public static final String AGGIUNGI_MANAGER = "Aggiungi manager";
    public static final String AGGIUNGI_PUNTO_VENDITA = "Aggiungi punto vendita";
    public static final String SALVA_PRODOTTO = "Salva prodotto";
    public static final String SALVA_SERVIZIO = "Salva servizio";
    public static final String SALVA_PRODOTTO_COMPOSITO = "Salva prodotto composito";
    public static final String SALVA_CATEGORIA = "Salva categoria";
    public static final String SALVA_PRODUTTORE = "Salva produttore";
    public static final String SALVA_PUNTO_VENDITA = "Salva punto vendita";
    public static final String IMMAGINE = "Immagine";
    private JTextField nome;
    private JTextField descrizione;
    private JTextField nomeCategoriaProdotto;
    private JTextField nomeCategoriaServizio;
    private JTextField nomeSottocategoria;
    private JTextField indirizzo;
    private JTextField sitoWeb;
    private JTextField citta;
    private JTextField nazione;
    private JTextField prezzo;
    private JComboBox<Produttore> produttoreCbx;
    private JComboBox<Collocazione> collocazioneCbx;
    private JComboBox<CategoriaProdotto> categoriaProdottoCbx;
    private JComboBox<CategoriaServizio> categoriaServizioCbx;
    private JComboBox<SottoCategoria> sottocategoriaCbx;
    private JComboBox<PuntoVendita> puntoVenditaCbx;
    private JComboBox<Manager> managerCbx;
    private JComboBox<Magazzino> magazzinoCbx;
    private File selectedFile;
    private JFrame imageSelectionFrame;
    private List<Prodotto> prodottiSelezionati;
    private List<IProdotto> articoliSelezionati;

    public AmministratoreListener(JTextField nome, JTextField descrizione, JTextField prezzo, JComboBox<Produttore> produttoreCbx, JComboBox<Magazzino> magazzinoCbx, JComboBox<Collocazione> collocazioneCbx, JComboBox<CategoriaProdotto> categoriaCbx, JComboBox<SottoCategoria> sottocategoriaCbx, JComboBox<PuntoVendita> puntoVenditaCbx, File selectedFile, JFrame imageSelectionFrame)
    {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.produttoreCbx = produttoreCbx;
        this.magazzinoCbx = magazzinoCbx;
        this.collocazioneCbx = collocazioneCbx;
        this.categoriaProdottoCbx = categoriaCbx;
        this.sottocategoriaCbx = sottocategoriaCbx;
        this.puntoVenditaCbx = puntoVenditaCbx;
        this.selectedFile = selectedFile;
        this.imageSelectionFrame = imageSelectionFrame;
    }

    public AmministratoreListener(JTextField nome, JTextField descrizione, JTextField prezzo, JComboBox<Produttore> produttoreCbx, JComboBox<CategoriaServizio> categoriaCbx)
    {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.produttoreCbx = produttoreCbx;
        this.categoriaServizioCbx = categoriaCbx;
    }

    public AmministratoreListener(JTextField nome, JTextField descrizione, JComboBox<Produttore> produttoreCbx, JComboBox<Collocazione> collocazioneCbx, JComboBox<CategoriaProdotto> categoriaCbx, JComboBox<PuntoVendita> puntoVenditaCbx, File selectedFile, JFrame imageSelectionFrame, List<Prodotto> prodottiSelezionati) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.produttoreCbx = produttoreCbx;
        this.collocazioneCbx = collocazioneCbx;
        this.categoriaProdottoCbx = categoriaCbx;
        this.puntoVenditaCbx = puntoVenditaCbx;
        this.selectedFile = selectedFile;
        this.imageSelectionFrame = imageSelectionFrame;
        this.prodottiSelezionati = prodottiSelezionati;
    }

    public AmministratoreListener(JTextField nomeCategoriaProdotto, JTextField nomeCategoriaServizio, JTextField nomeSottocategoria, JComboBox<CategoriaProdotto> categoriaCbx) {
        this.nomeCategoriaProdotto = nomeCategoriaProdotto;
        this.nomeCategoriaServizio = nomeCategoriaServizio;
        this.nomeSottocategoria = nomeSottocategoria;
        this.categoriaProdottoCbx = categoriaCbx;
    }

    public AmministratoreListener(JTextField nome, JTextField sitoWeb, JTextField citta, JTextField nazione) {
        this.nome = nome;
        this.sitoWeb = sitoWeb;
        this.citta = citta;
        this.nazione = nazione;
    }

    public AmministratoreListener(JTextField nome, JTextField indirizzo, JComboBox<Magazzino> magazzinoCbx, List<IProdotto> articoliSelezionati)
    {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.magazzinoCbx = magazzinoCbx;
        this.articoliSelezionati = articoliSelezionati;
    }

    public AmministratoreListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        if(AGGIUNGI_PRODOTTO.equals(action)) {
            frame.aggiungiProdotto();
        } else if (SALVA_PRODOTTO.equals(action)) {
            Path sourcePath = Paths.get(selectedFile.getAbsolutePath());
            String fileNameFromPath = sourcePath.getFileName().toString();
            String destinationPath = "C:/Users/thoma/OneDrive/Desktop/MyShop/Immagini/";
            try {
                Files.copy(sourcePath, Paths.get(destinationPath+fileNameFromPath), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Prodotto prodottoToSave = new Prodotto(nome.getText(), descrizione.getText(), Float.parseFloat(prezzo.getText()), (Produttore) produttoreCbx.getSelectedItem(), (Collocazione) collocazioneCbx.getSelectedItem(), (ICategoria) categoriaProdottoCbx.getSelectedItem(), ((ICategoria) sottocategoriaCbx.getSelectedItem()),null, destinationPath+fileNameFromPath);
            ProdottoBusiness prodottoBusiness = new ProdottoBusiness();
            prodottoBusiness.addProdotto(prodottoToSave,((SottoCategoria) sottocategoriaCbx.getSelectedItem()).getIdSottoCategoria());

            Prodotto prodotto = prodottoBusiness.getProdottoByName(nome.getText());
            PuntoVenditaBusiness puntoVenditaBusiness = new PuntoVenditaBusiness();
            puntoVenditaBusiness.associaArticolo(prodotto, ((PuntoVendita) puntoVenditaCbx.getSelectedItem()).getIdPuntoVendita(), ((PuntoVendita) puntoVenditaCbx.getSelectedItem()).getMagazzino().getIdMagazzino());

            JOptionPane.showMessageDialog(null, "Prodotto salvato correttamente");
        } else if (IMMAGINE.equals(action)) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(imageSelectionFrame);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            }
        } else if (AGGIUNGI_SERVIZIO.equals(action)) {
            frame.aggiungiServizio();
        } else if (SALVA_SERVIZIO.equals(action)) {
            Servizio servizioToSave = new Servizio(nome.getText(),descrizione.getText(), Float.parseFloat(prezzo.getText()), (Produttore) produttoreCbx.getSelectedItem(), (ICategoria) categoriaServizioCbx.getSelectedItem(), null);
            ServizioBusiness servizioBusiness = new ServizioBusiness();
            servizioBusiness.addServizio(servizioToSave);
            JOptionPane.showMessageDialog(null, "Servizio salvato correttamente");
        } else if (AGGIUNGI_PRODOTTO_COMPOSITO.equals(action)) {
            frame.aggiungiProdottoComposito();
        } else if (SALVA_PRODOTTO_COMPOSITO.equals(action)) {
            Path sourcePath = Paths.get(selectedFile.getAbsolutePath());
            String fileNameFromPath = sourcePath.getFileName().toString();
            String destinationPath = "C:/Users/thoma/OneDrive/Desktop/MyShop/Immagini/";
            try {
                Files.copy(sourcePath, Paths.get(destinationPath+fileNameFromPath), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            ProdottoComposito prodottoCompToSave = new ProdottoComposito(nome.getText(), descrizione.getText(), (Produttore) produttoreCbx.getSelectedItem(), (Collocazione) collocazioneCbx.getSelectedItem(), (ICategoria) categoriaProdottoCbx.getSelectedItem(), null,  destinationPath+fileNameFromPath);
            ProdottoCompositoBusiness prodottoCompositoBusiness = new ProdottoCompositoBusiness();
            prodottoCompositoBusiness.addProdottoComposito(prodottoCompToSave);
            ProdottoComposito prodottoComposito = prodottoCompositoBusiness.getProdottoCompositoByName(prodottoCompToSave.getNome());
            for (Prodotto prodotto : prodottiSelezionati) {
                prodottoCompositoBusiness.addSottoprodotto(prodottoComposito.getId(), prodotto.getId());
            }

            PuntoVenditaBusiness puntoVenditaBusiness = new PuntoVenditaBusiness();
            puntoVenditaBusiness.associaArticolo(prodottoComposito, ((PuntoVendita) puntoVenditaCbx.getSelectedItem()).getIdPuntoVendita(), ((PuntoVendita) puntoVenditaCbx.getSelectedItem()).getMagazzino().getIdMagazzino());
            JOptionPane.showMessageDialog(null, "Prodotto Composito salvato correttamente");
        } else if (AGGIUNGI_CATEGORIA.equals(action)) {
            frame.aggiungiCategoria();
        } else if (SALVA_CATEGORIA.equals(action)) {
            CategoriaProdotto categoriaProdottoToSave = new CategoriaProdotto(nomeCategoriaProdotto.getText());
            CategoriaServizio categoriaServizioToSave = new CategoriaServizio(nomeCategoriaServizio.getText());
            CategoriaBusiness categoriaBusiness = new CategoriaBusiness();

            if (!nomeCategoriaProdotto.getText().equals("")) {
                categoriaBusiness.addCategoriaProdotto(categoriaProdottoToSave);
            } else if (!nomeCategoriaServizio.getText().equals("")) {
                categoriaBusiness.addCategoriaServizio(categoriaServizioToSave);
            } else if (!nomeSottocategoria.getText().equals("")) {
                categoriaBusiness.addSottocategoria(nomeSottocategoria.getText(),(CategoriaProdotto) categoriaProdottoCbx.getSelectedItem());
            }
            JOptionPane.showMessageDialog(null, "Categoria salvata correttamente");
        } else if (AGGIUNGI_PRODUTTORE.equals(action)) {
            frame.aggiungiProduttore();
        } else if (SALVA_PRODUTTORE.equals(action)) {
            Produttore produttoreToSave = new Produttore(nome.getText(), sitoWeb.getText(), citta.getText(), nazione.getText());
            ProduttoreBusiness produttoreBusiness = new ProduttoreBusiness();
            produttoreBusiness.addProduttore(produttoreToSave);
            JOptionPane.showMessageDialog(null, "Produttore salvato correttamente");
        } else if (AGGIUNGI_MANAGER.equals(action)) {
            frame.mostraRegistrazione();
        } else if (AGGIUNGI_PUNTO_VENDITA.equals(action)) {
            frame.aggiungiPuntoVendita();
        } else if (SALVA_PUNTO_VENDITA.equals(action)) {
            PuntoVendita puntoVenditaToSave = new PuntoVendita(nome.getText(), indirizzo.getText(), ((Magazzino) magazzinoCbx.getSelectedItem()));
            PuntoVenditaBusiness puntoVenditaBusiness = new PuntoVenditaBusiness();
            puntoVenditaBusiness.addPuntoVendita(puntoVenditaToSave);
            for (IProdotto articolo : articoliSelezionati) {
                puntoVenditaBusiness.associaArticolo(articolo, puntoVenditaBusiness.getPuntoVenditaByName(puntoVenditaToSave.getNome()).getIdPuntoVendita(), ((PuntoVendita) puntoVenditaCbx.getSelectedItem()).getMagazzino().getIdMagazzino());
            }
            JOptionPane.showMessageDialog(null, "Punto Vendita salvato correttamente");

        }
    }

    public void setFrame(FinestraIniziale frame) {
        this.frame = frame;
    }
}
