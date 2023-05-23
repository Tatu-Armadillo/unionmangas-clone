package br.com.clone.unionmangas.dto.manga;

import java.util.List;

import br.com.clone.unionmangas.dto.CategoryDto;
import br.com.clone.unionmangas.model.Manga;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MangaFindDto {

    private Long idManga;
    private String mainTitle;
    private String linkImage;
    private List<CategoryDto> categories;

    public MangaFindDto() {
    }

    public MangaFindDto(Manga manga) {
        this.idManga = manga.getIdManga();
        this.mainTitle = manga.getMainTitle();
        this.linkImage = manga.getLinkImage();
        this.categories = CategoryDto.categorySetToCategoryDtoList(manga.getCategories());
    }

    public Long getIdManga() {
        return idManga;
    }

    public void setIdManga(Long idManga) {
        this.idManga = idManga;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

}