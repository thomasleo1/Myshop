package Model;


public class Messaggio {

    private String testo;
    private String oggetto;

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public Messaggio(String oggetto, String testo) {
        this.testo = testo;
        this.oggetto = oggetto;
    }
}
