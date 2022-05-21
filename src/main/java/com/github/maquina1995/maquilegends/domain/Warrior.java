package com.github.maquina1995.maquilegends.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Warrior extends GameCharacter {

	public Warrior(String name) {
		super(name);
		super.damage = super.damage * 0.50f;
		super.health = super.health * 0.75f;
		super.defense = super.defense * 0.70f;
	}

}
