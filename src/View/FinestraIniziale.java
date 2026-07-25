package View;

import Business.SessionManager;
import Model.Amministratore;
import Model.Cliente;
import Model.Manager;
import Model.Utente;
import View.Decorator.*;
import View.Decorator.Menu;
import View.Listener.RegistrazioneListener;

import javax.swing.*;
import java.awt.*;


public class FinestraIniziale extends JFrame {

    private final JPanel utenteLoggato = new JPanel();
    private final JPanel nord = new JPanel();
    private final JPanel centro = new JPanel();
    private final JPanel ovest = new JPanel();
    private JPanel est = new JPanel();
    private JPanel sud = new JPanel();
    private int idPuntoVendita;


    public FinestraIniziale() {

        super("MyShop");

        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JLabel benvenuto = new JLabel("Benvenuto in MyShop");
        benvenuto.setFont(new Font("Arial", Font.PLAIN, 50));
        nord.add(benvenuto);

        nord.setLayout(new FlowLayout());
        centro.setLayout(new GridLayout(1,2));

        LoginPanel loginPanel = new LoginPanel(this);
        centro.add(loginPanel);

        c.add(nord, BorderLayout.NORTH);
        c.add(centro, BorderLayout.CENTER);
        c.add(est, BorderLayout.EAST);


        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void pannelloCliente(String message) {

        remove(centro);

        utenteLoggato.removeAll();
        utenteLoggato.setLayout(new FlowLayout());
        utenteLoggato.add(new JLabel(message));
        add(utenteLoggato, BorderLayout.CENTER);

        repaint();
        validate();
    }

    public void nuoviPulsanti() {

        ovest.removeAll();
        est.removeAll();

        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        if (utente instanceof Cliente) {
            View.Decorator.Menu guestMenu = new GuestMenu(this);
            Menu clienteMenu = new ClienteMenuDecorator(guestMenu,this);
            ovest.setLayout(new GridLayout(10, 1));
            for (JButton btn: clienteMenu.getPulsanti()) {
                ovest.add(btn);
            }
            getContentPane().add(ovest, BorderLayout.WEST);

        }
        else if(utente instanceof Amministratore) {
            View.Decorator.Menu guestMenu = new GuestMenu(this);
            Menu amministratoreMenu = new AmministratoreMenuDecorator(guestMenu, this);
            ovest.setLayout(new GridLayout(10, 1));
            for (JButton btn: amministratoreMenu.getPulsanti()) {
                ovest.add(btn);
            }
            getContentPane().add(ovest, BorderLayout.WEST);
        }
        else if(utente instanceof Manager) {
            View.Decorator.Menu guestMenu = new GuestMenu(this);
            Menu managerMenu = new ManagerMenuDecorator(guestMenu, this);
            ovest.setLayout(new GridLayout(10, 1));
            for (JButton btn: managerMenu.getPulsanti()) {
                ovest.add(btn);
            }

            getContentPane().add(ovest, BorderLayout.WEST);
        }

        repaint();
        validate();
    }

    public void mostraCatalogo(int idPuntoVendita) {

        this.idPuntoVendita = idPuntoVendita;
        centro.removeAll();
        utenteLoggato.removeAll();
        utenteLoggato.setLayout(new GridLayout(1,2));
        centro.add(new CatalogoPanel(this, idPuntoVendita));
        utenteLoggato.add(new CatalogoPanel(this, idPuntoVendita));

        repaint();
        validate();
    }

    public void mostraFinestraIniziale() {
        FinestraIniziale nuovaFinestra = new FinestraIniziale();
        nuovaFinestra.setVisible(true);
        dispose();
    }

    public void mostraLogin() {

        est.removeAll();
        LoginPanel loginPanel = new LoginPanel(this);
        loginPanel.setCatalogoVisibile(false);
        est.add(loginPanel);

        repaint();
        validate();
    }

    public void mostraRegistrazione() {

        centro.removeAll();
        utenteLoggato.removeAll();
        est.removeAll();
        RegistrazionePanel registrazionePanel = new RegistrazionePanel(this);
        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        if (utente instanceof Amministratore) {
            utenteLoggato.add(registrazionePanel);
        } else {
            ovest.removeAll();
            centro.add(registrazionePanel);
        }

        repaint();
        validate();
    }

    public void registrazioneOk() {
        centro.removeAll();
        JLabel label = new JLabel("Registrazione effettuata con successo!");
        centro.setLayout(new FlowLayout());
        JButton button = new JButton("Continua");
        RegistrazioneListener listener = new RegistrazioneListener();
        listener.setFrame(this);
        button.addActionListener(listener);
        button.setActionCommand(RegistrazioneListener.CONTINUA);
        centro.add(label);
        centro.add(button);

        repaint();
        validate();

    }


    public void aggiungiProdotto() {

        utenteLoggato.removeAll();
        NuovoProdottoPanel nuovoProdotto = new NuovoProdottoPanel(this);
        utenteLoggato.add(nuovoProdotto);

        repaint();
        validate();
    }

    public void aggiungiServizio() {
        utenteLoggato.removeAll();
        NuovoServizioPanel nuovoServizio = new NuovoServizioPanel(this);
        utenteLoggato.add(nuovoServizio);

        repaint();
        validate();
    }

    public void aggiungiProdottoComposito() {
        utenteLoggato.removeAll();
        NuovoProdottoCompositoPanel nuovoProdottoComposito = new NuovoProdottoCompositoPanel(this);
        utenteLoggato.add(nuovoProdottoComposito);

        repaint();
        validate();
    }

    public void aggiungiCategoria() {
        utenteLoggato.removeAll();
        NuovaCategoriaPanel nuovaCategoriaPanel = new NuovaCategoriaPanel(this);
        utenteLoggato.add(nuovaCategoriaPanel);

        repaint();
        validate();
    }

    public void aggiungiProduttore() {
        utenteLoggato.removeAll();
        NuovoProduttorePanel nuovaCategoriaPanel = new NuovoProduttorePanel(this);
        utenteLoggato.add(nuovaCategoriaPanel);

        repaint();
        validate();
    }

    public void aggiungiPuntoVendita() {
        utenteLoggato.removeAll();
        NuovoPuntoVenditaPanel nuovoPuntoVenditaPanel = new NuovoPuntoVenditaPanel(this);
        utenteLoggato.add(nuovoPuntoVenditaPanel);

        repaint();
        validate();
    }

    public void mostraListe(int idCliente) {
        utenteLoggato.removeAll();
        ListaAcquistoPanel listaPanel = new ListaAcquistoPanel(this, idCliente);
        utenteLoggato.add(listaPanel);

        repaint();
        validate();
    }

    public void mostraGestioneMagazzino() {
        utenteLoggato.removeAll();
        GestioneMagazzinoPanel gestioneMagazzinoPanel = new GestioneMagazzinoPanel(this);
        utenteLoggato.add(gestioneMagazzinoPanel);

        repaint();
        validate();
    }

    public void mostraRecensionePanel() {
        utenteLoggato.removeAll();
        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        RecensionePanel recensionePanel = new RecensionePanel(this, utente.getIdUtente());
        utenteLoggato.add(recensionePanel);

        repaint();
        validate();
    }

    public void mostraGestioneUtenti() {
        utenteLoggato.removeAll();
        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        GestisciUtentiPanel gesticiUtentiPanel = new GestisciUtentiPanel(this, utente.getIdUtente());
        utenteLoggato.add(gesticiUtentiPanel);

        repaint();
        validate();
    }

    public void mostraRecensioniFatte() {
        utenteLoggato.removeAll();
        Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        VediFeedbackPanel vediFeedbackPanel = new VediFeedbackPanel(this, utente);
        utenteLoggato.add(vediFeedbackPanel);

        repaint();
        validate();
    }

    public void mostraOrdini() {
        utenteLoggato.removeAll();
        OrdinePanel panel = new OrdinePanel(this);
        utenteLoggato.add(panel);

        repaint();
        validate();
    }
}
