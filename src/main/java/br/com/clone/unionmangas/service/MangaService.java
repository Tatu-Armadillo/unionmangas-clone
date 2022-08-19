package br.com.clone.unionmangas.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.model.*;
import br.com.clone.unionmangas.repository.MangaRepository;

@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    public List<Manga> findAllByName(final String filter) {
        Set<Manga> mangas = this.mangaRepository.findByName(filter);
        List<Manga> ordination = mangas.stream().collect(Collectors.toList());
        ordination.sort(Comparator.comparing(a -> a.getMainTitle()));
        return ordination;
    }

    public Page<Manga> releaseWeek(final Pageable pageable) {
        Page<Manga> mangas = this.mangaRepository.findAll(pageable);
        return mangas;
    }

    public Manga findById(final Long idManga) {
        Manga manga = this.mangaRepository.findById(idManga)
                .orElseThrow(() -> new NegocioException("Titulo indisponivel"));
        return manga;
    }

    public Manga create(final Manga manga) {
        var author = this.authorService.create(manga.getAuthor());
        manga.setAuthor(author);

        Set<Genre> genres = this.genreService.checkGenres(manga.getGenres());
        manga.setGenres(genres);

        if (manga.getRating() == null) {
            manga.setRating(0.0);
        }

        var response = mangaRepository.save(manga);
        return response;

    }

    public Manga update(final Long idManga, final Manga manga) {
        var mangaDb = this.findById(idManga);
        mangaDb.setMainTitle(manga.getMainTitle());
        mangaDb.setAlternativeTitle(manga.getAlternativeTitle());
        mangaDb.setAlternativeTitle(manga.getAlternativeTitle());
        mangaDb.setDescription(manga.getDescription());
        mangaDb.setStatus(manga.getStatus());
        mangaDb.setRating(manga.getRating());
        mangaDb.setReleaseDate(manga.getReleaseDate());

        mangaDb.setLinkImage(manga.getLinkImage());
        mangaDb.setGenres(manga.getGenres());

        this.setReturnAuthor(mangaDb, manga.getAuthor());
        return mangaDb;
    }

    public String delete(final Long idManga) {
        var manga = this.findById(idManga);
        String response = "Successfully deleted manga: " + manga.getMainTitle();
        this.mangaRepository.delete(manga);
        return response;
    }

    public void updateCover(final Long idManga, final MultipartFile blobImage) {
        try {
            var manga = this.mangaRepository.findById(idManga).orElseThrow();
            manga.setBlobImage(blobImage.getBytes());
        } catch (Exception e) {
            throw new NegocioException("Update cover error");
        }
    }

    public void updateReleaseDateAndVolumeQuantity(Manga manga, Chapter chapter) {
        manga.setVolumeQuantity(chapter.getNumberVolume());
        manga.setLastUpdate(chapter.getReleaseDate());
    }

    private void setReturnAuthor(Manga mangaDb, Author author) {
        var authorDb = this.authorService.findByName(author.getName());
        mangaDb.setAuthor(authorDb);
    }
}
