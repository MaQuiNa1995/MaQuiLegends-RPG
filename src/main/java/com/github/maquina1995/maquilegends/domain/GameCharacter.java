package com.github.maquina1995.maquilegends.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public abstract class GameCharacter {

	protected String name;
	protected Float damage = 3F;
	protected Float defense = 10F;
	protected Float health = 10F;
	protected Integer level = 1;

	protected GameCharacter(String name) {
		this.name = name;
	}

	public Integer attack(GameCharacter character) {

		float damageDealt = character.getDefense() - this.damage;
		damageDealt = damageDealt > 0 ? 0 : damageDealt;

		return Math.round(damageDealt);
	}

	public boolean defend(int damage) {

		this.health = this.health - damage;
		return Math.round(this.health) > 0;
	}

}
