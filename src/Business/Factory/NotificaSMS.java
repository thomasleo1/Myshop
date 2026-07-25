package Business.Factory;

import Model.Cliente;
import Model.Messaggio;

public class NotificaSMS extends Notifica {

    @Override
    public boolean inviaNotifica(Messaggio messaggio, Cliente cliente) {
        System.out.println("Invio tramite SMS");
        return true;
    }
}
