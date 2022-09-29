package br.com.clone.unionmangas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.clone.unionmangas.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> { }
