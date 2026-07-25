package Model;


public class Collocazione {

    private int idCollocazione;
    private int corsia;
    private int scaffale;
    private Magazzino magazzino;

    public Collocazione() {
    }

    public Collocazione(int idCollocazione,int corsia, int scaffale) {
        this.idCollocazione = idCollocazione;
        this.corsia = corsia;
        this.scaffale = scaffale;
    }

    public Collocazione(int idCollocazione,int corsia, int scaffale, Magazzino magazzino) {
        this.idCollocazione = idCollocazione;
        this.corsia = corsia;
        this.scaffale = scaffale;
        this.magazzino = magazzino;
    }

    public int getIdCollocazione() {
        return idCollocazione;
    }

    public int getCorsia() {
        return corsia;
    }

    public void setCorsia(int corsia) {
        this.corsia = corsia;
    }

    public int getScaffale() {
        return scaffale;
    }

    public void setScaffale(int scaffale) {
        this.scaffale = scaffale;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(int idMagazzino) {
        this.magazzino = new Magazzino(idMagazzino);
    }

    public void setIdCollocazione(int idcollocazione) {
        this.idCollocazione = idcollocazione;
    }

    public String toString(){
        return "Corsia: " + this.corsia + ", Scaffale: " + this.scaffale;
    }
}
