package com.korinek.MeteorologicalDataApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MeteorologicalDataAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeteorologicalDataAppApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Meteorological Data App!";
	}
}
