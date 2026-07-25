package View.Listener;

import Business.LoginResult;
import Business.UtenteBusiness;
import View.FinestraIniziale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    private JTextField username;
    private JPasswordField password;
    private FinestraIniziale frame;

    public LoginListener(JTextField username, JPasswordField password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String user = username.getText();
        String pwd = new String(password.getPassword());

        LoginResult result = UtenteBusiness.getInstance().login(user, pwd);

        if(result.getResult() == LoginResult.Result.LOGIN_OK) {
            frame.pannelloCliente(result.getMessage());
            frame.nuoviPulsanti();
            }
        else {
            JOptionPane.showMessageDialog(null, result.getMessage());
        }
    }

    public void setFrame(FinestraIniziale frame) {
        this.frame = frame;
    }
}
