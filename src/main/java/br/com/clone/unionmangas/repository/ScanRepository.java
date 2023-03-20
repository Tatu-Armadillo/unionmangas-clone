package br.com.clone.unionmangas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clone.unionmangas.model.Scan;

@Repository
public interface ScanRepository extends JpaRepository<Scan, Long> {
    
}
