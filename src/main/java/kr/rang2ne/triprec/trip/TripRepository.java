package kr.rang2ne.triprec.trip;

import kr.rang2ne.triprec.account.model.Member;
import kr.rang2ne.triprec.trip.model.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by rang on 2015-09-11.
 */
public interface TripRepository extends CrudRepository<Trip, Long> {
    List<Trip> findByMember(Member member);

    @Query("select t from Trip t where t.member.id = :id")
    Trip findByMemberWith(@Param("id")String id);
}
