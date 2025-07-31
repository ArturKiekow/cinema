package com.arturFerreira.cinema.config;

import com.arturFerreira.cinema.entity.Role;
import com.arturFerreira.cinema.entity.User;
import com.arturFerreira.cinema.repository.RoleRepository;
import com.arturFerreira.cinema.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class CreateInitialAdmin implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CreateInitialAdmin.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateInitialAdmin(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByRoleName(Role.Values.ADMIN.name());

        var userbd = userRepository.findByUsername("admin");
        if (userbd.isPresent()){
            System.out.println("Admin já existe");
        } else {
            var admin = new User();
            admin.setUsername("admin");
            admin.setCpf("000000000000");
            admin.setBirthDate(LocalDate.of(1000, 1, 1));
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Set.of(roleAdmin));
            userRepository.save(admin);
            System.out.println("Admin criado com sucesso");
        }
    }
}
