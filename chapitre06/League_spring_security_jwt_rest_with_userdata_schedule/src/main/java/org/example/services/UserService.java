package org.example.services;

import org.example.dto.Role;
import org.example.dto.User;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        List<Role> roles = roleRepository.getRolesByUsername(username);
        user.setRoles(roles);
        return user;
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Role> roles = user.getRoles().stream().distinct().collect(Collectors.toList());

        for (Role role : roles) {
            Role existingRole = roleRepository.findByName(role.getName());

            if (existingRole != null) {
                role.setId(existingRole.getId());
            } else {
                throw new IllegalArgumentException("Role " + role.getName() + " does not exist.");
            }

        }
        user.setRoles(roles);
        userRepository.save(user);


    }


}
