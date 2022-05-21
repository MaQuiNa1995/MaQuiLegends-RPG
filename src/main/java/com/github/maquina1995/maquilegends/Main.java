package com.github.maquina1995.maquilegends;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.github.maquina1995.maquilegends.service.MenuService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class Main implements CommandLineRunner {

	private final MenuService menuService;

	public static void main(String... args) {
		new SpringApplicationBuilder(Main.class).web(WebApplicationType.NONE)
				.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuService.showIntro();
		menuService.showIngame();
	}
}