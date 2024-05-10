package one.tsv.medclinic.service;

import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import one.tsv.medclinic.dto.UserDto;
import one.tsv.medclinic.entity.User;
import one.tsv.medclinic.repository.RoleRepository;
import one.tsv.medclinic.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User registerNewUserAccount(final UserDto accountDto) throws EntityExistsException, BadCredentialsException {
        if (userRepository.findByEmail(accountDto.getEmail()) != null) {
            throw new EntityExistsException("There is an account with that email address: " + accountDto.getEmail());
        }

        if (userRepository.findByUsername(accountDto.getUsername()) != null) {
            throw new EntityExistsException("There is an account with that username: " + accountDto.getUsername());
        }

        if (!accountDto.getPassword().equals(accountDto.getMatchingPassword())) {
            throw new BadCredentialsException("Passwords do not match");
        }

        final User user = new User();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setRoles(Collections.singletonList(roleRepository.findById("ROLE_USER").get()));
        user.getMedicalCard().setUser(user);

        return userRepository.save(user);
    }
}
