package Model;

public class Manager extends Utente {

    private Float salario;
    private int idManager;

    public Manager() {
    }

    public Manager(String nome, String cognome, int eta, String telefono, String email, String residenza, String professione, String username, String password,  String tipo, Stato stato, Float salario) {
        super(nome, cognome, eta, telefono, email, residenza, professione, username, password, tipo, stato);
        this.salario = salario;
    }

    public Manager(int idManager) {
        this.idManager = idManager;
    }

    public int getIdManager() {
        return idManager;
    }

    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.getNome();
    }
}
