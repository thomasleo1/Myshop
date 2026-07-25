package Model;

import Model.Composite.IProdotto;

import java.util.Date;

public class Recensione {

    public enum Feedback {SCARSO, MEDIOCRE, DISCRETO, BUONO, OTTIMO}

    private int idRecensione;
    private String testo;
    private Cliente cliente;
    private Date data;
    private Feedback feedback;
    private Boolean visualizzato;
    private String risposta;

    public Recensione() {
    }

    public Recensione(int idRecensione,String testo, Cliente cliente, Boolean visualizzato, Date data, Feedback feedback, String risposta) {
        this.idRecensione = idRecensione;
        this.testo = testo;
        this.cliente = cliente;
        this.visualizzato = visualizzato;
        this.data = data;
        this.feedback = feedback;
        this.risposta = risposta;
    }

    public Recensione(int idRecensione, String testo, Feedback feedback) {
        this.idRecensione = idRecensione;
        this.testo = testo;
        this.feedback = feedback;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }


    public Boolean getVisualizzato() {
        return visualizzato;
    }

    public void setVisualizzato(Boolean visualizzato) {
        this.visualizzato = visualizzato;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(int idCliente) {
        this.cliente = new Cliente(idCliente);
    }

    public Feedback getFeedback() {
        return this.feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }
}
