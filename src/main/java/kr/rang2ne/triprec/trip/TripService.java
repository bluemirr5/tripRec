package kr.rang2ne.triprec.trip;

import kr.rang2ne.triprec.account.model.Member;
import kr.rang2ne.triprec.trip.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rang on 2015-09-11.
 */
@Service
public class TripService  {
    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getTrips(String memberId) throws Exception {
        Member member = new Member();
        member.setId(memberId);
        return tripRepository.findByMember(member);
    }
}
