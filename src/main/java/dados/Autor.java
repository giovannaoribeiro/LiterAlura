package dados;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    private Integer dataNascimento;
    private Integer dataFalecimento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livro;

    public Autor() {}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.dataNascimento = dadosAutor.dataNascimento();
        this.dataFalecimento = dadosAutor.dataFalecimento();
    }
    @Override
    public String toString() {
        StringBuilder livros = new StringBuilder();
        livros.append("Livros: ");

        for (int i = 0; i < livro.size(); i++) {
            livros.append(livro.get(i).getTitulo());
            if (i < livro.size() -1) {
                livros.append(", ");
            }
        }

        return  String.format("------------ Autor -------------%nNome:" +
                "%s%n%s%nData de nascimento: %s%nData de falecimento:" +
                "%s%n--------------------------%n", nome, livros.toString(), dataNascimento, dataFalecimento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getDataFalecimento() {
        return dataFalecimento;
    }

    public void setDataFalecimento(Integer dataFalecimento) {
        this.dataFalecimento = dataFalecimento;
    }

    public List<Livro> getLivros() {
        return livro;
    }

    public void setLivros(List<Livro> livros) {
        this.livro = livros;
    }
}
