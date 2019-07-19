package it.capgemini.clubOlympia.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tennis_court")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class TennisCourt extends Court {

	@Column(name="has_double_lines")
	private boolean hasDoubleLines;

	public TennisCourt(int id, String name, Surface surface, int numPlayers, double cost, boolean hasdoubleLines) {
		super(id, name, surface, numPlayers, cost);
		this.hasDoubleLines = hasdoubleLines;

	}

	public TennisCourt() {

	}

	@Override
	protected Surface[] allowedSurfaces() {
		return new Surface[] { Surface.GRASS, Surface.CLAY, Surface.HARDCOURT };
	}

	@Override
	protected int maxPlayers() {
		return 4;
	}


	public boolean isHasDoubleLines() {
		return hasDoubleLines;
	}

	public void setHasDoubleLines(boolean hasDoubleLines) {
		this.hasDoubleLines = hasDoubleLines;
	}

}
