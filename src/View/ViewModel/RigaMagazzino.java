package View.ViewModel;

public class RigaMagazzino {

    private int idProdotto;
    private String nomeProdotto;
    private boolean isComposito;
    private Float prezzo;
    private int quantita;
    private String disponibilita;


    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public boolean isComposito() {
        return isComposito;
    }

    public void setComposito(boolean composito) {
        isComposito = composito;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita() {
        if (this.quantita > 0) {
            this.disponibilita = "Disponibile";
        } else {
            this.disponibilita = "Esaurito";
        }
    }
}
