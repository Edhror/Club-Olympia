package it.capgemini.clubOlympia.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQuery(
		name="Reservation.findByTimeRange",
		query="select r from Reservation r where startTime < :endRange and endTime > :startRange"
		)

@NamedNativeQuery(
		name="Reservation.findByTimeRangeForTennis",
		resultClass = Reservation.class,
		query="select r.id, r.start_date, r.end_date, r.cost, r.client_id, r.court_id, r.training_camp_id from reservation r inner join tennis_court t on r.court_id = t.id where start_date < ? and end_date > ?"
		)

@NamedNativeQuery(
		name="Reservation.findByTimeRangeForSoccer",
		resultClass = Reservation.class,
		query="select r.id, r.start_date, r.end_date, r.cost, r.client_id, r.court_id, r.training_camp_id from reservation r inner join soccer_court s on r.court_id = s.id where start_date < ? and end_date > ?"
		)
public class Reservation {
	@Id
	@SequenceGenerator(name="reservationSeq",sequenceName="public.reservation_id_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="reservationSeq")
	private int id;
	@Column(name="start_date")
	private LocalDateTime startTime;
	@Column(name="end_date")
	private LocalDateTime endTime;
    private double cost;
    @ManyToOne
    @JoinColumn(name="client_id")
	private Client client;
    @ManyToOne
    @JoinColumn(name="court_id")
    private Court court;
    
    @ManyToOne
    @JoinColumn(name="training_camp_id", nullable = true)
    private TrainingCamp trainingCamp;
    


	public Reservation(int id, LocalDateTime start, LocalDateTime end, Client client, Court court, double cost) {
		this.id = id;
		this.startTime = start;
		this.endTime = end;
		this.client = client;
		this.court = court;
		this.cost = cost;
	}

	
	public TrainingCamp getTrainingCamp() {
		return trainingCamp;
	}

	public void setTrainingCamp(TrainingCamp trainingCamp) {
		this.trainingCamp = trainingCamp;
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
		return startTime.isBefore(other.getEndTime()) && endTime.isAfter(other.getStartTime());
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	
	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime start) {
		this.startTime = start;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime end) {
		this.endTime = end;
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
		return "Reservation [id=" + id + ", start=" + startTime + ", end=" + endTime + ", client=" + client + ", court=" + court
				+ ", cost=" + cost + "]";
	}

	
}
