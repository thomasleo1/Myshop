package Model;


public class Produttore {

    private int idProduttore;
    private String nome;
    private String sitoWeb;
    private String citta;
    private String nazione;

    public Produttore() {
        this.nome = "";
        this.sitoWeb = "";
        this.citta = "";
        this.nazione = "";
    }

    public Produttore(int id, String nome, String sitoWeb, String citta, String nazione) {
        this.idProduttore = id;
        this.nome = nome;
        this.sitoWeb = sitoWeb;
        this.citta = citta;
        this.nazione = nazione;
    }

    public Produttore(String nome, String sitoWeb, String citta, String nazione) {
        this.nome = nome;
        this.sitoWeb = sitoWeb;
        this.citta = citta;
        this.nazione = nazione;
    }

    public int getIdProduttore() {
        return idProduttore;
    }

    public void setIdProduttore(int idProduttore) {
        this.idProduttore = idProduttore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSitoWeb() {
        return sitoWeb;
    }

    public void setSitoWeb(String sitoWeb) {
        this.sitoWeb = sitoWeb;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
