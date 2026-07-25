package Model;

public class CategoriaServizio implements ICategoria {

    private int idCategoriaServizio;
    private String nome;

    public CategoriaServizio(String nome) {
        this.nome = nome;
    }

    public CategoriaServizio() {
    }

    public CategoriaServizio(int idCategoriaServizio, String nome) {
        this.idCategoriaServizio = idCategoriaServizio;
        this.nome = nome;
    }

    public int getId() {
        return idCategoriaServizio;
    }

    public void setIdCategoriaServizio(int idCategoriaServizio) {
        this.idCategoriaServizio = idCategoriaServizio;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return this.nome;
    }


}
