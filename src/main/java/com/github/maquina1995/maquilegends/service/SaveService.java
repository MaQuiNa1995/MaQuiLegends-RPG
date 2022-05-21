package com.github.maquina1995.maquilegends.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.maquina1995.maquilegends.domain.GameCharacter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

@Setter
@Service
@RequiredArgsConstructor
public class SaveService {

	private final ObjectMapper objectMapper;

	@Getter
	private GameCharacter actualCharacter = null;

	@SneakyThrows
	public void saveCharacter() {

		if (this.actualCharacter != null) {
			String json = objectMapper.writeValueAsString(this.actualCharacter);
			this.saveCharacterFile(json, this.actualCharacter.getName());
			System.out.println("Progreso del personaje: " + this.actualCharacter.getName());
		}
	}

	@SneakyThrows
	public void saveCharacter(GameCharacter character) {

		String json = objectMapper.writeValueAsString(character);
		this.saveCharacterFile(json, character.getName() + ".json");
	}

	public boolean readCharacter(String characterName) {

		boolean success = true;
		try {
			String json = Files.readString(Paths.get(characterName + ".json"));
			this.actualCharacter = objectMapper.readValue(json, GameCharacter.class);
			System.out.println("Se ha cargado el personaje correctamente");
		} catch (Exception e) {
			System.out.println("Error al cargar personaje: " + e.getMessage());
			success = false;
		}
		return success;
	}

	private boolean saveCharacterFile(String characterJson, String characterName) {

		boolean success = true;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(characterName))) {
			writer.write(characterJson);
			System.out.println("Se ha guardado el progreso del personaje correctamente");
		} catch (IOException e) {
			System.out.println("Error al guardar personaje: " + e.getMessage());
			success = false;
		}
		return success;
	}

}
