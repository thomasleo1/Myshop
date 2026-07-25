package Business.Bridge;

import Model.Composite.IProdotto;
import Model.Composite.Prodotto;
import Model.ListaAcquisto;
import Model.ProdottoMagazzino;
import Model.Servizio;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DocumentoListaAcquisto extends Documento {

    private ListaAcquisto lista;

    public DocumentoListaAcquisto(ListaAcquisto lista, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;

    }

    @Override
    public void invia(String indirizzo) {

        String text = "Fattura dei prodotti acquistati \n";
        List<ProdottoMagazzino> prodotti = this.lista.getArticoli();
        List<Servizio> servizi = this.lista.getServizi();
        for (ProdottoMagazzino prodottoMagazzino : prodotti) {
            text += "Nome prodotto: " + prodottoMagazzino.getProdotto().getNome() +
                    "; Quantita: " + prodottoMagazzino.getQuantita() +
                    "; Prezzo: " + prodottoMagazzino.getProdotto().getPrezzo() + " euro\n" ;

        }

        for (Servizio servizio : servizi) {
            text += "Nome prodotto: " + servizio.getNome() +
                    "; Prezzo: " + servizio.getPrezzo() + " euro\n";
        }

        text += "Prezzo Totale: " + lista.getPrezzo() + " euro";



        try {
            File tempFile = File.createTempFile("myshop", ".pdf");
            EmailSender sender = new EmailSender();
            pdfAPI.creaPdf(text, tempFile.getAbsolutePath());
            sender.send(indirizzo, tempFile.getAbsolutePath(), lista.getNome());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
