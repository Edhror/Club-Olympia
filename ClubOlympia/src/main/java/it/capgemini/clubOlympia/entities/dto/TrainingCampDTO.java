package it.capgemini.clubOlympia.entities.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import it.capgemini.clubOlympia.entities.TipoSport;
import it.capgemini.clubOlympia.entities.TrainingCamp;

public class TrainingCampDTO {

	private int id;
	private LocalDateTime start;
	private LocalDateTime end;
	private double cost;
	private CoachForSelectionDTO coachDto;
	private TipoSport tipoSport;
	private List<ReservationDTO> reservations;

	public TrainingCampDTO(int id, LocalDateTime start, LocalDateTime end, double cost,
			CoachForSelectionDTO coachDto, TipoSport tipoSport, List<ReservationDTO> reservations) {
		this.id = id;
		this.start = start;
		this.end = end;
		this.cost = cost;
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

	public List<ReservationDTO> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationDTO> reservations) {
		this.reservations = reservations;
	}

	public static TrainingCampDTO toTrainingCampDto(TrainingCamp val) {
		
		CoachForSelectionDTO coach = CoachForSelectionDTO.toCoachDto(val.getCoach());

		return new TrainingCampDTO(val.getId(), val.getStart(), val.getEnd(), val.getCost(),  coach,
				val.getTipoSport(), val.getReservations().stream().map(ReservationDTO::reservationToDTO).collect(Collectors.toList()));
	}

	public TrainingCamp toTrainingCamp() {
		return new TrainingCamp(this.id, this.start, this.end, null, this.cost, this.tipoSport,
				this.reservations.stream().map(ReservationDTO::toReservation).collect(Collectors.toList()) );
	}

}
