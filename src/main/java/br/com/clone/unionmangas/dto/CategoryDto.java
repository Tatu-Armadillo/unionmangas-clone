package br.com.clone.unionmangas.dto;

import java.util.*;
import java.util.stream.Collectors;

import br.com.clone.unionmangas.model.Category;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CategoryDto implements Comparable<CategoryDto> {

    private Long idCategory;
    private String name;

    public CategoryDto() { }

    public CategoryDto(Category category) {
        this.idCategory = category.getIdCategory();
        this.name = category.getName();
    }

    public static List<CategoryDto> categorySetToCategoryDtoList(Set<Category> categories) {
        final var categoriesDto = categories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
        Collections.sort(categoriesDto);
        return categoriesDto;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(CategoryDto o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

}
