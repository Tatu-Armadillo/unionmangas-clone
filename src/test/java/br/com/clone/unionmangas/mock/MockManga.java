package br.com.clone.unionmangas.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.clone.unionmangas.dto.chapter.ChapterGetDto;
import br.com.clone.unionmangas.dto.manga.MangaWeekDto;
import br.com.clone.unionmangas.enumerator.AgeGroupEnum;
import br.com.clone.unionmangas.model.Author;
import br.com.clone.unionmangas.model.Chapter;
import br.com.clone.unionmangas.model.Category;
import br.com.clone.unionmangas.model.Manga;

public class MockManga {

    private LocalDate data = LocalDate.now();

    public List<Manga> mockMangaList() {
        List<Manga> mangas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mangas.add(this.mockManga(i));
        }
        return mangas;
    }
    
    public List<MangaWeekDto> mockMangaWeekDtoList() {
        List<MangaWeekDto> mangas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mangas.add(this.mangaWeekVO(i));
        }
        return mangas;
    }

    public Manga mockManga(Integer number) {
        Manga manga = new Manga();
        manga.setMainTitle("mainTitle" + number);
        manga.setAlternativeTitle("alternativeTitle" + number);
        manga.setLinkImage("linkImage" + number);
        manga.setBlobImage(new byte[number]);
        manga.setDescription("description" + number);
        manga.setStatus("status" + number);
        manga.setRating(number.doubleValue());
        manga.setVolumeQuantity(number);
        manga.setReleaseDate(data);
        manga.setLastUpdate(data);

        Author author = new Author(number.longValue(), "author teste" + number, number, data);
        manga.setAuthors(Set.of(author));

        Category category = new Category(number.longValue(), "category teste" + number, AgeGroupEnum.LIVRE, "description" + number);
        manga.setCategories(Set.of(category));

        Chapter chapter = new Chapter(number, number, "titleChapter", number, "linkChapter" + number);
        manga.setChapters(Set.of(chapter));

        return manga;
    }

    public MangaWeekDto mangaWeekVO(Integer number) {
        MangaWeekDto manga = new MangaWeekDto();
        manga.setMainTitle("mainTitle" + number);
        manga.setLinkImage("linkImage" + number);
        manga.setLastUpdate(data);

        var chapter = new ChapterGetDto();
        chapter.setIdChapter(number.longValue());
        chapter.setNumberChapter(number);
        manga.setChapters(Set.of(chapter));

        return manga;
    }

}
