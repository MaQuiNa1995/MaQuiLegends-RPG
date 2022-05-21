package com.github.maquina1995.maquilegends.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "class")
@JsonSubTypes({
	@Type(value = Warrior.class, name = "Warrior"),
	@Type(value = Thief.class, name = "Thief"),
	@Type(value = Mage.class, name = "Mage")
})
public abstract class GameCharacter {

	protected String name;
	protected Float damage = 3F;
	protected Float defense = 10F;
	protected Float health = 10F;
	protected Integer level = 1;
	protected Integer exp = 0;

	protected GameCharacter(String name) {
		this.name = name;
	}

	public Integer attack(GameCharacter character) {

		float damageDealt = character.getDefense() - this.damage;
		damageDealt = damageDealt > 0 ? 0 : damageDealt;

		return Math.round(damageDealt);
	}

	public void levelUp(Integer exp) {
		this.exp = this.exp + exp;

		if (this.exp > 100) {
			this.exp = 0;
			this.level++;
			System.out.println("El héroe" + this.name + " ha subido de nivel al " + this.level);
		}
	}

	public boolean defend(int damage) {

		this.health = this.health - damage;
		return Math.round(this.health) > 0;
	}

	public Float getDamage() {
		return damage * level;
	}

	public Float getDefense() {
		return defense * level;
	}

	public Float getHealth() {
		return health * level;
	}

}
