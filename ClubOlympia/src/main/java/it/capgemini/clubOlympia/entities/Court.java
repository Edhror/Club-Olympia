package it.capgemini.clubOlympia.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import it.capgemini.clubOlympia.exception.SportClubException;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Court {
	@Id
	@SequenceGenerator(name = "courtSeq", sequenceName = "public.court_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courtSeq")
	protected int id;
	protected String name;
	@Column(name = "surface_id")
	protected Surface surface;
	@Column(name = "num_players")
	protected int numPlayers;
	protected double cost;

	@OneToMany(mappedBy = "court")
	protected Set<Reservation> reservations = new HashSet<>();

	public Court() {

	}

	public Court(int id, String name, Surface surface, int numPlayers, double cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		if (Arrays.stream(allowedSurfaces()).noneMatch(s -> s == surface)) {
			throw new SportClubException("Surface not allowed for this court");
		}
		this.surface = surface;
		if (numPlayers < 0 || numPlayers > maxPlayers()) {
			throw new SportClubException("Ilegal number of players for this court");
		}
		this.numPlayers = numPlayers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Surface getSurface() {
		return surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	protected abstract Surface[] allowedSurfaces();

	protected abstract int maxPlayers();

	@Override
	public String toString() {
		return "Court [name=" + name + "]";
	}

}

class ReservationPredicate implements Predicate<Reservation> {

	private Reservation newReservation;

	public ReservationPredicate(Reservation newReservation) {
		this.newReservation = newReservation;
	}

	@Override
	public boolean test(Reservation t) {
		return newReservation.overlapsWith(t);
	}

}
