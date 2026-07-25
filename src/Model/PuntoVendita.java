package Model;

import java.util.ArrayList;
import java.util.List;

public class PuntoVendita {

    private int idPuntoVendita;
    private String nome;
    private String indirizzo;
    private Manager manager;
    private Magazzino magazzino;

    public PuntoVendita() {
    }

    public PuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public PuntoVendita(String nome, String indirizzo, Magazzino magazzino) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.magazzino = magazzino;
    }

    public int getIdPuntoVendita() {
        return idPuntoVendita;
    }

    public void setIdPuntoVendita(int idPuntoVendita) {
        this.idPuntoVendita = idPuntoVendita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(int idManager) {
        this.manager = new Manager(idManager);
    }


    public Magazzino getMagazzino() {
        return this.magazzino;
    }

    public void setMagazzino(int idMagazzino) {
        this.magazzino = new Magazzino(idMagazzino);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
