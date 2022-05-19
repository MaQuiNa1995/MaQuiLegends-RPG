package com.github.maquina1995.maquilegends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.maquina1995.maquilegends.service.GameMenuServiceImpl;

@SpringBootApplication
public class Main implements CommandLineRunner {

	@Autowired
	private GameMenuServiceImpl gameMenuServiceImpl;

	public static void main(String... args) {
		SpringApplication.run(Main.class);
	}

	@Override
	public void run(String... args) throws Exception {
		gameMenuServiceImpl.showIntro();
	}
}