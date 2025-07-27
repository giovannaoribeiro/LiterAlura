package dados;

public enum Idioma {
    en("[en]", "Inglês"),
    es("[es]", "Espanhol"),
    fr("[fr]", "Francês"),
    pt("[pt]", "Português");

    private String idiomaGutendex;
    private String idiomaPortugues;

    Idioma(String idiomaGutendex, String idiomaPortugues) {
        this.idiomaGutendex = idiomaGutendex;
        this.idiomaPortugues = idiomaPortugues;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaGutendex.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado: " + text);
    }

    public static Idioma fromPortugues(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaPortugues.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado: " + text);
    }

    public String getIdiomaGutendex() {
        return idiomaGutendex;
    }

    public String getIdiomaPortugues() {
        return idiomaPortugues;
    }
}