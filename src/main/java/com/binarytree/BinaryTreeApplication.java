package com.binarytree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan({"com.vaadin.wscdn"})
@SpringBootApplication
public class BinaryTreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinaryTreeApplication.class, args);
	}
}
