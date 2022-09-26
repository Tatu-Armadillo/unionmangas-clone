package br.com.clone.unionmangas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.clone.unionmangas.model.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @Query("SELECT chapter FROM Chapter chapter "
            + " JOIN chapter.manga manga"
            + " WHERE manga.idManga = :idManga")
    Page<Chapter> findChaptersByManga(
            Pageable pageable,
            @Param("idManga") Long idManga);

}
