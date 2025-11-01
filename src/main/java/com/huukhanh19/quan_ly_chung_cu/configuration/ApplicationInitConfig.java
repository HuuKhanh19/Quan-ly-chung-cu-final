package com.huukhanh19.quan_ly_chung_cu.configuration;

import com.huukhanh19.quan_ly_chung_cu.entity.User;
import com.huukhanh19.quan_ly_chung_cu.enums.Role;
import com.huukhanh19.quan_ly_chung_cu.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(Role.ADMIN)
                        .build();

                userRepository.save(user);
                log.warn("Admin has been created with default password: admin, please change it");
            }

            if (userRepository.findByUsername("quanly").isEmpty()) {
                User user = User.builder()
                        .username("quanly")
                        .password(passwordEncoder.encode("quanly"))
                        .role(Role.QUANLY)
                        .build();

                userRepository.save(user);
                log.warn("quanly has been created with default password: quanly, please change it");
            }

            if (userRepository.findByUsername("ketoan").isEmpty()) {
                User user = User.builder()
                        .username("ketoan")
                        .password(passwordEncoder.encode("ketoan"))
                        .role(Role.KETOAN)
                        .build();

                userRepository.save(user);
                log.warn("ketoan has been created with default password: ketoan, please change it");
            }
        };
    }
}
