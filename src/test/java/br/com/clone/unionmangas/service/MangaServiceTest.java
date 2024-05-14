package br.com.clone.unionmangas.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import br.com.clone.unionmangas.dto.manga.MangaWeekDto;
import br.com.clone.unionmangas.model.Manga;
import br.com.clone.unionmangas.repository.MangaRepository;

@Tags({ @Tag("unit"), @Tag("manga") })
@DisplayName("Testes da regra de negocio em formacao aluno")
@ExtendWith(MockitoExtension.class)
class MangaServiceTest {

    @InjectMocks
    private MangaService mangaService;

    @Mock
    private MangaRepository mangaRepository;

    private Pageable pageable = PageRequest.of(1, 10);;
    private Page<MangaWeekDto> mangas;

    @BeforeEach
    void setUp() {
        List<Manga> mangasArray = new ArrayList<>();
        mangasArray.add(new Manga());
        mangasArray.add(new Manga());

        Page<Manga> mangas = new PageImpl<>(mangasArray);
        Mockito.when(mangaRepository.releseWeek(pageable)).thenReturn(mangas);
    }

    @Test
    @DisplayName("Check that the return of all mangas for the week is not empty")
    void findAllMangasByWeekIsNotEmpty() {
        this.mangas = this.mangaService.releaseWeek(pageable);
        Assertions.assertTrue(!mangas.isEmpty());
    }

}
