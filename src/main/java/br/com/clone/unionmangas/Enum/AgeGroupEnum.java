package br.com.clone.unionmangas.Enum;

public enum AgeGroupEnum {

    LIVRE(0L, "LIVRE", "Livre para todos os publicos"),
    MAIOR_10(10L, "MAIOR_10", "Não recomendado para menores de 10 anos"),
    MAIOR_12(12L, "MAIOR_12", "Não recomendado para menores de 12 anos"),
    MAIOR_14(12L, "MAIOR_14", "Não recomendado para menores de 14 anos"),
    MAIOR_16(12L, "MAIOR_16", "Não recomendado para menores de 16 anos"),
    MAIOR_18(12L, "MAIOR_18", "Não recomendado para menores de 18 anos");

    private Long age;
    private String chave;
    private String description;

    AgeGroupEnum(Long age, String chave, String description) {
        this.age = age;
        this.chave = chave;
        this.description = description;
    }

    public Long getAge() {
        return age;
    }

    public String getChave() {
        return chave;
    }

    public String getDescription() {
        return description;
    }

}
