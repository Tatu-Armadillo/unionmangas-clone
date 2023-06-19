package br.com.clone.unionmangas.model.enumerator;

public enum AgeGroupEnum {

    LIVRE(0, "LIVRE", "Livre para todos os publicos"),
    MAIOR_10(10, "MAIOR_10", "Não recomendado para menores de 10 anos"),
    MAIOR_12(12, "MAIOR_12", "Não recomendado para menores de 12 anos"),
    MAIOR_14(14, "MAIOR_14", "Não recomendado para menores de 14 anos"),
    MAIOR_16(16, "MAIOR_16", "Não recomendado para menores de 16 anos"),
    MAIOR_18(18, "MAIOR_18", "Não recomendado para menores de 18 anos");

    private Integer age;
    private String chave;
    private String description;

    AgeGroupEnum(Integer age, String chave, String description) {
        this.age = age;
        this.chave = chave;
        this.description = description;
    }

    public Integer getAge() {
        return age;
    }

    public String getChave() {
        return chave;
    }

    public String getDescription() {
        return description;
    }

}
