package kr.rang2ne.triprec.view;

import kr.rang2ne.triprec.account.model.Member;
import kr.rang2ne.triprec.trip.TripService;
import kr.rang2ne.triprec.view.model.TripDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by rang on 2015-09-17.
 */
@Controller
public class TripViewController {
    @Autowired
    private TripService tripService;

    @RequestMapping(
            value="/trip",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity postTrip(
            @RequestBody TripDto.Create trip,
            HttpSession session
    ) throws Exception {
        Member member = (Member) session.getAttribute("auth");
        tripService.insert(trip, member);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(
            value="/trip",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity putTrip(
            @RequestBody TripDto.Update dto,
            HttpSession session
    ) throws Exception {
        Member member = (Member) session.getAttribute("auth");
        tripService.update(dto, member);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value="/trip/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity deleteTrip(
            @PathVariable Long id,
            HttpSession session
    ) throws Exception {
        tripService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            value="/trips",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity getTrips(
            HttpSession session
    ) throws Exception {
        Member member = (Member) session.getAttribute("auth");
        return new ResponseEntity(tripService.getTrips(member.getId()), HttpStatus.OK);
    }
}
