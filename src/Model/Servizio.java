package Model;



public class Servizio {

    private int idServizio;
    private String nome;
    private String descrizione;
    private Float prezzo;
    private Produttore produttore;
    private ICategoria categoria;
    private Recensione recensione;

    public Servizio() {
    }

    public Servizio(String nome, String descrizione, Float prezzo, Produttore produttore, ICategoria categoria, Recensione recensione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.produttore = produttore;
        this.categoria = categoria;
        this.recensione = recensione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(int idProduttore, String nome, String sitoWeb, String citta, String nazione) {
        this.produttore = new Produttore(idProduttore, nome, sitoWeb, citta, nazione);
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public int getId() {
        return idServizio;
    }

    public void setIdServizio(int idServizio) {
        this.idServizio = idServizio;
    }

    public Recensione getRecensione() {
        return recensione;
    }

    public void setRecensione(int idRecensione, String testo, Recensione.Feedback feedback) {
        this.recensione = new Recensione(idRecensione, testo, feedback);
    }

    public ICategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(int idCategoria, String nome) {
        this.categoria = new CategoriaServizio(idCategoria, nome);
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
