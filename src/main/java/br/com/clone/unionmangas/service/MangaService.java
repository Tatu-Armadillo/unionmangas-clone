package br.com.clone.unionmangas.service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.exception.NegocioException;
import br.com.clone.unionmangas.model.Author;
import br.com.clone.unionmangas.model.Genre;
import br.com.clone.unionmangas.model.Manga;
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

    public Manga findById(final Long idManga) {
        Manga manga = this.mangaRepository.findById(idManga)
                .orElseThrow(() -> new NegocioException("Titulo indisponivel"));
        return manga;
    }

    public Manga create(final Manga manga) {
        var author = this.authorService.create(manga.getAuthor());
        manga.setAuthor(author);

        Set<Genre> genres = new HashSet<>();
        manga.getGenres().forEach(g -> {
            var genre = this.genreService.findByName(g.getName());
            genres.add(genre);
        });
        manga.setGenres(genres);

        if (manga.getEvaluation() == null) {
            manga.setEvaluation(0.0);
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
        mangaDb.setEvaluation(manga.getEvaluation());
        mangaDb.setReleaseDate(manga.getReleaseDate());

        mangaDb.setGenres(manga.getGenres());

        this.updateImage(idManga, manga.getLinkImage(), manga.getBlobImage());
        this.setReturnAuthor(mangaDb, manga.getAuthor());
        return mangaDb;
    }

    public String delete(final Long idManga) {
        var manga = this.findById(idManga);
        String response = "Successfully deleted manga: " + manga.getMainTitle();
        this.mangaRepository.delete(manga);
        return response;
    }

    private void updateImage(final Long idManga, final String linkImage, final Byte[] blobImage) {
        var manga = this.mangaRepository.findById(idManga).orElseThrow();
        manga.setLinkImage(linkImage);
        manga.setBlobImage(blobImage); // TODO apply image treatment rule
    }

    private void setReturnAuthor(Manga mangaDb, Author author) {
        var authorDb = this.authorService.findByName(author.getName());
        mangaDb.setAuthor(authorDb);
    }
}
