package View.Listener;

import Business.PuntoVenditaBusiness;
import Business.SessionManager;
import Business.UtenteBusiness;
import Model.*;
import View.FinestraIniziale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrazioneListener implements ActionListener {
    private FinestraIniziale frame;

    public final static String REGISTRATI = "registrati";
    public final static String COMPLETA = "completa";
    public final static String CONTINUA = "continua";
    private JTextField username;
    private JPasswordField password;
    private JTextField nome;
    private JTextField cognome;
    private JTextField eta;
    private JTextField telefono;
    private JTextField residenza;
    private JTextField professione;
    private JTextField email;
    private JTextField salario;
    private JComboBox<PuntoVendita> puntoVenditaCbx;

    public RegistrazioneListener() {
    }

    public RegistrazioneListener(JTextField nome, JTextField cognome, JTextField eta, JTextField telefono, JTextField email, JTextField residenza, JTextField professione, JTextField username, JPasswordField password, JTextField salario, JComboBox<PuntoVendita> puntoVenditaCbx) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.telefono = telefono;
        this.email = email;
        this.residenza = residenza;
        this.professione = professione;
        this.username = username;
        this.password = password;
        this.salario = salario;
        this.puntoVenditaCbx = puntoVenditaCbx;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        String action = e.getActionCommand();

        if(REGISTRATI.equals(action)) {
            frame.mostraRegistrazione();
        } else if (COMPLETA.equals(action)) {
            String user = username.getText();
            String pwd = new String(password.getPassword());
            String name = nome.getText();
            String surname = cognome.getText();
            int age = Integer.parseInt(eta.getText());
            String telephone = telefono.getText();
            String residence = residenza.getText();
            String profession = professione.getText();
            String mail = email.getText();

            UtenteBusiness utenteBusiness = UtenteBusiness.getInstance();

            Utente utente = (Utente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
            if (utente instanceof Amministratore) {
                Float salary = Float.valueOf(salario.getText());
                if (!(user.equals("") | pwd.equals("") | name.equals("") | surname.equals("") | mail.equals("") | telephone.equals("") | residence.equals("") | profession.equals("") | salary == 0)) {
                    if (!utenteBusiness.checkUtente(user) & !utenteBusiness.checkEmail(mail)) {
                        utenteBusiness.registrazione(name, surname, age, telephone, mail, residence, profession, user, pwd, "m", salary, 0);
                        Manager manager = utenteBusiness.getManager(user);
                        PuntoVenditaBusiness puntoVenditaBusiness = new PuntoVenditaBusiness();
                        puntoVenditaBusiness.addManager(manager.getIdManager(), (PuntoVendita) puntoVenditaCbx.getSelectedItem());
                        JOptionPane.showMessageDialog(null, "Manager inserito con successo");
                    } else if (utenteBusiness.checkEmail(mail)) {
                        JOptionPane.showMessageDialog(null, "Email già in uso");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Utente già in uso");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Dati mancanti");
                }
            } else {
                if (!(user.equals("") | pwd.equals("") | name.equals("") | surname.equals("") | mail.equals("") | telephone.equals("") | residence.equals("") | profession.equals(""))) {
                    if (!utenteBusiness.checkUtente(user) & !utenteBusiness.checkEmail(mail)) {
                        utenteBusiness.registrazione(name, surname, age, telephone, mail, residence, profession, user, pwd, "c", null, ((PuntoVendita) puntoVenditaCbx.getSelectedItem()).getIdPuntoVendita());
                        frame.registrazioneOk();
                    } else if (utenteBusiness.checkEmail(mail)) {
                        JOptionPane.showMessageDialog(null, "Email già in uso");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Utente già in uso");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Dati mancanti");
                }
            }
        } else if (CONTINUA.equals(action)) {
            frame.mostraFinestraIniziale();
        }
    }

    public void setFrame(FinestraIniziale frame) {
        this.frame = frame;
    }

}
