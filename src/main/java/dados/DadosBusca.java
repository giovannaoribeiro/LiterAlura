package dados;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosBusca(
        @JsonAlias("count") Integer count,
        @JsonAlias("results") List<DadosLivros> results) {
}
