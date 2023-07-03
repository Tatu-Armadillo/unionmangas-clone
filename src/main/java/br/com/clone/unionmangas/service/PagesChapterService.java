package br.com.clone.unionmangas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import br.com.clone.unionmangas.dto.chapter.PagesChapterDto;
import br.com.clone.unionmangas.model.Chapter;
import br.com.clone.unionmangas.model.PagesChapter;
import br.com.clone.unionmangas.repository.PagesChapterRepository;

@Service
public class PagesChapterService {

    private final PagesChapterRepository pagesChapterRepository;

    @Autowired
    public PagesChapterService(final PagesChapterRepository pagesChapterRepository) {
        this.pagesChapterRepository = pagesChapterRepository;
    }

    public void savePagesChapter(final List<PagesChapterDto> pages, final Chapter chapter) {

        for (PagesChapterDto dto : pages) {
            final var entity = new PagesChapter(dto.getPageNumber(), dto.getBlobPages(), chapter);
            this.pagesChapterRepository.save(entity);
        }
    }

}
