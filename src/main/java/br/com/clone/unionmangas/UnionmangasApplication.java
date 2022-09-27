package br.com.clone.unionmangas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@SpringBootApplication
public class UnionmangasApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnionmangasApplication.class, args);

		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());

		String result = passwordEncoder.encode("admin234");
		System.out.println("My hash " + result);
	}

}
