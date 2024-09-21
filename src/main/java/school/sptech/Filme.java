package school.sptech;

public class Filme {

    private Integer id;
    private String nome;
    private Integer ano;
    private String genero;
    private String diretor;

    public Filme() {
    }

    public Filme(Integer id, String nome, Integer ano, String genero, String diretor) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.genero = genero;
        this.diretor = diretor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public String toString() {
        return this.nome + " (" + this.ano + ") - " + this.genero + " - " + this.diretor;
    }
}
