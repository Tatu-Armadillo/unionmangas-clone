package br.com.clone.unionmangas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.dto.security.CreateCredentialsDto;
import br.com.clone.unionmangas.model.User;
import br.com.clone.unionmangas.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionService permissionService;

    @Autowired
    public UserService(
            final UserRepository userRepository,
            final PermissionService permissionService,
            final ReaderService readerService) {
        this.userRepository = userRepository;
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = this.userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }

    public void create(final CreateCredentialsDto data, boolean isScan) {
        final var permission = this.permissionService.getPermission(isScan);

        final var user = new User(
                data.getEmail(),
                data.getFullName(),
                data.getPassword(),
                true,
                true,
                true,
                true);
        user.setPermissions(List.of(permission));
        this.userRepository.save(user);
    }

    public UserDetails findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

}
