package Business.Factory;

import Model.Cliente;
import Model.Messaggio;

public abstract class Notifica {

    private Messaggio messaggio;
    private Cliente cliente;

    public Messaggio getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(Messaggio messaggio) {
        this.messaggio = messaggio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public abstract boolean inviaNotifica(Messaggio messaggio, Cliente cliente);
}