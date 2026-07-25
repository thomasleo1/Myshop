package View.ViewModel;

import Model.Recensione;

public class RigaFeedback {

    private int idRecensione;
    private String nomeProdotto;
    private String emailCliente;
    private Recensione.Feedback feedback;
    private String commento;
    private String risposta;
    private boolean visualizzato;

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public Recensione.Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Recensione.Feedback feedback) {
        this.feedback = feedback;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public boolean isVisualizzato() {
        return visualizzato;
    }

    public void setVisualizzato(boolean visualizzato) {
        this.visualizzato = visualizzato;
    }
}
