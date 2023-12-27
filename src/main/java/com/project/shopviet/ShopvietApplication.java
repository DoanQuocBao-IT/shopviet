package com.project.shopviet;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopvietApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		System.setProperty("DATASOURCE_URL", dotenv.get("DATASOURCE_URL"));
		System.setProperty("DATASOURCE_USERNAME", dotenv.get("DATASOURCE_USERNAME"));
		System.setProperty("DATASOURCE_PASSWORD", dotenv.get("DATASOURCE_PASSWORD"));
		System.setProperty("SERVER_PORT", dotenv.get("SERVER_PORT"));
		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
		System.setProperty("JWT_EXPIRATION_MS", dotenv.get("JWT_EXPIRATION_MS"));
		System.setProperty("JWT_REFRESH_EXPIRATION_MS", dotenv.get("JWT_REFRESH_EXPIRATION_MS"));
		System.setProperty("MAIL_HOST", dotenv.get("MAIL_HOST"));
		System.setProperty("MAIL_PORT", dotenv.get("MAIL_PORT"));
		System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
		System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
		System.setProperty("PATH_UPLOAD", dotenv.get("PATH_UPLOAD"));
		System.setProperty("PATH_IMAGE", dotenv.get("PATH_IMAGE"));
		System.setProperty("MAX_IMAGE_SIZE", dotenv.get("MAX_IMAGE_SIZE"));

		SpringApplication.run(ShopvietApplication.class, args);
	}

}
