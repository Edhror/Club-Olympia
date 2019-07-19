package it.capgemini.clubOlympia.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Reservation {
	@Id
	@SequenceGenerator(name="reservationSeq",sequenceName="public.reservation_id_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reservationSeq")
	private int id;
	@Column(name="start_date")
	private LocalDateTime start;
	@Column(name="end_date")
	private LocalDateTime end;
    private double cost;
    @ManyToOne
    @JoinColumn(name="client_id")
	private Client client;
    @ManyToOne
    @JoinColumn(name="court_id")
    private Court court;
    
    @ManyToOne
    @JoinColumn(name="coach_id")
    private Coach coach;
    
	public Reservation(int id, LocalDateTime start, LocalDateTime end, Client client, Court court, Coach coach, double cost) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.client = client;
		this.court = court;
		this.coach = coach;
		this.cost = cost;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Reservation() {

	}

	public boolean overlapsWith(Reservation other) {
		return start.isBefore(other.getEnd()) && end.isAfter(other.getStart());
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		Reservation other = (Reservation) obj;
		return this.id == other.id;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", start=" + start + ", end=" + end + ", client=" + client + ", court=" + court
				+ ", cost=" + cost + "]";
	}

	
}
