package kr.rang2ne.triprec;

import kr.rang2ne.triprec.account.MemberService;
import kr.rang2ne.triprec.account.model.LoginDto;
import kr.rang2ne.triprec.account.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Calendar;

/**
 * Created by rang on 2015-09-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TripRecApplication.class)
public class MemberTests {
    @Autowired
    MemberService memberService;

    @Test
    public void memberCRUDTest() throws Exception {
        String testId = "bluemirr5"+System.currentTimeMillis();
        String testPass = "1234"+System.currentTimeMillis();

        Member member = new Member();
        member.setId(testId);
        member.setName("WGS");
        member.setNickName("Rang");
        member.setSex("M");
        member.setPassword(testPass);
        member.setBirth(Calendar.getInstance().getTime());
        member.setJoinTime(Calendar.getInstance().getTime());
        memberService.save(member);
    }

    @Test
    public void memberLoginTest() throws Exception {
        String testId = "bluemirr5"+System.currentTimeMillis();
        String testPass = "1234"+System.currentTimeMillis();

        Member member = new Member();
        member.setId(testId);
        member.setName("WGS");
        member.setNickName("Rang");
        member.setSex("M");
        member.setPassword(testPass);
        member.setBirth(Calendar.getInstance().getTime());
        member.setJoinTime(Calendar.getInstance().getTime());
        memberService.save(member);

        LoginDto loginDto = memberService.login(testId, testPass);
        Assert.isTrue(LoginDto.LOGIN_OK == loginDto.getResultCode());

        memberService.remove(member.getId());
    }
}
