package com.github.maquina1995.maquilegends.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;

import lombok.SneakyThrows;

// TODO: conseguir que se limpie la consola
//@Aspect
//@Component
public class ClearConsole {

	private static final String PACKAGE = "com.github.maquina1995.maquilegends.service.";

	// TODO: verificar en que SO se ejecuta
	@SneakyThrows
	@After("execution(* " + PACKAGE + "MenuService.show*(..))")
	public void logAfter(JoinPoint joinPoint) {
		new ProcessBuilder("cmd", "/c", "cls").inheritIO()
				.start()
				.waitFor();
	}

}
