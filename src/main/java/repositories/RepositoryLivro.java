package repositories;

import dados.Idioma;
import dados.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface RepositoryLivro extends JpaRepository<Livro, Long> {
    List<Livro> findByLinguagem(Idioma idioma);

    Optional<Livro> findByTitulo(String titulo);
}
