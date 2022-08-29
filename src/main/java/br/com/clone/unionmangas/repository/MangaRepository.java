package br.com.clone.unionmangas.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.clone.unionmangas.dto.manga.*;
import br.com.clone.unionmangas.model.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {

    @Query("SELECT new br.com.clone.unionmangas.dto.manga.MangaFindDto(manga) FROM Manga manga "
            + " WHERE :filter IS NULL "
            + " OR :filter = '' "
            + " OR manga.mainTitle LIKE CONCAT('%', :filter, '%') "
            + " OR manga.alternativeTitle LIKE CONCAT('%', :filter, '%')")
    Page<MangaFindDto> findByName(Pageable pageable, @Param("filter") String filter);

    @Query("SELECT new br.com.clone.unionmangas.dto.manga.MangaWeekDto(manga) FROM Manga manga "
            + "WHERE WEEK(manga.lastUpdate) = WEEK(CURDATE())")
    Page<MangaWeekDto> releseWeek(Pageable page);

}
