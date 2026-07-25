package Model;

import Model.Composite.IProdotto;

import java.util.ArrayList;
import java.util.List;

public class Magazzino {

    private int idMagazzino;
    private String nome;
    private List<ProdottoMagazzino> articoli;

    public Magazzino() {
    }

    public Magazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public Magazzino(String nome) {
        this.nome = nome;
    }

    public Magazzino(int idMagazzino, String nome, List<ProdottoMagazzino> articoli) {
        this.idMagazzino = idMagazzino;
        this.nome = nome;
        this.articoli = articoli;
    }

    public int getIdMagazzino() {
        return idMagazzino;
    }

    public List<ProdottoMagazzino> getArticolo() {
        return articoli;
    }

    public void setArticolo(List<ProdottoMagazzino> articoli) {
        this.articoli = articoli;
    }

    public void setIdMagazzino(int idMagazzino) {
        this.idMagazzino = idMagazzino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
