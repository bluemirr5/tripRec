package kr.rang2ne.triprec.account;

import kr.rang2ne.triprec.account.model.LoginDto;
import kr.rang2ne.triprec.account.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rang on 2015-09-11.
 */
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member findOne(String id) {
        return memberRepository.findOne(id);
    }

    public LoginDto login(String id, String password) throws Exception {
        LoginDto result = null;
        Member member = memberRepository.findOne(id);
        if(member != null) {
            AccountPasswordEncrypter encryptor = new AccountPasswordEncrypter(id);
            if(member.getPassword().equals(encryptor.encrypt(password))) {
                result = new LoginDto(LoginDto.LOGIN_OK, "OK", member);
            } else {
                result = new LoginDto(LoginDto.LOGIN_FAIL_PASSWORD, "PASSWORD IS WRONG");
            }
        } else {
            result = new LoginDto(LoginDto.LOGIN_FAIL_ID, "ID IS WRONG");
        }
        return result;
    }

    public void save(Member member) throws Exception {
        AccountPasswordEncrypter encryptor = new AccountPasswordEncrypter(member.getId());
        member.setPassword(encryptor.encrypt(member.getPassword()));
        memberRepository.save(member);
    }

    public void remove(String id) throws Exception {
        memberRepository.delete(id);
    }
}
