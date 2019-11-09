package de.hsh.dbs2.imdb.logic.dto;

/**
 * Data Transfer Object (DTO) fuer Objekte der Klasse Character
 * Enthaelt alles noetige fuer die Kommunikation GUI <-> Geschaeftslogik.
 */
public class CharacterDTO {
	
	private int person_ID; //Player
	private String character;
	private String alias;
	
	public CharacterDTO(CharacterDTO that) {
		this.character = that.character;
		this.person_ID = that.person_ID;
		this.alias = that.alias;
	}
	
	public CharacterDTO() {		
	}
	
	public String getCharacter() {
		return character;
	}
	
	public void setCharacter(String character) {
		this.character = character;
	}
	
	public int getPerson_ID() {
		return person_ID;
	}
	
	public void setPerson_ID(int person_ID) {
		this.person_ID = person_ID;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

}
