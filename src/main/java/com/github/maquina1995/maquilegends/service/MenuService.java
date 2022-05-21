package com.github.maquina1995.maquilegends.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.maquina1995.maquilegends.domain.GameCharacter;
import com.github.maquina1995.maquilegends.domain.Mage;
import com.github.maquina1995.maquilegends.domain.Thief;
import com.github.maquina1995.maquilegends.domain.Warrior;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class MenuService implements AutoCloseable {

	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private final SaveService saveService;

	public void showIntro() {

		int option = this.createMenu("Nueva partida", "Continuar Partida", "Salir");

		boolean badOption = false;

		do {
			switch (option) {
			case 1:
				this.showNewCharacterMenu();
				break;
			case 2:
				badOption = this.showOldCharacterMenu();
				break;
			case 3:
				saveService.saveCharacter();
				System.out.println("Saliendo del juego");
				System.exit(0);
				break;
			default:
				badOption = true;
				break;
			}
		} while (badOption);
	}

	public void showIngame() {

	}

	@SneakyThrows
	private void showNewCharacterMenu() {

		int option = this.createMenu("Crear Guerrero", "Crear Ladrón", "Crear Mago");

		System.out.println("Que nombre quieres ponerle al héroe");
		String name = this.reader.readLine();

		GameCharacter gameCharacter = null;
		boolean badOption = false;

		do {
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
			default:
				badOption = true;
				break;
			}
		} while (badOption);

		System.out.println("Un nuevo " + gameCharacter.getClass()
				.getSimpleName() + " se ha gestado");
		saveService.saveCharacter(gameCharacter);
	}

	@SneakyThrows
	private boolean showOldCharacterMenu() {

		String name;
		do {
			System.out.print("Introduce el nombre del héroe a cargar (si no pones nombre volverás al anterior menú): ");
			name = reader.readLine();

			if (!StringUtils.hasText(name)) {
				return true;
			}

		} while (!saveService.readCharacter(name));

		return false;

	}

	@SneakyThrows
	private int createMenu(String... options) {

		System.out.println();

		AtomicInteger counter = new AtomicInteger(0);
		Stream.of(options)
				.map(e -> counter.addAndGet(1) + ". " + e)
				.forEach(System.out::println);

		// TODO: manejar cuando el usuario meta una String
		return Integer.valueOf(this.reader.readLine());
	}

	@PreDestroy
	@Override
	@SneakyThrows
	public void close() {
		reader.close();
	}

}
