package br.com.clone.unionmangas.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.clone.unionmangas.model.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {

    @Query("SELECT manga FROM Manga manga "
            + " WHERE :filter IS NULL "
            + " OR :filter = '' "
            + " OR manga.mainTitle LIKE CONCAT('%', :filter, '%') "
            + " OR manga.alternativeTitle LIKE CONCAT('%', :filter, '%')")
    Set<Manga> findByName(@Param("filter") String filter);

}
