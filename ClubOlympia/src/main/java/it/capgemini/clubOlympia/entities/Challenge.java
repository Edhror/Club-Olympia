package it.capgemini.clubOlympia.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "challenge")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Challenge {

	@Id
	@SequenceGenerator(name="challengeSeq",sequenceName="public.challenge_id_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="challengeSeq")
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="reservation_id")
	private Reservation reservation;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="squadra_uno_id")
	private Team squadra1;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="squadra_due_id")	
	private Team squadra2;
	
	@Column
	private int punti1,punti2;
	
	public Challenge(int id, Reservation reservation) {
		this.id = id;
		this.reservation = reservation;
		this.squadra1 = new Team(0,reservation.getCourt().getNumPlayers()/2);
		this.squadra2 = new Team(0,reservation.getCourt().getNumPlayers()/2);
	}

	public Challenge(int id, Reservation reservation, Team squadra1, Team squadra2) {
		this.id = id;
		this.reservation = reservation;
		this.squadra1 = squadra1;
		this.squadra2 = squadra2;
	}
	
	public Challenge() {}

	public String getVincitore() {
		if (punti1 == punti2) {
			return "Pareggio";
		}
		if (punti1 > punti2) {
			return "Vince la squadra 1";
		} else {
			return "Vince la squadra 2";
		}
	}
	
	public void aggiungiGiocatore(Client client, boolean squadraG) {
		if (squadraG) {
			squadra1.aggiungiGiocatore(client);
			return;
		}
		squadra2.aggiungiGiocatore(client);
	}
	
	public void togliGiocatore(Client client, boolean squadraG) {
		if (squadraG) {
			squadra1.togliGiocatore(client);
			return;
		}
		squadra2.togliGiocatore(client);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Team getSquadra1() {
		return squadra1;
	}

	public void setSquadra1(Team squadra1) {
		this.squadra1 = squadra1;
	}

	public Team getSquadra2() {
		return squadra2;
	}

	public void setSquadra2(Team squadra2) {
		this.squadra2 = squadra2;
	}

	public int getPunti1() {
		return punti1;
	}

	public void setPunti1(int punti1) {
		this.punti1 = punti1;
	}

	public int getPunti2() {
		return punti2;
	}

	public void setPunti2(int punti2) {
		this.punti2 = punti2;
	}
}
