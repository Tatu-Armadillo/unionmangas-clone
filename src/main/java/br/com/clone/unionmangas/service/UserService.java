package br.com.clone.unionmangas.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.clone.unionmangas.dto.security.CreateCredentialsDto;
import br.com.clone.unionmangas.model.User;
import br.com.clone.unionmangas.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PermissionService permissionService;
    private final ReaderService readerService;

    @Autowired
    public UserService(UserRepository userRepository, PermissionService permissionService,
            ReaderService readerService) {
        this.userRepository = userRepository;
        this.permissionService = permissionService;
        this.readerService = readerService;
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

    public void createReader(final CreateCredentialsDto data) {
        final var permission = this.permissionService.getPermission(data.isScan());

        final var user = new User(
                data.getUserName(),
                data.getUserName(),
                securityPassword(data.getPassword()),
                true,
                true,
                true,
                true);
        user.setPermissions(List.of(permission));
        final var persisted = this.userRepository.save(user);

        this.readerService.create(data.getReaderDto(), persisted);
    }

    private static String securityPassword(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());

        String result = passwordEncoder.encode(password);
        return result.substring("{pbkdf2}".length());
    }

}
