package it.capgemini.clubOlympia.entities.dto;

import java.time.LocalDateTime;

import it.capgemini.clubOlympia.entities.Reservation;

public class ReservationDTO {
	private int id;
	private LocalDateTime start;
	private LocalDateTime end;
	private double cost;
	private String clientName;
	private String courtName;

	public ReservationDTO(int id, LocalDateTime start, LocalDateTime end, double cost, String clientName,
			String courtName) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.cost = cost;
		this.clientName = clientName;
		this.courtName = courtName;

	}

	public ReservationDTO() {
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public Reservation toReservation() {
		return new Reservation(this.id, this.start, this.end, null, null, this.cost);
	}

	public static ReservationDTO reservationToDTO(Reservation res) {

		return new ReservationDTO(res.getId(), res.getStartTime(), res.getEndTime(), res.getCost(),
				res.getClient().getName() + " " + res.getClient().getLastname(), res.getCourt().getName());
	}

}
