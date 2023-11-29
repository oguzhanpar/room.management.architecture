package com.management.room.configurations;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class FakerConfig {
	
	@Bean
	public Faker faker() {
		return new Faker(new Locale("tr-TR"));
	}

}