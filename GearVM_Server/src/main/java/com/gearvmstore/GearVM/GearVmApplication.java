package com.gearvmstore.GearVM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GearVmApplication {

	public static void main(String[] args) {
        SpringApplication.run(GearVmApplication.class, args);
	}
}
