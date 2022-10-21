package br.com.clone.unionmangas.service;

import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.model.Permission;
import br.com.clone.unionmangas.repository.PermissionRepository;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission getPermission(boolean isScan) {
        if (isScan) {
            return this.permissionRepository.getPermissionByDescription("SCAN_ADM");
        }
        return this.permissionRepository.getPermissionByDescription("COMMON_READER");
    }
}
