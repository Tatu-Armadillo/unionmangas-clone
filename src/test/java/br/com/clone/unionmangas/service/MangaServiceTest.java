package br.com.clone.unionmangas.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import br.com.clone.unionmangas.UnionmangasApplication;
import br.com.clone.unionmangas.repository.MangaRepository;

@SpringBootTest(classes = UnionmangasApplication.class)
public class MangaServiceTest {

    @Autowired
    private MangaRepository mangaRepository;

    @Test
    @DisplayName("Check that the return of all mangas for the week is not empty")
    void findAllMangasByWeekIsNotEmpty() {
        PageRequest page = PageRequest.of(1, 10);
        final var mangas = this.mangaRepository.findAllByWeek(page);
        Assertions.assertTrue(mangas.isEmpty());
    }

}
