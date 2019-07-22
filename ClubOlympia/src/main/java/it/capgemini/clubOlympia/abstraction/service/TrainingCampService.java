package it.capgemini.clubOlympia.abstraction.service;

import it.capgemini.clubOlympia.entities.TrainingCamp;

public interface TrainingCampService {

    Iterable<TrainingCamp> allTrainingCamp();
    TrainingCamp add(TrainingCamp newTrainingCamp);
    TrainingCamp update(TrainingCamp newTrainingCamp);
    TrainingCamp delete (int idTrainingCamp);
    TrainingCamp findById(int id);
}
