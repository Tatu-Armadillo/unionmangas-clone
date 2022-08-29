package br.com.clone.unionmangas.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.clone.unionmangas.dto.manga.MangaWeekDto;
import br.com.clone.unionmangas.mock.MockManga;
import br.com.clone.unionmangas.model.Manga;

public class DozerMapperTest {

    private MockManga inputObject;
    private MangaWeekDto mangaWeekDto;
    private Manga entityBaseManga;
    private Integer number;

    @BeforeEach
    public void setUp() {
        inputObject = new MockManga();
    }

    @Test
    void parseEntityToVOTest() {
        number = 0;
        entityBaseManga = inputObject.mockManga(number);
        mangaWeekDto = DozerMapper.parseObject(inputObject.mockManga(number), MangaWeekDto.class);

        assertEquals(mangaWeekDto.getIdManga(), entityBaseManga.getIdManga());
        assertEquals(mangaWeekDto.getMainTitle(), entityBaseManga.getMainTitle());
        assertEquals(mangaWeekDto.getLinkImage(), entityBaseManga.getLinkImage());
        assertEquals(mangaWeekDto.getLastUpdate(), entityBaseManga.getLastUpdate());
    }

    @Test
    void parseEntityListToVOListTest() {

        List<MangaWeekDto> mangaWeekDtos = DozerMapper.parseListObjects(inputObject.mockMangaList(), MangaWeekDto.class);

        number = 0;
        mangaWeekDto = mangaWeekDtos.get(number);
        entityBaseManga = inputObject.mockManga(number);
        assertEquals(mangaWeekDto.getIdManga(), entityBaseManga.getIdManga());
        assertEquals(mangaWeekDto.getMainTitle(), entityBaseManga.getMainTitle());
        assertEquals(mangaWeekDto.getLinkImage(), entityBaseManga.getLinkImage());
        assertEquals(mangaWeekDto.getLastUpdate(), entityBaseManga.getLastUpdate());

        number = 1;
        mangaWeekDto = mangaWeekDtos.get(number);
        entityBaseManga = inputObject.mockManga(number);
        assertEquals(mangaWeekDto.getIdManga(), entityBaseManga.getIdManga());
        assertEquals(mangaWeekDto.getMainTitle(), entityBaseManga.getMainTitle());
        assertEquals(mangaWeekDto.getLinkImage(), entityBaseManga.getLinkImage());
        assertEquals(mangaWeekDto.getLastUpdate(), entityBaseManga.getLastUpdate());

        number = 2;
        mangaWeekDto = mangaWeekDtos.get(number);
        entityBaseManga = inputObject.mockManga(number);
        assertEquals(mangaWeekDto.getIdManga(), entityBaseManga.getIdManga());
        assertEquals(mangaWeekDto.getMainTitle(), entityBaseManga.getMainTitle());
        assertEquals(mangaWeekDto.getLinkImage(), entityBaseManga.getLinkImage());
        assertEquals(mangaWeekDto.getLastUpdate(), entityBaseManga.getLastUpdate());

        number = 5;
        mangaWeekDto = mangaWeekDtos.get(number);
        entityBaseManga = inputObject.mockManga(number);
        assertEquals(mangaWeekDto.getIdManga(), entityBaseManga.getIdManga());
        assertEquals(mangaWeekDto.getMainTitle(), entityBaseManga.getMainTitle());
        assertEquals(mangaWeekDto.getLinkImage(), entityBaseManga.getLinkImage());
        assertEquals(mangaWeekDto.getLastUpdate(), entityBaseManga.getLastUpdate());

        number = 7;
        mangaWeekDto = mangaWeekDtos.get(number);
        entityBaseManga = inputObject.mockManga(number);
        assertEquals(mangaWeekDto.getIdManga(), entityBaseManga.getIdManga());
        assertEquals(mangaWeekDto.getMainTitle(), entityBaseManga.getMainTitle());
        assertEquals(mangaWeekDto.getLinkImage(), entityBaseManga.getLinkImage());
        assertEquals(mangaWeekDto.getLastUpdate(), entityBaseManga.getLastUpdate());

    }
}
