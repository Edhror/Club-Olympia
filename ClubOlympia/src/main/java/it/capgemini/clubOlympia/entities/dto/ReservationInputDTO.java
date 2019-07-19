package it.capgemini.clubOlympia.entities.dto;

import java.time.LocalDateTime;

import it.capgemini.clubOlympia.entities.Reservation;

public class ReservationInputDTO {
	private int id;
	private LocalDateTime start;
	private LocalDateTime end;
	private double cost;
	private int clientId;
	private int courtId;
	private int coachId;



	public ReservationInputDTO(int id, LocalDateTime start, LocalDateTime end, double cost, int clientId, int courtId, int coachId) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.cost = cost;
		this.clientId = clientId;
		this.courtId = courtId;
		this.coachId = coachId;
	}

	public ReservationInputDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}
	public int getCoachId() {
		return coachId;
	}

	public void setCoachId(int coachId) {
		this.coachId = coachId;
	}

	public Reservation toReservation() {
		return new Reservation(this.id, this.start, this.end, null, null, null, this.cost);
	}

	public static ReservationInputDTO reservationToDTO(Reservation res) {
		if(res.getCoach() != null) {
			return new ReservationInputDTO(res.getId(), res.getStart(), res.getEnd(), res.getCost(),
					res.getClient().getId(), res.getCourt().getId(), res.getCoach().getId());
		}
		return new ReservationInputDTO(res.getId(), res.getStart(), res.getEnd(), res.getCost(),
				res.getClient().getId(), res.getCourt().getId(), 0);
	}

}