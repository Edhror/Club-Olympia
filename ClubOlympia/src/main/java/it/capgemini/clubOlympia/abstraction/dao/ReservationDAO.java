package it.capgemini.clubOlympia.abstraction.dao;

import it.capgemini.clubOlympia.entities.Reservation;

public interface ReservationDAO {
    Iterable<Reservation> allReservations();
    Reservation add(Reservation newReservation);
    Reservation update(Reservation newReservation);
    Reservation delete (int idReservation);
    Reservation findById(int id);
}
