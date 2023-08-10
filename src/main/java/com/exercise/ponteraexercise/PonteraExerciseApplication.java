package com.exercise.ponteraexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PonteraExerciseApplication {

	@Bean
	public StartupRunner schedulerRunner() {
		return new StartupRunner();
	}

	public static void main(String[] args) {
		SpringApplication.run(PonteraExerciseApplication.class, args);
	}

}
