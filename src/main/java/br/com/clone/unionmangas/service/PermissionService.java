package br.com.clone.unionmangas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.model.Permission;
import br.com.clone.unionmangas.repository.PermissionRepository;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission getPermission(boolean isScan) {
        this.verifyExistsPermission();
        
        if (isScan) {
            return this.permissionRepository.getPermissionByDescription("SCAN_ADM");
        }
        return this.permissionRepository.getPermissionByDescription("COMMON_READER");
    }

    public void verifyExistsPermission() {
        final var permissions = this.permissionRepository.findAll();

        if (permissions.isEmpty()) {
            final var ADMIN = new Permission("ADMIN");
            final var COMMON_READER = new Permission("COMMON_READER");
            final var SCAN_ADM = new Permission("SCAN_ADM");
            this.permissionRepository.save(ADMIN);
            this.permissionRepository.save(COMMON_READER);
            this.permissionRepository.save(SCAN_ADM);
        }
    }
}
