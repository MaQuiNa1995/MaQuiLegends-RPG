package com.github.maquina1995.maquilegends.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsoleUtils {

	// TODO: verificar en que SO se ejecuta para elegir clear o cls
	@SneakyThrows
	public void clearConsole() {
		Runtime.getRuntime().exec("cls");
	}

}
