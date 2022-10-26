package br.com.clone.unionmangas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clone.unionmangas.model.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

}
