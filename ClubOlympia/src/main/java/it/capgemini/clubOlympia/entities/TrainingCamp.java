package it.capgemini.clubOlympia.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class TrainingCamp {

	@Id
	@SequenceGenerator(name = "trainingCampSeq", sequenceName = "public.training_camp_id_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trainingCampSeq")
	private int id;

	@Column(name = "start_date")
	private LocalDateTime start;
	
	@Column(name = "end_date")
	private LocalDateTime end;

	@ManyToOne
	@JoinColumn(name = "coach_id")
	private Coach coach;

	private double cost;

	@OneToMany(mappedBy = "trainingCamp")
	List<Reservation> reservations;

	@Enumerated(EnumType.STRING)
	private TipoSport tipoSport;

	
	@ManyToMany
	@JoinTable(
			name="trainingcamp_client",
			joinColumns=@JoinColumn(name="training_camp_id"),
			inverseJoinColumns=@JoinColumn(name="client_id")
			)
	private List<Client> clients = new ArrayList<>();
	
	
	public TrainingCamp(int id, LocalDateTime start, LocalDateTime end, Coach coach, double cost,
			TipoSport tipoSport) {
	     this(id, start,end, coach, cost, tipoSport, new ArrayList<>());
	}

	public TrainingCamp(int id, LocalDateTime start, LocalDateTime end, Coach coach, double cost,
			TipoSport tipoSport,List<Reservation> reservations) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.coach = coach;
		this.cost = cost;
		this.tipoSport = tipoSport;
		this.reservations = reservations;
	}
	
	public TrainingCamp() {
		this.reservations = new ArrayList<>();
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

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public TipoSport getTipoSport() {
		return tipoSport;
	}

	public void setTipoSport(TipoSport tipoSport) {
		this.tipoSport = tipoSport;
	}


	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
		reservation.setTrainingCamp(this);
	}
	

}
