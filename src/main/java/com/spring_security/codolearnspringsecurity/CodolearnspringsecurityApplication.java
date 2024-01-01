package com.spring_security.codolearnspringsecurity;

import com.spring_security.codolearnspringsecurity.Entity.AppicationUser;
import com.spring_security.codolearnspringsecurity.Entity.Role;
import com.spring_security.codolearnspringsecurity.Repository.RoleRepository;
import com.spring_security.codolearnspringsecurity.Repository.UserRepositories;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CodolearnspringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodolearnspringsecurityApplication.class, args);
	}


	/*
	* CommandLineRunner : thuc hien logic 1 cach tu dong khi khoi chay
	* */
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepositories userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			AppicationUser admin = new AppicationUser(1, "admin", passwordEncoder.encode("password"), roles);
			userRepository.save(admin);
		};
	}
}
