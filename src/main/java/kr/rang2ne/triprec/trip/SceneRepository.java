package kr.rang2ne.triprec.trip;

import kr.rang2ne.triprec.trip.model.Scene;
import kr.rang2ne.triprec.trip.model.Trip;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rang on 2015-09-12.
 */
public interface SceneRepository extends CrudRepository<Scene, Long> {
    void deleteByTrip(Trip trip);
}
