package it.capgemini.clubOlympia.abstraction.dao;

import java.time.LocalDateTime;

import it.capgemini.clubOlympia.entities.TipoSport;
import it.capgemini.clubOlympia.entities.TrainingCamp;

public interface TrainingCampDAO {

    Iterable<TrainingCamp> allTrainingCamp();
    TrainingCamp add(TrainingCamp newTrainingCamp);
    TrainingCamp update(TrainingCamp newTrainingCamp);
    TrainingCamp delete (int idTrainingCamp);
    TrainingCamp findById(int id);
    Iterable<TrainingCamp> byTimeRangeAndSport(LocalDateTime start, LocalDateTime end, TipoSport tipoSport);
}
