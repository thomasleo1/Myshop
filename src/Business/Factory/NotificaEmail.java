package Business.Factory;

import Business.Bridge.EmailSender;
import Model.Cliente;
import Model.Messaggio;

public class NotificaEmail extends Notifica {

    @Override
    public boolean inviaNotifica(Messaggio messaggio, Cliente cliente) {
        EmailSender emailSender = new EmailSender();
        emailSender.sendCustomMessage(cliente.getEmail(), messaggio.getOggetto(), messaggio.getTesto());
        return true;
    }
}
