package com.mallika.EmployeeManagementSystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class EmployeeManagementSystemApplicationTests {

	public static void main(String[] args) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String password = "employee123";
		String hash = "$2a$10$wpOpliNfgghELycmwRCE3eyjvdKDizXnjclpR8p1gllRcoyI5pH7K";

		System.out.println(encoder.matches(password, hash));
	}

}
