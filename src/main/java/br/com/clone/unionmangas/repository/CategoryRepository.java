package br.com.clone.unionmangas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.clone.unionmangas.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT category FROM Category category"
            + " WHERE category.name = :name")
    Category findByName(@Param("name") String name);
    
    @Query("SELECT category FROM Category category"
            + " WHERE category.name LIKE CONCAT('%', :name, '%')")
    Page<Category> findAllByName(Pageable pageable, @Param("name") String name);
}
