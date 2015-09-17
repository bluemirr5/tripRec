package kr.rang2ne.triprec.view;

import kr.rang2ne.triprec.account.MemberService;
import kr.rang2ne.triprec.account.model.LoginDto;
import kr.rang2ne.triprec.trip.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by rang on 2015-09-11.
 */
@Controller
public class LoginMainViewController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private TripService tripService;

    @RequestMapping("/")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(
            value = "/main",
            method = RequestMethod.POST
    )
    public String login(
            @RequestParam String id,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) throws Exception {
        LoginDto result = memberService.login(id, password);
        if(result.getResultCode() == LoginDto.LOGIN_OK) {
            session.setAttribute("auth", result.getMember());
            model.addAttribute("trips", tripService.getTrips(result.getMember().getId()));
            return "main";
        } else {
            model.addAttribute("loginStatus", result.getResultCode());
            return "login";
        }
    }
}
