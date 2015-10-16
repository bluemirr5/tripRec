package kr.rang2ne.triprec;

import kr.rang2ne.triprec.account.MemberService;
import kr.rang2ne.triprec.trip.TripService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by rang on 2015-09-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TripTests {
    @Autowired
    private TripService tripService;

    @Autowired
    private MemberService memberService;

    @Test
    public void tripCRUDTest() throws Exception {
//        Member member = memberService.findOne("bluemirr5");
//
//        Trip trip = new Trip();
//        trip.setDesc("ddd");
//        trip.setMember(member);
//        trip.setName("test1");
//        trip.setRegTime(Calendar.getInstance().getTime());
//        trip.setModTime(Calendar.getInstance().getTime());
//        trip.setPublished(false);
//
//        Scene scene1 = new Scene();
//        scene1.setDesc("first");
//        Scene scene2 = new Scene();
//        scene2.setDesc("second");
//
//        List<Scene> scenes = new ArrayList<>();
//        scenes.add(scene1);
//        scenes.add(scene2);
//
//        trip.setScenes(scenes);
//        tripService.save(trip);
//
//        System.out.println(tripService.getClass());
//        tripService.transactionTest();
    }
}
