package Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dados.DadosBusca;

public class ConversorDados {
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao converter JSON: " + e.getMessage());
            return null;
        }
    }
}
