package br.com.clone.unionmangas.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.model.Chapter;
import br.com.clone.unionmangas.model.Manga;
import br.com.clone.unionmangas.repository.ChapterRepository;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private MangaService mangaService;

    public List<Chapter> findChaptersByManga(Long idManga) {
        final var response = this.chapterRepository.findChaptersByManga(idManga);
        return response;
    }

    public List<Chapter> insertChapters(Long idManga, Chapter chapter) {
        final var manga = this.mangaService.findById(idManga);
        
        this.checkExistenceChapter(manga, chapter);
        
        chapter.setReleaseDate(LocalDate.now());
        this.mangaService.updateReleaseDateAndVolumeQuantity(manga, chapter);
        
        chapter.setManga(manga);
        this.chapterRepository.save(chapter);

        final var response = this.findChaptersByManga(idManga);
        return response;
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
