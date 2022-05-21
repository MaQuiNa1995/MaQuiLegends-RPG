package com.github.maquina1995.maquilegends.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.github.maquina1995.maquilegends.domain.GameCharacter;
import com.github.maquina1995.maquilegends.domain.Mage;
import com.github.maquina1995.maquilegends.domain.Thief;
import com.github.maquina1995.maquilegends.domain.Warrior;

import lombok.SneakyThrows;

@Service
public class GameMenuServiceImpl implements AutoCloseable {

	private final BufferedReader reader;

	public GameMenuServiceImpl() {
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public void showIntro() {

		int option = this.createMenu("Nueva partida", "Salir");

		switch (option) {
		case 1:
			this.showNewCharacterMenu();
			break;
		case 2:
			System.out.println("Saliendo del juego");
			System.exit(0);
			break;

		}
	}

	@SneakyThrows
	public void showNewCharacterMenu() {

		int option = this.createMenu("Crear Guerrero", "Crear Ladrón", "Crear Mago");

		System.out.println("Que nombre quieres ponerle al héroe");
		String name = this.reader.readLine();

		// TODO: evitar el NPE cuando el usuario no meta una opción válida
		GameCharacter gameCharacter = null;

		switch (option) {
		case 1:
			gameCharacter = new Warrior(name);
			break;
		case 2:
			gameCharacter = new Thief(name);
			break;
		case 3:
			gameCharacter = new Mage(name);
			break;
		}
		System.out.println("Un nuevo " + gameCharacter.getClass().getSimpleName() + " se ha gestado");
	}

	@SneakyThrows
	private int createMenu(String... options) {

		System.out.println();

		AtomicInteger counter = new AtomicInteger(0);
		Stream.of(options).map(e -> counter.addAndGet(1) + ". " + e).forEach(System.out::println);

		// TODO: manejar cuando el usuario meta una String
		return Integer.valueOf(this.reader.readLine());
	}

	@Override
	@SneakyThrows
	public void close() {
		if (this.reader != null) {
			reader.close();
		}
	}

}
