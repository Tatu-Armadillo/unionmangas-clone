package br.com.clone.unionmangas.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.dto.chapter.ChapterGetDto;
import br.com.clone.unionmangas.dto.chapter.ChapterParamDto;
import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.model.Chapter;
import br.com.clone.unionmangas.model.Manga;
import br.com.clone.unionmangas.repository.ChapterRepository;

@Service
public class ChapterService {

    private final ChapterRepository chapterRepository;

    private final MangaService mangaService;
    
    @Autowired
    public ChapterService(ChapterRepository chapterRepository, MangaService mangaService) {
        this.chapterRepository = chapterRepository;
        this.mangaService = mangaService;
    }

    public Page<ChapterGetDto> findChaptersByManga(Pageable pageable, Long idManga) {
        this.mangaService.findById(idManga);
        final var response = this.chapterRepository.findChaptersByManga(pageable, idManga);
        final var dtos = response.map(ChapterGetDto::new);
        return dtos;
    }

    public void insertChapters(Long idManga, ChapterParamDto chapterDto) {
        final var manga = this.mangaService.findById(idManga);
        
        final var chapter = new Chapter(
            chapterDto.getVolume(),
            chapterDto.getNumberChapter(),
            chapterDto.getTitleChapter(),
            chapterDto.getPages(),
            chapterDto.getLinkPages());

        this.checkExistenceChapter(manga, chapter);
        
        chapter.setReleaseDate(LocalDate.now());
        this.mangaService.updateReleaseDateAndVolumeQuantity(manga, chapter);
        
        chapter.setManga(manga);
        this.chapterRepository.save(chapter);
    }

    private void checkExistenceChapter(Manga manga, Chapter chapter) {
        final var chapters = manga.getChapters();
        for (Chapter c : chapters) {
            if (c.equals(chapter)) {
                throw new NegocioException("Chapter already registered");
            }
        }
    }

}
