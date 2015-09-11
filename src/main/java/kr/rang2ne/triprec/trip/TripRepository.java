package kr.rang2ne.triprec.trip;

import kr.rang2ne.triprec.trip.model.Trip;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rang on 2015-09-11.
 */
public interface TripRepository extends CrudRepository<Trip, Long> {
}
