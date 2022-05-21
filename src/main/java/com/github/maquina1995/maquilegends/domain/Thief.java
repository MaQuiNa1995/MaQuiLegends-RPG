package com.github.maquina1995.maquilegends.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Thief extends GameCharacter {

	public Thief(String name) {
		super(name);
		super.damage = super.damage * 0.80f;
		super.health = super.health * 0.65f;
		super.defense = super.defense * 0.50f;
	}

}
