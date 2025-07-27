package dados;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAutor(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") Integer dataNascimento,
        @JsonAlias("death_year") Integer dataFalecimento) {
}
