package it.capgemini.clubOlympia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@Table(name="soccer_court")
public class SoccerCourt extends Court {

	@Column(name="has_big_field_doors")
	private boolean hasBigFieldDoors;

	public SoccerCourt() {
	}

	public SoccerCourt(int id, String name, Surface surface, int numPlayers, double cost, boolean hasBigFieldDoors) {
		super(id, name, surface, numPlayers, cost);
		this.hasBigFieldDoors = hasBigFieldDoors;
	}

	@Override
	protected Surface[] allowedSurfaces() {
		return new Surface[] { Surface.GRASS, Surface.HARDCOURT };
	}

	@Override
	protected int maxPlayers() {
		return 14;
	}

	public boolean isHasBigFieldDoors() {
		return hasBigFieldDoors;
	}

	public void setHasBigFieldDoors(boolean hasBigFieldDoors) {
		this.hasBigFieldDoors = hasBigFieldDoors;
	}

}
