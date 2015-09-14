package kr.rang2ne.triprec.trip;

import kr.rang2ne.triprec.account.model.Member;
import kr.rang2ne.triprec.trip.model.Scene;
import kr.rang2ne.triprec.trip.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rang on 2015-09-11.
 */
@Service
public class TripService  {
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private SceneRepository sceneRepository;

//    @Transactional(readOnly = true)
    public List<Trip> getTrips(String memberId) throws Exception {
        Member member = new Member();
        member.setId(memberId);
        System.out.println("in service end");
        return tripRepository.findByMember(member);
    }

    @Transactional
    public void save(Trip trip) {
        List<Scene> scenes = trip.getScenes();
        scenes.forEach(scene -> sceneRepository.save(scene));
        tripRepository.save(trip);
    }
}
