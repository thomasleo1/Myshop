package Business.Factory;

import Model.Cliente;
import Model.Messaggio;

public class NotificaPush extends Notifica {

    @Override
    public boolean inviaNotifica(Messaggio messaggio, Cliente cliente) {
        System.out.println("Invio tramite push");
        return true;
    }
}
