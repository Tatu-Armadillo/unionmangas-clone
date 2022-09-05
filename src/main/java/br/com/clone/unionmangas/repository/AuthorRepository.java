package br.com.clone.unionmangas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.clone.unionmangas.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT author FROM Author author "
            + " WHERE author.name LIKE CONCAT('%', :name, '%') "
            + " OR author.pseudonym LIKE CONCAT('%', :name, '%')")
    Page<Author> findByName(Pageable pageable, @Param("name") String name);

    @Query("SELECT author FROM Author author "
            + " WHERE author.name = :name ")
    Author findByName(@Param("name") String name);

}
