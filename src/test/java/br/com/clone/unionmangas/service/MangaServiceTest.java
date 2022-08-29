package br.com.clone.unionmangas.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import br.com.clone.unionmangas.UnionmangasApplication;
import br.com.clone.unionmangas.dto.manga.MangaWeekDto;
import br.com.clone.unionmangas.repository.MangaRepository;

@SpringBootTest(classes = UnionmangasApplication.class)
public class MangaServiceTest {

    @Autowired
    private MangaService mangaService;

    @MockBean
    private MangaRepository mangaRepository;

    private Pageable pageable = PageRequest.of(1, 10);;
    private Page<MangaWeekDto> mangas;

    @BeforeEach
    void setUp() {
        List<MangaWeekDto> mangasArray = new ArrayList<>();
        mangasArray.add(new MangaWeekDto());
        mangasArray.add(new MangaWeekDto());

        Page<MangaWeekDto> mangas = new PageImpl<>(mangasArray);
        Mockito.when(mangaRepository.releseWeek(pageable)).thenReturn(mangas);
    }

    @Test
    @DisplayName("Check that the return of all mangas for the week is not empty")
    void findAllMangasByWeekIsNotEmpty() {
        mangas = this.mangaService.releaseWeek(pageable);
        Assertions.assertTrue(!mangas.isEmpty());
    }

}
