package it.capgemini.clubOlympia.entities.dto;

import java.time.LocalDateTime;
import java.util.List;

import it.capgemini.clubOlympia.entities.Reservation;
import it.capgemini.clubOlympia.entities.TipoSport;
import it.capgemini.clubOlympia.entities.TrainingCamp;

public class TrainingCampDTO {

	private int id;
	private LocalDateTime start;
	private LocalDateTime end;
	private double cost;
	private CourtForSelectionDTO courtDto;
	private CoachForSelectionDTO coachDto;
	private TipoSport tipoSport;
	private List<Reservation> reservations;

	public TrainingCampDTO(int id, LocalDateTime start, LocalDateTime end, double cost, CourtForSelectionDTO courtDto,
			CoachForSelectionDTO coachDto, TipoSport tipoSport, List<Reservation> reservations) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.cost = cost;
		this.courtDto = courtDto;
		this.coachDto = coachDto;
		this.tipoSport = tipoSport;
		this.reservations = reservations;
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

	public CourtForSelectionDTO getCourtDto() {
		return courtDto;
	}

	public void setCourtDto(CourtForSelectionDTO courtDto) {
		this.courtDto = courtDto;
	}

	public CoachForSelectionDTO getCoachDto() {
		return coachDto;
	}

	public void setCoachDto(CoachForSelectionDTO coachDto) {
		this.coachDto = coachDto;
	}

	public TipoSport getTipoSport() {
		return tipoSport;
	}

	public void setTipoSport(TipoSport tipoSport) {
		this.tipoSport = tipoSport;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public static TrainingCampDTO toTrainingCampDto(TrainingCamp val) {
		CourtForSelectionDTO court = CourtForSelectionDTO.toCourtDto(val.getCourt());
		CoachForSelectionDTO coach = CoachForSelectionDTO.toCoachDto(val.getCoach());

		return new TrainingCampDTO(val.getId(), val.getStart(), val.getEnd(), val.getCost(), court, coach,
				val.getTipoSport(), val.getReservations());
	}

	public TrainingCamp toTrainingCamp() {
		return new TrainingCamp(this.id, this.start, this.end, null, null, this.cost, this.tipoSport, this.reservations );
	}

}
