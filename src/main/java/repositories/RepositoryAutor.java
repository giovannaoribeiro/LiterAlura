package repositories;

import dados.Autor;
import dados.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RepositoryAutor extends JpaRepository<Autor, Long> {
    List<Autor> findByLinguagem(Idioma idioma);

    Optional<Autor> findByNome(String nome);

    @Query("SELECT A FROM Autor A WHERE a.dataNascimento <= : ano AND a.dataFalecimento >= : ano")
    List<Autor> listarAutorVivo(Integer ano);
}
