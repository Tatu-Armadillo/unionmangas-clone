package br.com.clone.unionmangas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.clone.unionmangas.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT genre FROM Genre genre"
            + " WHERE genre.name = :name")
    Genre findByName(@Param("name") String name);
    
    @Query("SELECT genre FROM Genre genre"
            + " WHERE genre.name LIKE CONCAT('%', :name, '%')")
    Page<Genre> findAllByName(Pageable pageable, @Param("name") String name);
}
