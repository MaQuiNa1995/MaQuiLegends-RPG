package com.github.maquina1995.maquilegends.domain;

public class Mage extends GameCharacter {

	public Mage(String name) {
		super(name);
		super.health = super.health * 0.55f;
		super.defense = super.defense * 0.50f;
	}

}
