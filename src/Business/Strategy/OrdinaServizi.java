package Business.Strategy;

import Model.Servizio;

import java.util.List;

public class OrdinaServizi {

    private List<Servizio> servizi;

    private IOrdinaServizi ordinaServizi;

    public OrdinaServizi(List<Servizio> servizi) {
        this.servizi = servizi;
    }

    public void setOrdinaServizi(IOrdinaServizi ordinaServizi) {
        this.ordinaServizi = ordinaServizi;
    }

    public void ordina() {
        ordinaServizi.ordina(servizi);
    }

    public List<Servizio> getServizi() {
        return servizi;
    }
}

